package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class YoutubeTrendsExplorer {

    private Map<String, List<TrendingVideo>> trendingVideos;

    /**
     * Loads the dataset from the given {@code dataInput} stream.
     */
    public YoutubeTrendsExplorer(InputStream dataInput) {
    	trendingVideos = new HashMap<>();
        loadTrendingVideos(dataInput);
    }


	/**
     * Returns all videos loaded from the dataset.
     **/
    public Collection<TrendingVideo> getTrendingVideos() {
    	return trendingVideos.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
    
    /**
     * Returns the id of the least liked video.
     */
    public String findIdOfLeastLikedVideo() {
    	  return getTrendingVideos().stream()
                  .min(Comparator.comparing(TrendingVideo::getLikes))
                  .get()
                  .getId();
    }
    
    /**
     * Returns the id of the most likable video.
     */
    public String findIdOfMostLikedLeastDislikedVideo() {
    	return getTrendingVideos().stream()
                .max(Comparator.comparing(TrendingVideo::getLikeRatio))
                .get()
                .getId();
    }
    
    /**
     * Returns list of titles of the most watched trending videos.
     */
    public List<String> findDistinctTitlesOfTop3VideosByViews(){
    	final int limit= 3;

        return getTrendingVideos()
                .stream()
                .sorted(Comparator.comparing(TrendingVideo::getViews).reversed())
                .map(TrendingVideo::getTitle)
                .limit(limit)
                .collect(Collectors.toList());
    }
    
    /**
     * Returns the id of the most tagged video.
     */
    public String findIdOfMostTaggedVideo() {
    	 return getTrendingVideos()
                 .stream()
                 .max(Comparator.comparingInt(o -> o.getTags().size()))
                 .get()
                 .getId();
    } 
    
    /**
     * Returns the first trending video before it got 100K views
     */
    public String findTitleOfFirstVideoTrendingBefore100KViews() {
    	 final int maxViews = 100_000;
         
         return getTrendingVideos().stream()
                 .filter(video -> video.getViews() < maxViews)
                 .min(Comparator.comparing(TrendingVideo::getPublishDate))
                 .get()
                 .getTitle();
    }
    
    /**
     * Returns the id of the most trending video
     */
    public String findIdOfMostTrendingVideo() {
    	return trendingVideos.values()
                .stream()
                .max(Comparator.comparing(List::size))
                .get()
                .get(0)
                .getId();
    }

    private void loadTrendingVideos(InputStream dataInput) {
    	try (BufferedReader reader = new BufferedReader(new InputStreamReader(dataInput))) {
            reader.readLine(); 

            String currentLine;
            TrendingVideo currentTrendingVideo;
            while ((currentLine = reader.readLine()) != null) {
                currentTrendingVideo = TrendingVideo.createTrendingVideo(currentLine);
                addTrendingVideo(currentTrendingVideo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
    
    private void addTrendingVideo(TrendingVideo trendingVideo) {
        final String videoId = trendingVideo.getId();
        if (!trendingVideos.containsKey(videoId)) {
            trendingVideos.put(videoId, new ArrayList<>());
        }

        trendingVideos.get(videoId).add(trendingVideo);
    }
}
