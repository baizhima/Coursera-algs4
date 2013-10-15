
public class Subset {
    
    public Subset() {
     int i = 0;
    }
    
    
    public static void main(String[] args)
    {
        int N = 0;
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while(!StdIn.isEmpty()) {
        String item = StdIn.readString();
        rq.enqueue(item);
        N = N + 1;
        }
        for (int i = 0; i < k; i++)
            StdOut.println(rq.dequeue());
        
    }
}