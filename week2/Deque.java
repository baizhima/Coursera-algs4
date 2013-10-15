import java.util.Iterator;
import java.util.NoSuchElementException;
public class Deque<Item> implements Iterable<Item> {
    private int N;
    private Node<Item> first;
    private Node<Item> last;
    
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
    }
    
    public Deque() {
        first = null;
        last = null;
        N = 0;
    }
    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException("null item");
        if (N == 0) {
            last = new Node<Item>();
            first = last;
            last.item = item;
            first.item = last.item;
            N++;
        }
        else {
            Node<Item> oldfirst = first;
            first = new Node<Item>();
            first.item = item;
            first.next = oldfirst;
            oldfirst.prev = first;
            N++;
        }
    }
    public void addLast(Item item) {
        if (N == 0)
            addFirst(item);
        else {
           if (item == null) throw new NullPointerException("null item");
           Node<Item> oldlast = last;
            last = new Node<Item>();
            last.item = item;
            last.prev = oldlast;
            oldlast.next = last;
            N++;
        }
        
    }
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item removedItem = first.item;
        if (N == 1) {           
            first = null;
            last = null;
            N--;
        }
        else {
            Node<Item> newfirst = first.next;
            newfirst.prev = null;
            first = newfirst;
            N--;    
        }
        return removedItem;
        
    
    }
    public Item removeLast() { 
        if (isEmpty()) throw new NoSuchElementException();
        
        if (N == 1) 
            return removeFirst();
        else {
            Item removedItem = last.item;
            Node<Item> newlast = last.prev;
            newlast.next = null;
            last = newlast;
            N--;
            return removedItem;
        }
    }        
        
        
    
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    
    
    
    private class ListIterator implements Iterator<Item> {
        private Node<Item> current = first;

        public boolean hasNext()  { return current != null;   }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
    
    public static void main(String[] args) {
        
        
    }
}
        