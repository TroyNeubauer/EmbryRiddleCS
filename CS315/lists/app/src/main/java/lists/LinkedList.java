package lists;

/**
 * Test
 */
public class LinkedList<T> implements List<T> {

    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        Node(T item) {
            this.item = item;
            this.next = null;
            this.prev = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int len;

    LinkedList() {
        this.head = null;
        this.tail = null;
        this.len = 0;
    }

    /**
     * Returns the element at index loc
     * Throws a NoSuchElementException if loc is out of range
     */
    @Override
    public T getAt(int loc) {
        this.indexCheck(loc);
        Node<T> node = this.head;
        for (int i = 0; i < loc; i++) {
            node = node.next;
        }
        return node.item;
    }

    /**
     * Deletes the item at index loc
     * Throws a NoSuchElementException if loc is out of range
     */
    @Override
    public T deleteAt(int loc) {
        this.indexCheck(loc);
        this.len--;

        Node<T> garbage = null;
        if (loc == 0) {
            // delete the head
            Node<T> oldHead = this.head;
            garbage = oldHead;
            this.head = oldHead.next;
            if (this.head != null) {
                this.head.prev = null;
            }
            if (this.len == 0) {
                // list now empty
                this.tail = null;
            }
        } else if (loc == this.len) {
            // delete the tail
            Node<T> oldTail = this.tail;
            garbage = oldTail;
            this.tail = this.tail.prev;
            this.tail.next = null;

        } else {
            // normal case, delete node in the middle
            Node<T> node = this.head;
            for (int i = 0; i < loc; i++) {
                node = node.next;
            }
            Node<T> last = node.prev;
            Node<T> next = node.next;

            // unlink
            last.next = next;
            next.prev = last;
            garbage = node;
        }
        // help garbage collector by nulling out references
        garbage.next = null;
        garbage.prev = null;
        return garbage.item;
    }

    /**
     * Adds item to the head of the list
     */
    @Override
    public void addToHead(T item) {
        Node<T> newHead = new Node<T>(item);
        if (this.head == null) {
            this.tail = newHead;
        } else {
            this.head.prev = newHead;
        }
        newHead.next = this.head;

        this.head = newHead;
        this.len++;
    }

    /**
     * Adds item to the tail of the list
     */
    @Override
    public void addToTail(T item) {
        Node<T> newTail = new Node<T>(item);
        if (this.tail == null) {
            this.head = newTail;
        } else {
            this.tail.next = newTail;
        }
        newTail.prev = this.tail;

        this.tail = newTail;
        this.len++;
    }

    /**
     * Adds item at loc to the list
     */
    @Override
    public void addAt(int loc, T item) {
        if (loc <= 0) {
            this.addToHead(item);
        } else if (loc >= this.len) {
            this.addToTail(item);
        } else {
            // We are not adding to the head or tail, so where we insert is guarnteed to
            // have a node before and after
            Node<T> before = this.head;
            for (int i = 1; i < loc; i++) {
                before = before.next;
            }
            Node<T> after = before.next;
            Node<T> node = new Node<T>(item);

            before.next = node;
            after.prev = node;

            node.next = after;
            node.prev = before;

            // The calls to `addToHead/Tail` increment len so make sure to only do than in
            // this block
            this.len++;
        }
    }

    /**
     * Prints this list in forward order
     */
    @Override
    public void printFwd() {
        Node<T> node = this.head;
        while (node != null) {
            System.out.print(node.item.toString());
            if (node.next != null) {
                System.out.print(", ");
            }
            node = node.next;
        }
        System.out.println("");
    }

    /**
     * Prints this list in reverse order
     */
    @Override
    public void printRev() {
        Node<T> node = this.tail;
        while (node != null) {
            System.out.print(node.item.toString());
            if (node.prev != null) {
                System.out.print(", ");
            }
            node = node.prev;
        }
    }

    /**
     * Returns true if this VecDeque is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return len == 0;
    }

    @Override
    public T[] toArray() {
        T[] result = (T[]) new Object[this.len];
        Node<T> node = this.head;
        int i = 0;
        while (node != null) {
            result[i++] = node.item;
            assert node.item != null;
            node = node.next;
        }
        return result;
    }

    @Override
    public LinkedList<T> cloneList() {
        LinkedList<T> result = new LinkedList<T>();
        Node<T> node = this.head;
        while (node != null) {
            result.addToTail(node.item);
            node = node.next;
        }
        return result;
    }

    /**
     * Returns the number of elements in the VecDeque
     */
    @Override
    public int size() {
        return this.len;
    }

    void printSingleNode(Node<T> node) {
        if (node == null) {
            System.out.println("null");
        } else {
            System.out.println(node.item);
        }
    }

    void indexCheck(int loc) {
        if (loc >= this.len || loc < 0) {
            throw new ArrayIndexOutOfBoundsException(loc);
        }
    }
}
