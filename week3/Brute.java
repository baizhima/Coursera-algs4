import java.util.Arrays;
public class Brute {
    public static void main(String[] args)
    {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] p = new Point[N]; 
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            p[i] = new Point(x, y);     
        }
        Point p0 = new Point(0, 0);
        Arrays.sort(p);
        for (int i = 0; i < N; i++)
            p[i].draw();
        
        for (int i1 = 0; i1 < N - 3; i1++)            
            for (int i2 = i1 + 1; i2 < N - 2; i2++)               
                for (int i3 = i2 + 1; i3 < N -1; i3++)                   
                    for (int i4 = i3 + 1; i4 < N; i4++)
                    {
                        Point p1 = p[i1];
                        Point p2 = p[i2];
                        Point p3 = p[i3];
                        Point p4 = p[i4];
                        double k1, k2, k3;
                        k1 = p1.slopeTo(p2);
                        k2 = p1.slopeTo(p3);
                        k3 = p1.slopeTo(p4);
                        if (k1 == k2 && k1 == k3)
                        {
                            Point[] pts = {p1, p2, p3, p4};
                            Arrays.sort(pts);
                            StdOut.println(pts[0].toString() + " -> "  
                                         +  pts[1].toString() + " -> "  
                                         +  pts[2].toString() + " -> " 
                                         +  pts[3].toString());
                            pts[0].drawTo(pts[3]);
                            StdDraw.show(0);
                        }
                        
                    }
            
        
    }
}