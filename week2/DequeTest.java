public class DequeTest {
    @org.junit.Test
    public void testIsEmpty() throws Exception {
        Deque<Integer> deque = new Deque<Integer>();
        assertTrue("empty deque", deque.isEmpty());
        deque.addFirst(1);
        assertTrue("empty deque", !deque.isEmpty());
        deque.removeLast();
        assertTrue("empty deque", deque.isEmpty());

        for (int i = 0; i < 10; i++) {
            deque.addFirst(i);
            assertTrue("empty deque", !deque.isEmpty());
        }
        for (int i = 0; i < 10; i++) {
            assertTrue("empty deque", !deque.isEmpty());
            deque.removeLast();
        }
        assertTrue("empty deque", deque.isEmpty());
        try {
            deque.removeFirst();
        } catch (NoSuchElementException e) {
        }
        assertTrue("empty deque", deque.isEmpty());
        try {
            deque.removeLast();
        } catch (NoSuchElementException e) {

        }
        assertTrue("empty deque", deque.isEmpty());
    }

    @Test
    public void testSize() throws Exception {
        Deque<Integer> deque = new Deque<Integer>();
        assertEquals("size deque", 0, deque.size());
        deque.addFirst(1);
        assertEquals("size deque", 1, deque.size());
        deque.removeLast();
        assertEquals("size deque", 0, deque.size());

        for (int i = 0; i < 10; i++) {
            deque.addFirst(i);
            assertEquals("size deque", i + 1, deque.size());
        }
        for (int i = 0; i < 10; i++) {
            assertEquals("size deque", 10 - i, deque.size());
            deque.removeLast();
        }
        assertEquals("size deque", 0, deque.size());
        try {
            deque.removeFirst();
        } catch (NoSuchElementException e) {
        }
        assertEquals("size deque", 0, deque.size());
        try {
            deque.removeLast();
        } catch (NoSuchElementException e) {

        }
        assertEquals("size deque", 0, deque.size());
    }

    @Test
    public void testAddFirst() throws Exception {
        Deque<Integer> deque = new Deque<Integer>();
        for (int i = 0; i < 10; ++i) {
            deque.addFirst(i);
        }
        for (int i = 0; i < 10; ++i) {
            assertEquals("addFirst deque", (long) i, (long) deque.removeLast());
        }
        // Throw a java.lang.NullPointerException if the client attempts to add a null item;
        try {
            deque.addFirst(null);
            assertTrue("addFirst null deque", false);
        } catch (NullPointerException e) {

        }
    }

    @Test
    public void testAddLast() throws Exception {
        Deque<Integer> deque = new Deque<Integer>();
        for (int i = 0; i < 10; ++i) {
            deque.addLast(i);
        }
        for (int i = 0; i < 10; ++i) {
            assertEquals("addLast deque", (long) i, (long) deque.removeFirst());
        }
        // Throw a java.lang.NullPointerException if the client attempts to add a null item;
        try {
            deque.addLast(null);
            assertTrue("addLast null deque", false);
        } catch (NullPointerException e) {

        }
    }

    @Test
    public void testRemoves() throws Exception {
        Deque<Integer> deque = new Deque<Integer>();
        //  throw a java.util.NoSuchElementException if the client attempts to remove an item from an empty deque;
        try {
            deque.removeFirst();
            assertTrue("removes no exception", false);
        } catch (NoSuchElementException e) {

        }
        try {
            deque.removeLast();
            assertTrue("removes no exception", false);
        } catch (NoSuchElementException e) {

        }

        for (int i = 0; i < 10; ++i) {
            deque.addLast(i);
        }
        for (int i = 0; i < 10; ++i) {
            assertEquals("removes deque", (long) i, (long) deque.removeFirst());
        }
        for (int i = 0; i < 10; ++i) {
            deque.addFirst(i);
        }
        for (int i = 0; i < 10; ++i) {
            assertEquals("removes deque", (long) i, (long) deque.removeLast());
        }

        //  throw a java.util.NoSuchElementException if the client attempts to remove an item from an empty deque;
        try {
            deque.removeFirst();
            assertTrue("removes no exception", false);
        } catch (NoSuchElementException e) {

        }
        try {
            deque.removeLast();
            assertTrue("removes no exception", false);
        } catch (NoSuchElementException e) {

        }
    }

    @Test
    public void testIterator() throws Exception {
        Deque<Integer> deque = new Deque<Integer>();
        for (int i = 0; i < 10; ++i) {
            deque.addLast(i);
        }

        int expected = 0;
        for (Integer i : deque) {
            assertEquals("iterator deque", (long) i, (long) expected++);
            int expectedInception = 0;
            for (Integer j : deque) {
                assertEquals("iterator inception deque", (long) j, (long) expectedInception++);
            }
        }

        Iterator<Integer> i = deque.iterator();
        while (i.hasNext()) {
            //throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator;
            try {
                i.remove();
                assertTrue("iterator remove deque", false);
            } catch (UnsupportedOperationException e) {

            }
            i.next();
        }

        // throw a java.util.NoSuchElementException if the client calls the next() method
        // //in the iterator and there are no more items to return
        try {
            i.next();
            assertTrue("iterator next deque", false);
        } catch (NoSuchElementException e) {

        }
    }
}