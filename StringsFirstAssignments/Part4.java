import edu.duke.URLResource;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    // Finds and prints YouTube links in a list of various links on a webpage
    public void findYoutubeLinks() {
        URLResource url = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
        
        int loopCount = 0;
        // Substring that all YouTube links must contain
        String yt = "youtube.com";
        // Iterate thru all words on webpage
        for (String word : url.words()) {
            // Temporary lowercase version of word for comparison
            String temp = word.toLowerCase();
            int ytPos = temp.indexOf(yt);
            // If temp contains yt, then print the link
            if (ytPos != -1) {
                int endQuote = temp.indexOf("\"", ytPos+1);
                int startQuote = temp.lastIndexOf("\"", ytPos);
                System.out.println(word.substring(startQuote+1, endQuote));
            }
        }
        return;
    }
}
