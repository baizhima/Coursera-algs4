import java.util.Iterator;
import java.util.NoSuchElementException;
//import java.util.NullPointerException;
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;            // queue elements
    private int N = 0;           // number of elements on queue
    private int first = 0;       // index of first element of queue
    private int last  = 0;       // index of next available slot
    public RandomizedQueue()
    {
        q = (Item[]) new Object[2];
    }
    
    public boolean isEmpty() { return N == 0;    }
    public int size()        { return N;         }
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        if (N == q.length) resize(2 * q.length);
        q[N++] = item;
    }
        
    public Item dequeue() {
        if (N == 0) throw new NoSuchElementException();
        int idx;
        do {
            idx = StdRandom.uniform(0, q.length);
        } while (q[idx] == null);
        Item deleteItem = q[idx];
        q[idx] = null;
        N--;
        if (N > 0 && N == q.length/4) resize(q.length/2);
        return deleteItem;
    }
    
    public Item sample() {
        if (N == 0)
            throw new NoSuchElementException();
        int idx;
        do {
            idx = StdRandom.uniform(0, q.length);
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
        private int[] num = new int[q.length];
        
        private void initiation() {
            for (int j = 0; j < q.length; j++)
                num[j] = j;
            StdRandom.shuffle(num);
            initiate = true;
        }
        //private int[] num = new int[N];
        
        //StdRandom.shuffle(num);
        public boolean hasNext()  { return i < q.length && N > 0;           }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!initiate) 
                initiation();
            if (!hasNext()) throw new NoSuchElementException();
            Item item;
            int k = i;
            while (true) {
                item = q[num[k]];
                if (item == null)
                    k++;
                else
                    break;
            }
            i = k+1;
            return item;
        }
    }
    
    public static void main(String[] args) {
        //Iterator<Item> i = new RandomizedQueue.iterator();
        RandomizedQueue<Integer> q = new RandomizedQueue();
        q.enqueue(Integer.valueOf(5));
        q.enqueue(Integer.valueOf(15));
        q.enqueue(Integer.valueOf(54));
        q.enqueue(Integer.valueOf(521));
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        StdOut.println(q.size());
        Iterator<Integer> i = q.iterator();
        for (Integer s :q)
            StdOut.println(s);
        
    }
    
}
    