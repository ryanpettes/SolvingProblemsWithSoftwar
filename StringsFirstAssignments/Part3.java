
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurences (String stringa, String stringb) {
        // Find first occurence
        int firstOccurence = stringb.indexOf(stringa);
        if (firstOccurence == -1) { return false; }
        // Find second occurence
        int secondOccurence = stringb.indexOf(stringa, 
                              firstOccurence+1);
        if (secondOccurence == -1) { return false; }
        return true;
    }
    
    public void testing() {
        // Declare testing variables
        String stringa, stringb, remaining;
        boolean test1;
        
        // Test 1 (should pass)
        stringa = "by";
        stringb = "A story by Abby Long";
        test1 = twoOccurences(stringa, stringb);
        System.out.println("stringa: " + stringa + "\nstringb: " + stringb);
        if (test1) {
            System.out.println("Result is true");
        } else {
            System.out.println("Result is false");
        }
        remaining = lastPart(stringa, stringb);
        System.out.println("The part of " + stringb + " after " + stringa + 
        " is " + remaining + "\n");
        
        // Test 2 (should fail, 0 occurences)
        stringa = "c";
        stringb = "banana";
        test1 = twoOccurences(stringa, stringb);
        System.out.println("stringa: " + stringa + "\nstringb: " + stringb);
        if (test1) {
            System.out.println("Result is true");
        } else {
            System.out.println("Result is false");
        }
        remaining = lastPart(stringa, stringb);
        System.out.println("The part of " + stringb + " after " + stringa + 
        " is " + remaining + "\n");
        
        // Test 3 (should fail, only 1 occurence)
        stringa = "atg";
        stringb = "ctgtatgta";
        test1 = twoOccurences(stringa, stringb);
        System.out.println("stringa: " + stringa + "\n" + "stringb: " + stringb);
        if (test1) {
            System.out.println("Result is true");
        } else {
            System.out.println("Result is false");
        }
        remaining = lastPart(stringa, stringb);
        System.out.println("The part of " + stringb + " after " + stringa + 
        " is " + remaining + "\n");
    }
    
    public String lastPart(String stringa, String stringb) {
        // Look for first occurence of stringa
        int index = stringb.indexOf(stringa);
        // If found, return remaining portion of stringb
        // Otherwise, return entire stringb
        if (index != -1) {
            return (stringb.substring(index+stringa.length()));
        } else { return stringb; }
    }
}
