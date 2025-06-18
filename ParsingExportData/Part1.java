import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * FROM: Programming Exercise: Parsing Export Data
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //listExportersTwoProducts(parser, "cotton", "flowers");
        //System.out.println(numberOfExporters(parser, "cocoa"));
        //bigExporters(parser, "$999,999,999");
    }
    
    public String countryInfo(CSVParser parser, String country) {
        // Search for country with a given name
        for (CSVRecord record : parser) {
            if (record.get("Country").equals(country)) {
                // Return a string of info about the country
                return (record.get("Country") + ": " + record.get("Exports") + ": " + record.get("Value (dollars)"));
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        // Iterate thru all countries in parser
        for (CSVRecord record : parser) {
            // If the country exports both parameter items, print it to console
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        // Iterate thru all countries
        for (CSVRecord record : parser) {
            // If country exports the item of interest, increment count
            if (record.get("Exports").contains(exportItem)) {
                count++;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            // If country has exports greater than 'amount', print name of country and country's value
            if (amount.length() < record.get("Value (dollars)").length()) {
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    }
}
