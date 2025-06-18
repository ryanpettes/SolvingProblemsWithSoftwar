import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
/**
 * This is a program to find the coldest day of the year and other interesting facts 
 * about the temperature and humidity in a day.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NCWeather {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestSoFar = null;
        // Iterate thru all records in file
        for (CSVRecord curr : parser) {
            // If first record, initialize 'coldestSoFar'
            if (coldestSoFar == null) {
                coldestSoFar = curr;
            }
            double currTemp = Double.parseDouble(curr.get("TemperatureF"));
            double coldest = Double.parseDouble(coldestSoFar.get("TemperatureF"));
            // If new valid, coldest temp has been found, update 'coldestSoFar'
            if ((currTemp < coldest) && (currTemp != -9999)) {
                coldestSoFar = curr;
            }
        }
        return coldestSoFar;
    }
    
    public String fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        String coldestFileSoFar = "";
        double coldestSoFar = 9999;
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord record = coldestHourInFile(parser);
            if ((Double.parseDouble(record.get("TemperatureF")) < coldestSoFar)
                && (Double.parseDouble(record.get("TemperatureF")) != -9999)) {
                coldestSoFar = Double.parseDouble(record.get("TemperatureF"));
                coldestFileSoFar = f.getName();
            }
        }
        return coldestFileSoFar;
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord answer = null;
        double lowestSoFar = 9999;
        
        for (CSVRecord record : parser) {
            if (answer == null) {
                answer = record;
            }
            if (!record.get("Humidity").equals("N/A")) {
                double currLow = Double.parseDouble(record.get("Humidity"));
                if (lowestSoFar > currLow) {
                    lowestSoFar = currLow;
                    answer = record;
                }
            }
        }
        
        return answer;
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord ans = null;
        double lowest = 9999;
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord csv = lowestHumidityInFile(fr.getCSVParser());
            if (ans == null) {
                ans = csv;
                lowest = Double.parseDouble(csv.get("Humidity"));
            }
            double currLow = Double.parseDouble(csv.get("Humidity"));
            if (currLow < lowest) {
                lowest = currLow;
                ans = csv;
            }
        }
        return ans;
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        double total = 0.0;
        int count = 0;
        
        for (CSVRecord rec : parser) {
            double currTemp = Double.parseDouble(rec.get("TemperatureF"));
            if (currTemp != -9999) {
                total += currTemp;
                count++;
            }
        }
        
        return (total / count);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double total = 0.0;
        int count = 0;
        
        for (CSVRecord rec : parser) {
            double currTemp = Double.parseDouble(rec.get("TemperatureF"));
            if (!rec.get("Humidity").equals("N/A")) {
                double currHum = Double.parseDouble(rec.get("Humidity"));
                if ((currTemp != -9999) && (currHum >= value)) {
                    total += currTemp;
                    count++;
                }
            }
        }
        
        if (count == 0) { return 0.0; }
        return (total / count);
    }
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        CSVRecord record = coldestHourInFile(parser);
        System.out.println("Coldest temp: " + record.get("TemperatureF") + " @ " + record.get("DateUTC"));
    }
    
    public void testFileWithColdestTemperature() {
        String coldest = fileWithColdestTemperature();
        FileResource fr = new FileResource("nc_weather/2013/" + coldest);
        CSVRecord rec = coldestHourInFile(fr.getCSVParser());
        
        System.out.println("Coldest day was in file " + coldest);
        System.out.println("Coldest temperature on that day was " + rec.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        for (CSVRecord curr : fr.getCSVParser()) {
            System.out.println(curr.get("DateUTC") + " " + curr.get("TemperatureF"));
        }
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        
        System.out.println("Lowest humidity = " + csv.get("Humidity") + " @ " + csv.get("DateUTC"));
    }
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest humidity was " + csv.get("Humidity") + " @ " + csv.get("DateUTC"));
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        double ans = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is " + ans);
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        double ans = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if (ans == 0.0) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average temperature in file is " + ans);
        }
    }
}
