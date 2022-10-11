package lists;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Test client for the Stack, Queue, List and VecDec classes
 *
 */
public class AppTest {

    /**
     * Creates a List and a Java Array list, performs 10 million random operations on them,
     * and ensures that 
     */
    @Test
    public void listRussianRoulette() {
        for (int i = 0; i < 10000; i++) {
            ArrayList<Double> arraylist = new ArrayList<Double>();
            LinkedList<Double> list = new LinkedList<Double>();
            for (int j = 0; i < 1000; i++) {
                double val = Math.random();
                int index = (int) (Math.random() * arraylist.size());
                switch ((int) (Math.random() * 7)) {
                    case 0:
                        // System.out.println("add to tail " + val);
                        arraylist.add(val);
                        list.addToTail(val);
                        break;
                    case 1:
                        // System.out.println("add to head " + val);
                        arraylist.add(0, val);
                        list.addToHead(val);
                        break;
                    case 2:
                        // System.out.println("add at " + index + ":" + val);
                        arraylist.add(index, val);
                        list.addAt(index, val);
                        break;
                    case 3:
                        if (!arraylist.isEmpty()) {
                            // System.out.println("get at " + index + ":" + val);
                            arraylist.get(index);
                            list.getAt(index);
                        }
                        break;
                    default:
                        if (!arraylist.isEmpty()) {
                            Double deleted = arraylist.remove(index);
                            // System.out.println("delete at " + index + ":" + deleted);
                            Double deleted2 = list.deleteAt(index);
                            assertEquals(deleted, deleted2);
                        }
                        break;
                }
                assertEquals(list.size(), arraylist.size());
                Object[] ours = list.toArray();
                Object[] theirs = arraylist.toArray();
                String oursString = Arrays.toString(ours);
                assertEquals(Arrays.toString(theirs), oursString);
                // System.out.println(oursString);
                // System.out.println("");

                if (Math.random() < 0.01) {
                    arraylist.clear();
                    int toClear = list.size();
                    for (int k = 0; k < toClear; k++) {
                        list.deleteAt(0);
                    }
                }
            }
        }
    }

    /**
     * Tests for explicit edge cases with the VecDeque class
     */
    @Test
    public void vecDeque() {
        VecDeque<Integer> v = new VecDeque<Integer>(2);
        assertTrue(v.isEmpty());
        v.resize(4);
        assertTrue(v.isEmpty());

        v.addToTail(1);
        v.addToTail(2);
        v.addToTail(3);
        v.addToTail(4);
        assertTrue(v.isFull());

        assertEquals(v.deleteFront().intValue(), 1);
        assertEquals(v.deleteFront().intValue(), 2);
        assertEquals(v.deleteBack().intValue(), 4);
        assertEquals(v.deleteBack().intValue(), 3);
        assertTrue(v.isEmpty());

        v.addToHead(4);
        v.addToHead(3);
        v.addToHead(2);
        v.addToHead(1);
        v.addToHead(0);
        assertArrayEquals(new Integer[] { 0, 1, 2, 3, 4 }, v.toArray());
    }

    /**
     * Tests for explicit edge cases with the Stack class
     */
    @Test
    public void stack() {
        Stack<Integer> v = new Stack<Integer>();
        assertTrue(v.isEmpty());
        v.push(1);
        v.push(2);
        assertEquals(v.size(), 2);
        v.push(3);
        assertEquals(v.size(), 3);

        assertArrayEquals(new Integer[] { 1, 2, 3 }, v.toArray());

        assertEquals(v.pop().intValue(), 3);
        assertEquals(v.size(), 2);
        assertEquals(v.pop().intValue(), 2);
        assertEquals(v.pop().intValue(), 1);
        assertEquals(v.size(), 0);
        assertTrue(v.isEmpty());
    }

    @Test
    public void stackRussianRoulette() {
        for (int i = 0; i < 10000; i++) {
            java.util.Stack<Double> javaStack = new java.util.Stack<Double>();
            Stack<Double> stack = new Stack<Double>();
            for (int j = 0; i < 1000; i++) {
                double val = Math.random();
                switch ((int) (Math.random() * 2)) {
                    case 0:
                        // System.out.println("push " + val);
                        javaStack.push(val);
                        stack.push(val);
                        break;
                    case 1:
                        if (!javaStack.isEmpty()) {
                            Double deleted = javaStack.pop();
                            // System.out.println("pop " + ":" + deleted);
                            Double deleted2 = stack.pop();
                            assertEquals(deleted, deleted2);
                        }
                        break;
                }
                assertEquals(stack.size(), javaStack.size());
                Object[] ours = stack.toArray();
                Object[] theirs = javaStack.toArray();
                String oursString = Arrays.toString(ours);
                assertEquals(Arrays.toString(theirs), oursString);
                // System.out.println(oursString);
                // System.out.println("");

                if (Math.random() < 0.01) {
                    javaStack.clear();
                    int toClear = stack.size();
                    for (int k = 0; k < toClear; k++) {
                        stack.pop();
                    }
                }
            }
        }
    }

    /**
     * Tests for explicit edge cases with the Queue class
     */
    @Test
    public void queue() {
        Queue<Integer> v = new Queue<Integer>();
        assertTrue(v.isEmpty());
        v.enqueue(1);
        v.enqueue(2);
        assertEquals(v.size(), 2);
        v.enqueue(3);
        assertEquals(v.size(), 3);

        assertArrayEquals(new Integer[] { 1, 2, 3 }, v.toArray());

        assertEquals(v.dequeue().intValue(), 1);
        assertEquals(v.size(), 2);
        assertEquals(v.dequeue().intValue(), 2);
        assertEquals(v.dequeue().intValue(), 3);
        assertEquals(v.size(), 0);
        assertTrue(v.isEmpty());
    }

    @Test
    public void queueRussianRoulette() {
        for (int i = 0; i < 10000; i++) {
            java.util.Deque<Double> javaQueue = new java.util.ArrayDeque<Double>();
            Queue<Double> queue = new Queue<Double>();
            for (int j = 0; i < 1000; i++) {
                double val = Math.random();
                switch ((int) (Math.random() * 2)) {
                    case 0:
                        // System.out.println("enqueue " + val);
                        javaQueue.addLast(val);
                        queue.enqueue(val);
                        break;
                    case 1:
                        if (!javaQueue.isEmpty()) {
                            Double deleted = javaQueue.removeFirst();
                            // System.out.println("dequeue " + ":" + deleted);
                            Double deleted2 = queue.dequeue();
                            assertEquals(deleted, deleted2);
                        }
                        break;
                }
                assertEquals(queue.size(), javaQueue.size());
                Object[] ours = queue.toArray();
                Object[] theirs = javaQueue.toArray();
                String oursString = Arrays.toString(ours);
                assertEquals(Arrays.toString(theirs), oursString);
                // System.out.println(oursString);
                // System.out.println("");

                if (Math.random() < 0.01) {
                    javaQueue.clear();
                    int toClear = queue.size();
                    for (int k = 0; k < toClear; k++) {
                        queue.dequeue();
                    }
                }
            }
        }
    }
}
