
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene (String dna) {
        // Find index of 'ATG'
        int startIndex = dna.indexOf("ATG");
        // Find index of 'TAA' found after startIndex
        int endIndex = dna.indexOf("TAA", startIndex+3);
        // If 'ATG' or 'TAA' aren't found, return empty string
        if ((startIndex == -1) || (endIndex == -1)) {
            return "";
        }
        // Extract substring
        String gene = dna.substring(startIndex, endIndex+3);
        // Only return 'gene' if length is a multiple of 3, o/w empty string
        if (gene.length() % 3 == 0) {
            return gene;
        }
        return "";
    }
    
    public void testSimpleGene() {
        String dna = "";
        String gene = "";
        
        // Test 1
        dna = "ACATGGCATCATAAGCGG";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        // Test 2
        dna = "CCATGACGACAGGGAAC";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        // Test 3
        dna = "ATAATCATCA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        // Test 4
        dna = "CATATACCCGGATCGG";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        // Test 5
        dna = "ATGGCAATTCATAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
    }
}
