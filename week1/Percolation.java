
public class Percolation {
    private WeightedQuickUnionUF grids;
    private WeightedQuickUnionUF grids2;
    private boolean[] gridIsOpen;
    //private boolean[] gridIsOpen2;
    private int N;
    
    public Percolation(int nn) {
        N = nn;
        grids = new WeightedQuickUnionUF(N * N + 2);
        gridIsOpen = new boolean[N * N];
        for (int i = 1; i <= N; i++)
            grids.union(toindex(N, i), N * N + 1); 
        
        grids2 = new WeightedQuickUnionUF(N * N + 1);
        
    }   // create N-by-N grid, with all sites blocked
    public void open(int i, int j) {
        checksafety(i, j);
        gridIsOpen[toindex(i, j)] = true;
        if (i == 1)
                grids.union(toindex(i, j), N * N); // connect top grid
        if (j > 1 && isOpen(i, j - 1))
                grids.union(toindex(i, j), toindex(i, j - 1)); // to left
        if (j < N && isOpen(i, j + 1))
                grids.union(toindex(i, j), toindex(i, j + 1)); // to right
        if (i > 1  && isOpen(i - 1, j))
                grids.union(toindex(i, j), toindex(i - 1, j)); // to top
        if (i < N  && isOpen(i + 1, j))
                grids.union(toindex(i, j), toindex(i + 1, j)); // to bot
        
        if (i == 1)
                grids2.union(toindex(i, j), N * N); // connect top grid
        if (j > 1 && isOpen(i, j - 1))
                grids2.union(toindex(i, j), toindex(i, j - 1)); // to left
        if (j < N && isOpen(i, j + 1))
                grids2.union(toindex(i, j), toindex(i, j + 1)); // to right
        if (i > 1  && isOpen(i - 1, j))
                grids2.union(toindex(i, j), toindex(i - 1, j)); // to top
        if (i < N  && isOpen(i + 1, j))
                grids2.union(toindex(i, j), toindex(i + 1, j)); // to bot
        
    }  // open site (row i , column j) if it is not already
    public boolean isOpen(int i, int j) {
        checksafety(i, j);
        return gridIsOpen[toindex(i, j)];
        //return false;
    }
    public boolean isFull(int i, int j) {
        checksafety(i, j);
        return isOpen(i, j) && grids2.connected(toindex(i, j), N * N);
    }
    public boolean percolates() {
        return grids.connected(N * N, N * N + 1);
    }
    
    private void checksafety(int i, int j) {
        if (i <= 0 || i > N) 
            throw new IndexOutOfBoundsException("row index i out of bounds");
        if (j <= 0 || j > N) 
            throw new IndexOutOfBoundsException("column index j out of bounds");
    }
    private int toindex(int row, int col)
    {
        return (row - 1) * N + (col - 1);
    }
}

    
        