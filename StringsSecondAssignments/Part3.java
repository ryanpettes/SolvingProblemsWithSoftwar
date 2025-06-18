
/**
 * A program to count how many genes are in a strand of DNA.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public int findStopCodon (String dna, int startIndex, String stopCodon) {
        int currIndex = startIndex;
        while (true) {
            // Look for first occurrence of 'stopCodon' starting from 'startIndex'
            int stopIndex = dna.indexOf(stopCodon, currIndex);
            // If no occurrence is found, exit the loop
            if (stopIndex == -1) { break; }
            // Check if 'stopIndex' is a multiple of 3 away from 'startIndex'
            if ((stopIndex - startIndex) % 3 == 0) {
                // If so, return the index of 'stopCodon'
                return stopIndex;
            } else {
                // If not, update 'currIndex' to continue searching
                currIndex = stopIndex + 1;
            }
        }
        // Return length of string if no occurrence found
        return dna.length();
    }
    
    public String findGene (String dna) {
        // Look for start codon, return if start codon isn't found
        int startCodon = dna.indexOf("ATG");
        if (startCodon == -1) { return ""; }
        
        // Look for occurrence of "TAA" stop codon
        int taaIndex = findStopCodon(dna, startCodon, "TAA");
        
        // Look for occurrence of "TAG" stop codon
        int tagIndex = findStopCodon(dna, startCodon, "TAG");
        
        // Look for occurrence of "TGA" stop codon
        int tgaIndex = findStopCodon(dna, startCodon, "TGA");
        
        // Find closest stop codon, if one exists
        int buffer = Math.min(taaIndex, tagIndex);
        int closestCodon = Math.min(buffer, tgaIndex);
        
        // Return gene formed by closest stop codon, if one exists
        if (closestCodon == dna.length()) { return ""; }
        else { return dna.substring(startCodon, closestCodon+3); }
    }
    
    public void printAllGenes(String dna) {
        while (true) {
            String gene = findGene(dna);
            if (gene == "") { break; }
            System.out.println(gene);
            dna = dna.substring(dna.indexOf(gene) + gene.length());
        }
    }
    
    public int countGenes(String dna) {
        int count = 0;
        
        while (true) {
            String gene = findGene(dna);
            if (gene == "") { break; }
            count++;
            dna = dna.substring(dna.indexOf(gene) + gene.length());
        }
        
        return count;
    }
    
    public void testCountGenes() {
        String test1 = "ATGTAAGATGCCCTAGT";
        int result1 = countGenes(test1);
        // Should return 2
        System.out.println("Original string: " + test1);
        System.out.println("# of genes found: " + result1);
        
        String test2 = "CGGATGCACCGCTGCTAGATGCCAGCA";
        int result2 = countGenes(test2);
        // Should return 1
        System.out.println("Original string: " + test2);
        System.out.println("# of genes found: " + result2);
        
        String test3 = "CCAATGCAGTACATACCA";
        int result3 = countGenes(test3);
        // Should return 0
        System.out.println("Original string: " + test3);
        System.out.println("# of genes found: " + result3);
        
        String test4 = "ATGATGATGATGTAGATGATGATGTGAATGTAA";
        int result4 = countGenes(test4);
        // Should return 3
        System.out.println("Original string: " + test4);
        System.out.println("# of genes found: " + result4);
    }
}
