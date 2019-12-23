package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class TrendingVideo {

    private static final int ID = 0;
    private static final int TITLE = 2;
    private static final int PUBLISH_DATE = 3;
    private static final int TAGS = 4;
    private static final int VIEWS = 5;
    private static final int LIKES = 6;
    private static final int DISLIKES = 7;

    private String id;
    private String title;
    private LocalDate publishDate;
    private Set<String> tags;
    private long views;
    private long likes;
    private long dislikes;

    private TrendingVideo(String id, String title, LocalDate publishDate,
                          Set<String> tags, long views, long likes, long dislikes) {
        this.id = id;
        this.title = title;
        this.publishDate = publishDate;
        this.tags = tags;
        this.views = views;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    /**
     * Returns a Movie instance, based on the given @{line} from the dataset.
     **/
    public static TrendingVideo createTrendingVideo(String line) {
        String[] tokens = line.split("\t");

        String id = tokens[ID];
        String title = tokens[TITLE];

        String parsedDate = tokens[PUBLISH_DATE].substring(0, tokens[PUBLISH_DATE]
                .indexOf('T')); // 2017-11-13T17:13:01.000Z
        LocalDate publishDate = LocalDate.parse(parsedDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Set<String> tags = Stream.of(tokens[TAGS].split("\\|"))
                .map(s -> s.replace("\"", ""))
                .collect(Collectors.toSet());

        long views = Long.parseLong(tokens[VIEWS]);
        long likes = Long.parseLong(tokens[LIKES]);
        long dislikes = Long.parseLong(tokens[DISLIKES]);

        return new TrendingVideo(id, title, publishDate, tags, views, likes, dislikes);

    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public Set<String> getTags() {
        return tags;
    }

    public long getViews() {
        return views;
    }

    public long getLikes() {
        return likes;
    }

    public long getDislikes() {
        return dislikes;
    }

    public long getLikeRatio() {
        return likes - dislikes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrendingVideo)) return false;
        TrendingVideo trendingVideo = (TrendingVideo) o;
        return id.equals(trendingVideo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}