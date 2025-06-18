import edu.duke.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
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
    
    public void testFindGene() {
        // Test 1         ˇ  ˇ  ˇ  ˇ  ˇ  ˇ
        String test1 = "ACATGCGATAGATAATATAACCA";
        System.out.println("Original strand: " + test1);
        String gene1 = findGene(test1);
        // Should return ATGCGATAG
        System.out.println("Gene found is: " + gene1);
        
        // Test 2       ˇ  ˇ  ˇ  ˇ  ˇ  ˇ
        String test2 = "ATGCAGACCATGGTATAG";
        System.out.println("Original strand: " + test2);
        String gene2 = findGene(test2);
        // Should return ATGCAGACCATGGTATAG
        System.out.println("Gene found is: " + gene2);
        
        // Test 3       ˇ  ˇ  ˇ  ˇ  ˇ
        String test3 = "ATGATAAGCTAATAG";
        System.out.println("Original strand: " + test3);
        String gene3 = findGene(test3);
        // Should return ATGATAAGCTAA
        System.out.println("Gene found is: " + gene3);
        
        // Test 4
        String test4 = "AATAGGTAATAGTGAAATG";
        System.out.println("Original strand: " + test4);
        String gene4 = findGene(test4);
        // Should return ""
        System.out.println("Gene found is: " + gene4);
        
        // Test 5
        String test5 = "AGAATGATACGGCCATATGGG";
        System.out.println("Original strand: " + test5);
        String gene5 = findGene(test5);
        // Should return ""
        System.out.println("Gene found is: " + gene5);
    }
    
    public void testFindStopCodon() {
        // Test 1         ˇ  ˇ  ˇ  ˇ  ˇ  ˇ
        String test1 = "ACATGCGATAGATAATATAACCA";
        int stop1 = findStopCodon(test1, 2, "TAA");
        // Should return 17
        System.out.println("Stop codon found at index " + stop1);
        
        // Test 2       ˇ  ˇ  ˇ  ˇ  ˇ  ˇ
        String test2 = "ATGCAGACCATGGTATAG";
        int stop2 = findStopCodon(test2, 0, "TAG");
        // Should return 15
        System.out.println("Stop codon found at index " + stop2);
        
        // Test 3       ˇ  ˇ  ˇ  ˇ  ˇ
        String test3 = "ATGATAAGCTAATAG";
        int stop3 = findStopCodon(test3, 0, "TAA");
        // Should return 12
        System.out.println("Stop codon found at index " + stop3);
        
        // Test 4
        String test4 = "";
        
        // Test 5
        String test5 = "";
    }
    
    public void printAllGenes(String dna) {
        while (true) {
            String gene = findGene(dna);
            if (gene == "") { break; }
            System.out.println(gene);
            dna = dna.substring(dna.indexOf(gene) + gene.length());
        }
    }
    
    public StorageResource getAllGenes(String dna) {
        StorageResource genes = new StorageResource();
        
        while (true) {
            String gene = findGene(dna);
            if (gene == "") { break; }
            genes.add(gene);
            dna = dna.substring(dna.indexOf(gene) + gene.length());
        }
        
        return genes;
    }
    
    public double cgRatio(String dna) {
        int count = 0;
        
        // Count number of c's and g's in dna
        for (int i = 0; i < dna.length(); i++) {
            if (dna.charAt(i) == 'C' || dna.charAt(i) == 'G') {
                count++;
            }
        }
        return (float)count / dna.length();
    }
    
    public int countCTG(String dna) {
        int count = 0;
        
        int index = dna.indexOf("CTG");
        while (index != -1) {
            count++;
            index = dna.indexOf("CTG", index+1);
        }
        
        return count;
    }
    
    public void processGenes(StorageResource sr) {
        int numLongGenes = 0;
        int numHighRatio = 0;
        int longest = 0;
        int count = 0;
        int total = 0;
        
        for (String s : sr.data()) {
            count++;
            total += countCTG(s);
            if (s.length() > 60) {
                System.out.println(s);
                numLongGenes++;
            }
            if (cgRatio(s) > 0.35) {
                System.out.println(s);
                numHighRatio++;
            }
            if (s.length() > longest) {
                longest = s.length();
            }
        }
        
        System.out.println("count = " + count);
        System.out.println("longest = " + longest);
        System.out.println("over 60 = " + numLongGenes);
        System.out.println("high ratio = " + numHighRatio);
        System.out.println("total CTG count = " + total);
    }
    
    public void testProcessGenes() {
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        
        StorageResource sr = getAllGenes(dna);
        processGenes(sr);
    }
}
