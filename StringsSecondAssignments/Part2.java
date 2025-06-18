
/**
 * Class containing the method method named howMany that has two String parameters named stringa and stringb. 
 * This method returns an integer indicating how many times stringa appears in stringb.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
        int count = 0;
        
        // Index of occurrence of stringa in stringb
        int occurrence = stringb.indexOf(stringa);
        // While a subsequent occurrence exists, add it to the count
        while (occurrence != -1) {
            count++;
            occurrence = stringb.indexOf(stringa, occurrence + stringa.length());
        }
        
        return count;
    }
    
    public void testHowMany() {
        String test1a = "ABA";
        String test1b = "ABABA";
        // Should return 1
        int result1 = howMany(test1a, test1b);
        System.out.println("Original string: " + test1b + "\n" + "Substring: " + test1a);
        System.out.println("# occurrences = " + result1 + "\n");
        
        String test2a = "xyz";
        String test2b = "abcdefghijklmnop";
        // Should return 0
        int result2 = howMany(test2a, test2b);
        System.out.println("Original string: " + test2b + "\n" + "Substring: " + test2a);
        System.out.println("# occurrences = " + result2 + "\n");
        
        String test3a = "A";
        String test3b = "AAAAAAA";
        // Should return 7
        int result3 = howMany(test3a, test3b);
        System.out.println("Original string: " + test3b + "\n" + "Substring: " + test3a);
        System.out.println("# occurrences = " + result3 + "\n");
    }
}
