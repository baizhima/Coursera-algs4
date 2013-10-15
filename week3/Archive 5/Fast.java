import java.util.Arrays;
public class Fast {
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
        
        Arrays.sort(p);
        for (int i = 0; i < N; i++)
            p[i].draw();
        //for (int i = 0; i < N; i++)
            //StdOut.println(p[i].toString());
        //StdOut.println("end");
        
        for (int i = 0; i < N; i++)
        {
            Point p0 = p[i]; // invoking element
            //StdOut.println("we choose "+p0.toString());
            Point[] points = new Point[N - 1 - i]; // without invoking point
            
            for (int j = 0; j < N - 1 - i; j++)
                points[j] = p[i+j+1];
            
            Arrays.sort(points,p0.SLOPE_ORDER);
            //StdOut.println("p0:"+p0.toString());
            //for (int j = 0; j < N - 1 - i; j++)
               //StdOut.println(points[j].toString());
            
            
            for (int j = 0; j < N - 3 - i; j++)
                if (p0.slopeTo(points[j]) == p0.slopeTo(points[j + 1])
                   && p0.slopeTo(points[j]) == p0.slopeTo(points[j + 2]))
            {
                int count = 3;
                while (1 == 1)
                {
                    //StdOut.println("count="+String.valueOf(count));
                    //StdOut.println("j="+String.valueOf(j));
                    if (count >= points.length)
                        break;
                    if (j + count < N 
                            && (p0.slopeTo(points[count]) 
                            == p0.slopeTo(points[j])))
                        count++;
                    else
                        break;
                };
                
                Point[] pts = new Point[count + 1];
                //StdOut.println("i="+String.valueOf(i)+" p0 =" + p0.toString()
                                   //+", j="+String.valueOf(j)
                                   //+", count="+String.valueOf(count));
                for (int k = 0; k < count; k++)
                    pts[k] = points[j + k];
                pts[count] = p0;
                Arrays.sort(pts);
                for (int k = 0; k < count; k++)
                {
                    StdOut.print(pts[k].toString() + " -> ");
                }
                StdOut.println(pts[count].toString());
                pts[0].drawTo(pts[count]);
                //StdOut.println();
                StdDraw.show(0);
                j += count - 1;

            }
        }
    }
}
