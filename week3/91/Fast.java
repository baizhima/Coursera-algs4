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
        //String[] lastStr = new String[N*100];       
        //int lastStrCount = 0;
        
        Point[] startPoints = new Point[N*100];
        Point[] endPoints = new Point[N*100];
        double[] slopes = new double[N*100];
        int lineCount = 0;
        
        Arrays.sort(p);
        for (int i = 0; i < N; i++)
            p[i].draw();
        //for (int i = 0; i < N; i++)
           //StdOut.println(p[i].toString());
       //StdOut.println("end");
        
        for (int i = 0; i < N; i++)
        {
            Point p0 = p[i]; // invoking element
            Point[] points = new Point[N - 1 - i]; // without invoking point
            
            for (int j = 0; j < N - 1 - i; j++)
                points[j] = p[i+j+1];
            
            Arrays.sort(points, p0.SLOPE_ORDER);
           //StdOut.println("p0:"+p0.toString());
           //for (int j = 0; j < N - 1 - i; j++)
               //StdOut.println(points[j].toString());
            
            
            for (int j = 0; j < N - 3 - i; j++)
            {
                double k0 = p0.slopeTo(points[j]);
                if (k0 == p0.slopeTo(points[j + 1])
                   && k0 == p0.slopeTo(points[j + 2]))
            {
                int count = 3;
                
                while (1 == 1)
                {
                   //StdOut.println("count="+String.valueOf(count));
                   //StdOut.println("j="+String.valueOf(j));
                    if (count >= points.length)
                        break;
                    if (j+count < points.length 
                            && p0.slopeTo(points[j + count]) 
                            == k0)
                        count++;
                    else
                        break;
                }
                
                Point[] pts = new Point[count + 1];               
                //StdOut.println("i="+String.valueOf(i)+" p0 =" + p0.toString()
                                   //+", j="+String.valueOf(j)
                                   //+", count="+String.valueOf(count));
                for (int k = 0; k < count; k++)
                    pts[k] = points[j + k];
                pts[count] = p0;
                Arrays.sort(pts);
                String outputStr = "";
                for (int k = 0; k < count; k++)
                {
                    outputStr = outputStr + pts[k].toString();
                    outputStr = outputStr + " -> ";
                }
                outputStr = outputStr + pts[count].toString();
                j += count - 1;
                Point currentStartPoint = pts[0];
                Point currentEndPoint = pts[count];
                double currentSlope = pts[0].slopeTo(pts[count]);
                boolean shownBefore = false;
                for (int k = 0; k < lineCount; k++)
                { 
                    if (currentSlope == slopes[k]                             
                            && currentStartPoint.compareTo(startPoints[k]) >= 0
                            && currentEndPoint.compareTo(endPoints[k]) <= 0)
                    {                        
                        
                        shownBefore = true;
                        if (currentSlope == Double.POSITIVE_INFINITY 
                                && currentStartPoint.slopeTo(startPoints[k]) 
                                != Double.POSITIVE_INFINITY)                              
                            shownBefore = false;
                        break;
                    }
                }
                
                if (shownBefore)
                {
                    continue;
                }
                else {
                StdOut.println(outputStr);
                startPoints[lineCount] = currentStartPoint;
                endPoints[lineCount] = currentEndPoint;
                slopes[lineCount++] = currentSlope;
                //lastStr[lastStrCount++] = outputStr;
               /* if (lastStrCount == lastStr.length)
                {
                    String[] temp = new String[lastStrCount * 2];
                    for (int t = 0; t < lastStrCount; t++)
                        temp[t] = lastStr[t];
                    lastStr = temp;
                }*/
                pts[0].drawTo(pts[count]);
                }
                StdDraw.show(0);
                

            }
}
        }
    }
}
