
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene (String dna, String startCodon, String stopCodon) {
        // Determine if string is ALL UPPERCASE or all lowercase
        if (dna == dna.toUpperCase()) {
            startCodon = startCodon.toUpperCase();
            stopCodon = stopCodon.toUpperCase();
        } else if (dna == dna.toLowerCase()) {
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        } else {
            // Return empty string if contains mixed cases
            return "";
        }
        // Find index of startCodon
        int startIndex = dna.indexOf(startCodon);
        // Find index of stopCodon found after startIndex
        int endIndex = dna.indexOf(stopCodon, startIndex+3);
        // If startCodon or stopCodon aren't found, return empty string
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
        dna = "acatggcatcataagcgg";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);
        
        // Test 2
        dna = "CCATGACGACAGGGAAC";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);
        
        // Test 3
        dna = "ATAATCATCA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);
        
        // Test 4
        dna = "CATATACCCGGATCGG";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);
        
        // Test 5
        dna = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);
    }
}
