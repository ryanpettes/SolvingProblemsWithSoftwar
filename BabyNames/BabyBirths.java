import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BabyBirths {
    public void printNames() {
        FileResource fr = new FileResource();
        // No header row
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name: " + rec.get(0) +
                                   " Gender: " + rec.get(1) +
                                   " Num Born: " + rec.get(2));
            }
        }
    }
    
    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys ++;
            } else {
                totalGirls ++;
            }
        }
        System.out.println("Total births = " + totalBirths);
        System.out.println("Total boys = " + totalBoys);
        System.out.println("Total girls = " + totalGirls);
    }
    
    public int getRank(int year, String name, String gender) {
        String filePrefix = "us_babynames/us_babynames_by_year/";
        String fileName = "yob" + year + ".csv";
        FileResource fr = new FileResource(filePrefix + fileName);
        
        int rank = -1;
        int curr = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                curr++;
                if (rec.get(0).equals(name)) {
                    rank = curr;
                    break;
                }
            }
        }
        
        return rank;
    }
    
    public String getName(int year, int rank, String gender) {
        String filePrefix = "us_babynames/us_babynames_by_year/";
        String fileName = "yob" + year + ".csv";
        FileResource fr = new FileResource(filePrefix + fileName);
        
        String ans = "NO NAME";
        int curr = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                curr++;
                if (curr == rank) {
                    ans = rec.get(0);
                    break;
                }
            }
        }
        
        return ans;
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int oldRank = getRank(year, name, gender);
        if (oldRank == -1) {
            System.out.println(name + " does not appear in list for the year " + year);
            return;
        }
        
        String newName = getName(newYear, oldRank, gender);
        if (newName.equals("NO NAME")) {
            System.out.println(name + " does not have a matching counterpart in the year " + year);
            return;
        }
        
        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " 
                            + newYear + ".");
    }
    
    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int highest = -1;
        int year = -1;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int currYear = Integer.parseInt(f.getName().substring(3, 7));
            int currRank = getRank(currYear, name, gender);
            
            // If first valid rank found
            if (highest == -1 && currRank != -1) {
                highest = currRank;
                year = currYear;
            } else if (currRank < highest && currRank != -1) {
                highest = currRank;
                year = currYear;
            }
        }
        
        return year;
    }
    
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int count = 0;
        int runningTotal = 0;
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int currYear = Integer.parseInt(f.getName().substring(3, 7));
            int currRank = getRank(currYear, name, gender);
            
            if (currRank != -1) {
                count++;
                runningTotal += currRank;
            }
        }
        
        if (count == 0) { return -1.0; }
        return ((double)runningTotal / count);
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        String filePrefix = "us_babynames/us_babynames_by_year/";
        String fileName = "yob" + year + ".csv";
        FileResource fr = new FileResource(filePrefix + fileName);
        int answer = 0;
        int origRank = getRank(year, name, gender);
        int currRank = 1;
        
        if (origRank == -1) { return answer; }
        
        for (CSVRecord rec : fr.getCSVParser()) {
            if (rec.get(1).equals(gender) && currRank < origRank) {
                answer += Integer.parseInt(rec.get(2));
                currRank++;
            }
        }
        
        return answer;
    }
    
    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public void testGetName() {
        int year = 1982;
        int rank = 450;
        String gender = "M";
        System.out.println("Name w/ rank " + rank + " and gender " + gender +
                            " is " + getName(year, rank, gender));
    }
    
    public void testGetRank() {
        int year = 1960;
        String name = "Emily";
        String gender = "F";
        System.out.println("Rank of " + name + " is " + getRank(year, name, gender));
    }
    
    public void testWhatIsNameInYear() {
        String name = "Owen";
        int year = 1974;
        int newYear = 2014;
        String gender = "M";
        
        whatIsNameInYear(name, year, newYear, gender);
    }
    
    public void testYearOfHighestRank() {
        String name = "Genevieve";
        String gender = "F";
        int ans = yearOfHighestRank(name, gender);
        if (ans == -1) { 
            System.out.println(name + " NOT FOUND");
            return;
        }
        System.out.println(name + " had a highest ranking in " + ans);
    }
    
    public void testGetAverageRank() {
        String name = "Robert";
        String gender = "M";
        double ans = getAverageRank(name, gender);
        System.out.println(name + " has an average rank of " + ans);
    }
    
    public void testGetTotalBirthsRankedHigher() {
        int year = 1990;
        String name = "Drew";
        String gender = "M";
        
        int ans = getTotalBirthsRankedHigher(year, name, gender);
        System.out.println(name + " has " + ans + " births ranked higher.");
    }
}
