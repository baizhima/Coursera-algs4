
public class PercolationStats {
    private double[] times;
    
    private int T;
    private int N;
    
    public PercolationStats(int nn, int tt) {
        if (nn <= 0 || tt <= 0)
            throw new IllegalArgumentException("N or T is illegal.");
        N = nn;
        T = tt;
        times = new double[T]; 
        for (int k = 0; k < T; k++) { // T times experiment
            Percolation perc;
            int count = 0;
            perc = new Percolation(N);
            do {
                int i, j;
                do
                {
                    i = StdRandom.uniform(1, N + 1);
                    j = StdRandom.uniform(1, N + 1);
                } while (perc.isOpen(i, j));
                perc.open(i, j);
                count++;
            //StdOut.println(i+" "+j);
               /* if (perc.percolates())
                    StdOut.println("percolates");
                else
                    StdOut.println("not percolates");    */
            } while (!perc.percolates());
            //StdOut.println("hi");
            times[k] = count / (N * 1.0 * N);  
            
        }
        
    }
    public double mean() { return StdStats.mean(times); }
    public double stddev() { return StdStats.stddev(times); }
    public double confidenceLo() { 
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }
    public double confidenceHi() { 
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }
    public static void main(String[] args) {
        //PercolationStats pstats2 = new PercolationStats(-1, 2);
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats pstats = new PercolationStats(N, T);
        StdOut.println("mean                    = " + pstats.mean());
        StdOut.println("stddev                  = " + pstats.stddev());
        StdOut.println("95% confidence interval = "  
                      + pstats.confidenceLo() + ", " + pstats.confidenceHi());
        return;
    }
}

        
    