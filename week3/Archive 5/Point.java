/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
        //SLOPE_ORDER =null;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        if ((that.y == y) && (that.x == x))
            return Double.NEGATIVE_INFINITY;
        else if (that.x == x)
            return Double.POSITIVE_INFINITY;
        else if (that.y ==  y)
            return 0;
        else
        {
            return (that.y - this.y)*1.0/(that.x - this.x);
        }
        
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if (that == null)
            throw new NullPointerException();
        if ((y < that.y) || (y == that.y && x < that.x))
            return -1;
        else if ((y == that.y) && (x == that.x))
            return 0;
        else
            return 1;
        
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
    
    private  class SlopeOrder implements Comparator<Point>
    {
        public int compare(Point p1, Point p2)
        { 
            if (p1 == null || p2 == null)
                throw new NullPointerException();
            double k1, k2;
            // initiate k1
            if ((p1.y == y) && (p1.x == x))
                k1 = Double.NEGATIVE_INFINITY;
            else if (p1.x == x)
                k1 =  Double.POSITIVE_INFINITY;
            else if (p1.y == y)
                k1 =  0;
            else
                k1 =  (p1.y - y) * 1.0 / (p1.x - x);
            // initiate k2
            if ((p2.y == y) && (p2.x == x))
                k2 = Double.NEGATIVE_INFINITY;
            else if (p2.x == x)
                k2 = Double.POSITIVE_INFINITY;
            else if (p2.y == y)
                k2 =  0;
            else
                k2 =  (p2.y - y) * 1.0 / (p2.x - x);
           
            if (k1 < k2)
                return -1;
            else if (k1 > k2)
                return 1;
            else
                return 0;
            
        }
    }
    
    
    // unit test
    public static void main(String[] args) {
        Point p0 = new Point(20000, 21000);
        Point p1 = new Point(3000, 4000);
        Point p2 = new Point(6000, 7000);
        Point p3 = new Point(14000, 15000);
        Point p4 = new Point(10000, 0);
        Point p5 = new Point(0, 10000);
        Point p6 = new Point(3000, 7000);
        Point p7 = new Point(7000, 3000);
        StdOut.println(p0.slopeTo(p7));
    }
}