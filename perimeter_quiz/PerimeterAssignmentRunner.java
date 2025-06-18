import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Initialize running total to 0
        int numPoints = 0;
        // For each point in the shape, add 1 to total count
        for (Point currPt : s.getPoints()) {
            numPoints++;
        }
        // numPoints is the answer
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        double totalSideLen = getPerimeter(s);
        int numSides = getNumPoints(s);
        // Result will be implicitly cast to a double
        return (totalSideLen / numSides);
    }

    public double getLargestSide(Shape s) {
        // Initialize largest side to length of 0.0
        double largestSide = 0.0;
        // Get last point in the shape
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            // Compute current side's length
            double currSideLen = prevPt.distance(currPt);
            // Update longestSide if new largest has been found
            if (currSideLen > largestSide) {
                largestSide = currSideLen;
            }
            prevPt = currPt;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Initialize largestX to 0.0
        double largestX = 0.0;
        // Iterate thru all points in shape
        for (Point currPt : s.getPoints()) {
            // Get current point's X value
            double currX = currPt.getX();
            // If current X is largest so far, update accordingly
            if (currX > largestX) {
                largestX = currX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = 0.0;
        // Iterate thru selected files
        for (File f : dr.selectedFiles()) {
            // For each file, create a FileResource and Shape
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            // Compute perim of current shape
            double currPerim = getPerimeter(s);
            // Update largestPerim if applicable
            if (currPerim > largestPerim) {
                largestPerim = currPerim;
            }
        }
        return largestPerim;
    }

    public String getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = 0.0;
        File temp = null;
        // Iterate thru selected files
        for (File f : dr.selectedFiles()) {
            // For each file, create a FileResource and Shape
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            // Compute perim of current shape
            double currPerim = getPerimeter(s);
            // Update largestPerim if applicable
            if (currPerim > largestPerim) {
                largestPerim = currPerim;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        // Calculate and output total number of points in the shape
        int numPoints = getNumPoints(s);
        System.out.println("number of points = " + numPoints);
        // Calculate and output average side length of shape
        double avgLen = getAverageLength(s);
        System.out.println("average side length = " + avgLen);
        // Calculate and output largest side length
        double largestSide = getLargestSide(s);
        System.out.println("largest side = " + largestSide);
        // Calculate and output largest X value
        double largestX = getLargestX(s);
        System.out.println("largest X-value = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        double largestPerim = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter = " + largestPerim);
    }

    public void testFileWithLargestPerimeter() {
        String largestFile = getFileWithLargestPerimeter();
        System.out.println("name of file w/ largest perimeter is: " + largestFile);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
