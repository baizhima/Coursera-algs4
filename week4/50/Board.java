import java.util.Arrays;
import java.util.ArrayList;

public class Board {
    private char[][] tiles;
    private int N;
    
    
    public Board(int[][] blocks) {
        N = blocks.length;
        tiles = new char[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)            
            tiles[i][j] = (char) (blocks[i][j] + 48); // from 0 to '0', etc  
    }
    public int dimension() { return N; }
    public int hamming() { 
        int count = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
            //StdOut.println("i = " + String.valueOf(i));
            //StdOut.println("j = " + String.valueOf(j));
            if (tiles[i][j] == '0')
                continue;
            else if (tiles[i][j] != (char) (N * i + j + 1 + 48))
                count += 1;
            //StdOut.println("after count = " + String.valueOf(count));
        }
        
        return count; }
    public int manhattan() {
        int count = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {               
                int num = (int) tiles[i][j] - 48;
                int iCorrect, jCorrect;                
                if (num == 0) continue;             
                iCorrect = (num - 1) / N;
                jCorrect = num - N * iCorrect - 1;                              
                count += Math.abs(iCorrect - i);  // vertical distance
                count += Math.abs(jCorrect - j);  // horizontal distance        
                }
        
        return count;
        }
        
    
    public boolean isGoal() {
        char[][] a = new char[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
            if (i == N - 1 && j == N - 1)
                a[i][j] = '0';               
            else                
                a[i][j] = (char) (N * i + j + 1 + 48);
        }
        
        return Arrays.deepEquals(a, tiles);
    }
    public Board twin() {
        int[][] blocks = new int[N][N];
        int zeroRow = -1;
        for (int i = 0; i < N; i++) 
            for (int j = 0; j < N; j++) {
                blocks[i][j] = (int) tiles[i][j] - 48;
                if (blocks[i][j] == 0)
                    zeroRow = i;
        }
        int changeRow = 0;
        if (zeroRow == 0)
            changeRow = 1;
        int temp = blocks[changeRow][0];
        blocks[changeRow][0] = blocks[changeRow][1];
        blocks[changeRow][1] = temp;    
        return new Board(blocks);
    }
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        return (Arrays.deepEquals(this.tiles, that.tiles));
    }
    
    public Iterable<Board> neighbors() {
    ArrayList<Board> boards = new ArrayList<Board>();
    final int[][] blocksOriginal = new int[N][N];
    int zeroRow = -1;
    int zeroCol = -1;
        for (int i = 0; i < N; i++) 
            for (int j = 0; j < N; j++) {
                blocksOriginal[i][j] = (int) tiles[i][j] - 48;
                if (blocksOriginal[i][j] == 0) {
                    zeroRow = i;
                    zeroCol = j;
                }
        }
    if (zeroRow == -1 || zeroCol == -1) StdOut.println("error finding 0");
    
    if (zeroRow > 0)
    {
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++) 
            for (int j = 0; j < N; j++) {
            blocks[i][j] = blocksOriginal[i][j];
        }
        int temp = blocks[zeroRow][zeroCol];
        blocks[zeroRow][zeroCol] = blocks[zeroRow - 1][zeroCol];
        blocks[zeroRow - 1][zeroCol] = temp;
        boards.add(new Board(blocks));
    }
    
    if (zeroRow < N - 1)
    {
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++) 
            for (int j = 0; j < N; j++) {
            blocks[i][j] = blocksOriginal[i][j];
        }
        int temp = blocks[zeroRow][zeroCol];
        blocks[zeroRow][zeroCol] = blocks[zeroRow + 1][zeroCol];
        blocks[zeroRow + 1][zeroCol] = temp;
        boards.add(new Board(blocks));
    }
    
    if (zeroCol > 0)
    {
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++) 
            for (int j = 0; j < N; j++) {
            blocks[i][j] = blocksOriginal[i][j];
        }
        int temp = blocks[zeroRow][zeroCol];
        blocks[zeroRow][zeroCol] = blocks[zeroRow][zeroCol - 1];
        blocks[zeroRow][zeroCol - 1] = temp;
        boards.add(new Board(blocks));
    }
    
    if (zeroCol < N - 1)
    {
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++) 
            for (int j = 0; j < N; j++) {
            blocks[i][j] = blocksOriginal[i][j];
        }
        int temp = blocks[zeroRow][zeroCol];
        blocks[zeroRow][zeroCol] = blocks[zeroRow][zeroCol + 1];
        blocks[zeroRow][zeroCol + 1] = temp;
        boards.add(new Board(blocks));
    }
    return boards;
    }
    
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", (int) tiles[i][j] - 48));
            }
            s.append("\n");
        }
        return s.toString();
    }
    
    public static void main(String[] args)
    {
        //int[][] g = {{1, 2, 3}, {4, 5, 6}, {8, 7, 0}};
        int[][] g = {{1, 2}, {3, 0}};
        Board b = new Board(g);
        
        //Board twin = b.twin();
        /*
        StdOut.println("hamming = " + String.valueOf(b.hamming()));
        StdOut.println("manhattan = " + String.valueOf(b.manhattan()));
        for (Board board: b.neighbors())
            board.toString();*/
        StdOut.println(b.isGoal());
    }
}
        
        
        