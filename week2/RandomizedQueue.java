import java.util.Iterator;
import java.util.NoSuchElementException;
//import java.util.NullPointerException;
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;            // queue elements
    private int N = 0;           // number of elements on queue
    private int maxSize = 0;
    //private int[] index = new int[N];
    //private int currentIndex = 0;       // index of first element of queue
    //private int last  = 0;       // index of next available slot
    public RandomizedQueue()
    {
        q = (Item[]) new Object[2];
        
    }
    public int getMaxSize() { return maxSize; }
    public boolean isEmpty() { return N == 0;    }
    public int size()        { return N;         }
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        if (N == q.length) resize(2 * q.length);
        q[N++] = item;
        //maxSize++;
    }
        
    public Item dequeue() {
        if (N == 0) throw new NoSuchElementException();
        int idx;
        do {
            idx = StdRandom.uniform(0,N);
        } while (q[idx] == null);
        Item[] deleteItem = (Item[]) new Object[1];
        deleteItem[0] = q[idx];
        q[idx] = null;
        N--;
        if (N > 0 && N == q.length/4) resize(q.length/2);
        return deleteItem[0];
    }
    
    public Item sample() {
        if (N == 0)
            throw new NoSuchElementException();
        int idx;
        do {
            idx = StdRandom.uniform(0, N);
        } while (q[idx] == null); 
        return q[idx];
    }
    
    private void resize(int capacity) {
        assert capacity >= N;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = q[i];
        }
        q = temp;
    }    
        
    public Iterator<Item> iterator() { return new ArrayIterator(); }

    // an iterator, doesn't implement remove() since it's optional
    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;
        private boolean initiate = false;
        private int[] num = new int[N];
        
        private void initiation() {
            for (int j = 0; j < N; j++)
                num[j] = j;
            StdRandom.shuffle(num);
            initiate = true;
        }
        //private int[] num = new int[N];
        
        //StdRandom.shuffle(num);
        public boolean hasNext()  { return i < N;           }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!initiate) 
                initiation();
            if (!hasNext()) throw new NoSuchElementException();
            for (int k = 0; k < q.length; k++) {
                StdOut.println("num["+k+"] = "+num[k]+", q[num["+k+"]] = "+q[num[k]]);
            }
            if (N == 4)StdOut.println("!! num[4] = "+num[4]+", q[num[4]] = "+q[num[4]]);
            StdOut.println("before while, i="+i);
            Item item;
            int count = 0;
            do {
                item = q[num[i++]];
                count += 1;
            } while (item == null);
            if (count > 1)
                i--;
            StdOut.println("after while, i="+i+", item = "+item);
            //i++;
            return item;
        }
    }
    
    public static void main(String[] args) {
        //Iterator<Item> i = new RandomizedQueue.iterator();
        /*RandomizedQueue<Integer> q = new RandomizedQueue();
        q.enqueue(Integer.valueOf(5));
        q.enqueue(Integer.valueOf(15));
        q.enqueue(Integer.valueOf(44));
        q.enqueue(Integer.valueOf(521));
        
        for (Integer s :q)
            StdOut.println(s);
        
        StdOut.println("dequeued element = "+q.dequeue());
        for (Integer s :q)
            StdOut.println(s);
        q.enqueue(Integer.valueOf(77));
        StdOut.println("newsize = "+q.size());
        for (Integer s :q)
            StdOut.println(s);
        /*
        q.enqueue(Integer.valueOf(564));
        StdOut.println("maxsize="+q.getMaxSize());
        /*StdOut.println(q.dequeue());
        StdOut.println();
        Iterator<Integer> i = q.iterator();
        */
        //StdOut.println(q.sample());
       /* StdOut.println();
        for (Integer s :q)
            StdOut.println(s);
        */
        int[] a = new int[5];
        StdOut.println(a.length);
    }
    
}
    