
import java.util.ArrayList;
import java.util.Collections;
public class Solver {
    private Board board;
    private Board twin;
    private int originalSolved = -1;
    
    private int moveCount;
    private Node lastNode;
    private ArrayList<Board> boardArray;
    private Node answerNode;
    // 2 for twin
    private int moveCount2;
    private Node lastNode2;
    private ArrayList<Board> boardArray2;
    
    public Solver(Board initial) {
    board = initial;
    twin = initial.twin();
    boardArray = new ArrayList<Board>();
    MinPQ<Node> pq = new MinPQ<Node>();
    moveCount = 0;
    Node firstNode = new Node(initial, moveCount, null);
    pq.insert(firstNode);
    
    boardArray2 = new ArrayList<Board>();
    MinPQ<Node> pq2 = new MinPQ<Node>();
    moveCount2 = 0;
    Node firstNode2 = new Node(twin, moveCount2, null);
    pq2.insert(firstNode2);
    /*******************BST data structure**********/
    BST<String, Integer> gameTree = new BST<String, Integer>();
    BST<String, Integer> gameTree2 = new BST<String, Integer>();
    /***********************************************/
    gameTree.put(initial.toString(), initial.manhattan() + moveCount);
    gameTree2.put(twin.toString(), twin.manhattan() + moveCount2);
    
    //while (true) {
    for (int i = 0; i < 2000000; i++)    {
    // for original tiles 
    if (pq.size() == 0)
    {
        originalSolved = 0;
        moveCount = -1; //
        break;
    }
    Node current = pq.delMin(); // select the node with least priority(min)
    Board currentBoard = current.getBoard(); // get the corresponding board   
    if (currentBoard.isGoal()) {
        originalSolved = 1;
        answerNode = current;
        moveCount = current.getMoves(); //
        break;
    }
        
    if (current != null) {
        moveCount =  current.getMoves() + 1;
        for (Board b: currentBoard.neighbors()) {
        Node newNode = new Node(b, moveCount, current);
        String bString = b.toString();
        int newPriority = newNode.getPriority();
        if (lastNode != null) {
            if (!b.equals(lastNode.getBoard())){
            
                if (!gameTree.contains(bString)) {   
                pq.insert(newNode);
                gameTree.put(bString, newPriority);
                }
            else {               
                if (newPriority < gameTree.get(bString)) {
                    gameTree.put(bString, newPriority);
                    pq.insert(newNode);
                }
            }
            }
        }
        else
        {
            //gameTree.put(bString, newPriority);
            pq.insert(newNode);
        }
        }
        lastNode = current;
        //StdOut.println("MinPQ size = " + String.valueOf(pq.size()) + "\n");
        //StdOut.println("all boards in priority queue:\n");
        //for (Node node : pq)
          //  StdOut.println(node.show());
    }
    else
        throw new NullPointerException("current Node is null");
    
    if (i % 16 == 0) {
    // for twin tiles    
    Node current2 = pq2.delMin(); // select the node with least priority(min)
    Board currentBoard2 = current2.getBoard(); // get the corresponding board
    //StdOut.println("currentBoard2 = \n"+currentBoard2.toString());
    if (currentBoard2.isGoal()) {
        originalSolved = 0;
        //answerNode = current;
        moveCount = -1; //
        break;
    }
        
    if (current2 != null) {
        //StdOut.println("current2 moveNum = " + String.valueOf(current2.getMoves()));
        moveCount2 =  current2.getMoves() + 1;
        for (Board b: currentBoard2.neighbors()) {
        Node newNode2 = new Node(b, moveCount2, current2);
        String bString2 = b.toString();
        int newPriority2 = newNode2.getPriority();
        if (lastNode2 != null) {
            if (!b.equals(lastNode2.getBoard())){
                if (!gameTree2.contains(bString2)) {   
                pq2.insert(newNode2);
                gameTree2.put(bString2, newPriority2);
                }
                else {               
                if (newPriority2 < gameTree2.get(bString2)) {
                    gameTree2.put(bString2, newPriority2);
                    pq2.insert(newNode2);
                }
            }
            
            }
        }
        else
        {
            //StdOut.println("(2)(b)we will add: \n");
            //StdOut.println(newNode2.show());
            pq2.insert(newNode2);
        }
        }
        lastNode2 = current2;
        //StdOut.println("MinPQ_2 size = " + String.valueOf(pq2.size()) + "\n");
        //StdOut.println("all boards in priority queue:\n");
        //for (Node node : pq2)
           // StdOut.println(node.show());
    }
    else
        throw new NullPointerException("current Node is null");
    
    }
    
    }
    
    if (originalSolved == 1) {
    for (Node current = answerNode; current.prev != null; current = current.prev)
        boardArray.add(current.getBoard());
    boardArray.add(initial);
    Collections.reverse(boardArray);
    //StdOut.println("Minimum number of moves = "+ moveCount + "\n");
        //for (Board b: solution())
            //StdOut.println(b.toString());
    }
    else if (originalSolved == 0) {
        //StdOut.println("No solution possible");
    }
    else
        StdOut.println("Haven't figured it out");
    
    }
    public boolean isSolvable() { return originalSolved == 1; }
    public int moves() { return moveCount; }
    public Iterable<Board> solution() {
        if (originalSolved == 1)
        return boardArray;
        else if (originalSolved == 0)
            return null;
        else {
            StdOut.println("error finding the correct in iterable.");
        return null;
        }
    }

    private class Node implements Comparable<Node> {
        private final String output;
        private final Board board;
        private final int priority;
        private final Node prev;
        private final int moves;
        private final int manDistance;
        
        
        public Node(Board b, int moves, Node lastNode) {
            board = b;
            manDistance = board.manhattan();
            priority =  manDistance + moves;
            output = board.toString();
            prev = lastNode;
            this.moves = moves;
        }
        
        public int compareTo(Node that) {
            if (this.priority < that.priority) return -1;
            else if (this.priority > that.priority) return +1;
            else return 0;
        }
        
        public String show() { 
            StringBuilder s = new StringBuilder();
            s.append("priority  = " + String.valueOf(priority) + "\n");
            s.append("moves     = " + String.valueOf(moves) + "\n");
            s.append("mahhattan = " + String.valueOf(manDistance) + "\n");
            s.append(board.toString());
            return s.toString(); 
        }
        
        public Board getBoard() { return board; }
        public int getMoves() { return moves; }
        public int getPriority() { return priority; }
    }

    
    public static void main(String[] args) {
    for (String filename : args) {

            // read in the board specified in the filename
            In in = new In(filename);
            int N = in.readInt();
            int[][] tiles = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    tiles[i][j] = in.readInt();
                }
            }

            // solve the slider puzzle
            Board initial = new Board(tiles);
            //StdOut.println("initial="+initial.toString());
            Solver solver = new Solver(initial);
    }
    }
}
        