package youtube;

import model.YoutubeTrendsExplorer;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class YoutubeTrendsExplorerTest {
    private static String data;
    private static String emptyData;
    private static InputStream input;
    private static YoutubeTrendsExplorer explorer;
    private static InputStream emptyInput;
    private static YoutubeTrendsExplorer emptyExplorer;

    @BeforeClass
    public static void setUp() {
        data = "video_id\ttrending_date\ttitle\tpublish_time\ttags\tviews\tlikes\tdislikes"
                + System.lineSeparator()
                + "ffxKSjUwKdU\t18.14.05\tAriana Grande - No Tears Left To Cry\t"
                + "2018-04-20T04:00:03.000Z\tAriana|\"Grande\"|\"No\"|\"Tears\"|"
                + "\"Left\"|\"To\"|\"Cry\"|\"Universal\"|\"Records\"|\"Pop\""
                + "\t148689896\t3094021\t129502" + System.lineSeparator()
                + "7C2z4GqqS5E\t18.01.06\tBTS (방탄소년단) 'FAKE LOVE' Official MV"
                + "\t2018-05-18T09:00:02.000Z\tBIGHIT|\"빅히트\"|\"방탄소년단\"|\"BTS\"|"
                + "\"BANGTAN\"|\"방탄\"|\"FAKE LOVE\"|\"FAKE_LOVE\"|\"fake love\""
                + "\t123010920\t5613827\t206892" + System.lineSeparator()
                + "ffxKSjUwKdU\t18.07.05\tAriana Grande - No Tears Left To Cry"
                + "\t2018-04-20T04:00:03.000Z\tAriana|\"Grande\"|\"No\"|\"Tears\"|"
                + "\"Left\"|\"To\"|\"Cry\"|\"Universal\"|\"Records\"|\"Pop\""
                + "\t112904452\t2875001\t115569" + System.lineSeparator()
                + "UQtt9I6c-YM\t18.07.01\tKramer vs Kramer-Clou Scene"
                + "\t2008-04-05T18:22:40.000Z\tMeryl|\"Streep\"|\"kramer\"|\"vs\"|"
                + "\"dustin\"|\"hoffman\"|\"clou\"|\"scene\"" + "\t50030\t46\t6"
                + System.lineSeparator()
                + "VYOjWnS4cMY\t18.02.06\tChildish Gambino - This Is America "
                + "(Official Video)\t2018-05-06T04:00:07.000Z\tChildish Gambino|"
                + "\"Rap\"|\"This Is America\"|\"mcDJ Recording/RCA Records\""
                + "\t225211923\t5023450\t343541" + System.lineSeparator()
                + "FlsCjmMhFmw\t17.14.12\tYouTube Rewind: The Shape of 2017 "
                + "| #YouTubeRewind\t2017-12-06T17:58:51.000Z\tRewind|\"Rewind 2017\"|"
                + "\"youtube rewind 2017\"|\"#YouTubeRewind\"|\"Rewind 2016\"|"
                + "\"Dan and Phil\"|\"Grace Helbig\"|\"HolaSoyGerman\"|\"Lilly Singh\""
                + "|\"Markiplier\"|\"Swoozie\"|\"Rhett Link\"|\"Liza Koshy\"|\"Dolan Twins\""
                + "|\"Lele Pons\"|\"Jake Paul\"|\"Logan Paul\"|\"KSI\"|\"Joey Graceffa\"|"
                + "\"Casey Neistat\"|\"Poppy\"|\"Niana Guerrero\"|\"Daddy Yankee\"|\"Luis Fonsi\""
                + "|\"Ed Sheeran\"|\"Kendrick Lamar\"|\"Stephen Colbert\"|\"Fidget Spinners\"|"
                + "\"Slime\"|\"Backpack Kid\"|\"April the Giraffe\"|\"#Rewind\"|\"Despacito\""
                + "|\"Shape of you\"|\"YouTubeRewind\"|\"I’m the One\"|\"Humble\""
                + "\t149376127\t3093544\t1643059";

        emptyData = "";
        input = new ByteArrayInputStream(data.getBytes());
        explorer = new YoutubeTrendsExplorer(input);
        emptyInput = new ByteArrayInputStream(emptyData.getBytes());
        emptyExplorer = new YoutubeTrendsExplorer(emptyInput);
    }

    @AfterClass
    public static void close() throws IOException {
        input.close();
        emptyInput.close();
    }

    @Test
    public void testFindIdOfLeastLikedVideoShouldReturnValidId() {
        final String expectedId = "UQtt9I6c-YM";
        final String actualId = explorer.findIdOfLeastLikedVideo();

        assertEquals(expectedId, actualId);
    }

    @Test
    public void testFindIdOfMostLikedLeastDislikedVideoShouldReturnValidId() {
        final String expectedId = "7C2z4GqqS5E";
        final String actualId = explorer.findIdOfMostLikedLeastDislikedVideo();

        assertEquals(expectedId, actualId);
    }


    @Test
    public void testFindDistinctTitlesOfTop3VideosByViewsShouldReturnValidListOfTitles() {
        final List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Childish Gambino - This Is America (Official Video)");
        expectedResult.add("YouTube Rewind: The Shape of 2017 | #YouTubeRewind");
        expectedResult.add("Ariana Grande - No Tears Left To Cry");
        final List<String> actualResult = explorer.findDistinctTitlesOfTop3VideosByViews();

        assertArrayEquals(expectedResult.toArray(), actualResult.toArray());
    }

    @Test
    public void testFindDistinctTitlesOfTop3VideosByViewsWithEmptyDataShouldReturnEmptyList() {
        assertTrue(emptyExplorer.findDistinctTitlesOfTop3VideosByViews().isEmpty());
    }

    @Test
    public void testFindIdOfMostTaggedVideoShouldReturnValidId() {
        final String expectedId = "FlsCjmMhFmw";
        final String actualId = explorer.findIdOfMostTaggedVideo();

        assertEquals(expectedId, actualId);
    }


    @Test
    public void testFindTitleOfFirstVideoTrendingBefore100KViewsShouldReturnValidId() {
        final String expectedId = "Kramer vs Kramer-Clou Scene";
        final String actualId = explorer.findTitleOfFirstVideoTrendingBefore100KViews();

        assertEquals(expectedId, actualId);
    }


    @Test
    public void testFindIdOfMostTrendingVideoShouldReturnValidId() {
        final String expectedId = "ffxKSjUwKdU";
        final String actualId = explorer.findIdOfMostTrendingVideo();

        assertEquals(expectedId, actualId);
    }
}