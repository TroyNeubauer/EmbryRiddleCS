package lists;

import java.lang.reflect.Field;
import java.util.NoSuchElementException;

import sun.misc.Unsafe;

/**
 * Same as `lists.VecDeque` but cheesier
 */
public class ExhibitionCheeseList {
    static Unsafe unsafe = loadUnsafe();

    static Unsafe loadUnsafe() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The ring buffer of data null for unwritten elements
     */
    private long buf;

    // the capacity of buf in number of int elements
    private int elements;

    /**
     * The index of the first element
     */
    int first;

    /**
     * One index past the last element - always modulus buf.length
     */
    int last;

    boolean wrapped;

    public ExhibitionCheeseList() {
        this(16);
    }

    /**
     * Creates a new empty VecDeque with the capacity of cap.
     */
    public ExhibitionCheeseList(int cap) {
        if (cap == 0) {
            throw new IllegalArgumentException();
        }
        this.buf = unsafe.allocateMemory(cap * 4);
        this.elements = cap;
    }

    /**
     * Returns the index where the actual data at location `loc` resides, accounting
     * for wrapping
     */
    int wrappedIndex(int loc) {
        if (!this.wrapped || loc < this.wrappedFirstLen()) {
            // in first sub array between `this.first` and the end of the array
            return this.first + loc;
        } else {
            // element resides in the start of the array before `this.first`
            return loc - this.wrappedFirstLen();
        }
    }

    /**
     * Returns the element at index loc
     * Throws a NoSuchElementException if index is out of range
     */
    public int getAt(int loc) {
        indexAccessCheck(loc);
        int real = this.wrappedIndex(loc);
        return unsafe.getInt(this.buf + real * 4);
    }

    /**
     * Deletes the first element from the vec deq.
     * Throws a NoSuchElementException if the VecDeque is empty
     */
    public int deleteFront() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        int result = unsafe.getInt(this.buf + this.first * 4);
        this.first++;
        if (this.first == this.elements) {
            this.first = 0;
            this.wrapped = false;
        }
        return result;
    }

    /**
     * Deletes the back element from the vec deq.
     * Throws a NoSuchElementException if the VecDeque is empty
     */
    public int deleteBack() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        int index;
        if (this.last == 0) {
            // Deleting the last element
            // This creates a space at the end of the array so we are no longer wrapped
            this.wrapped = false;
            index = this.elements - 1;
        } else {
            index = this.last - 1;
        }

        int result = unsafe.getInt(this.buf + index * 4);
        this.last = index;
        return result;
    }

    /**
     * Adds item to the head of the list
     */
    public void addToHead(int item) {
        doubleCapIfNeeded();
        int index;
        if (this.first == 0) {
            // adding to last element, so we are now wrapped
            index = this.elements - 1;
            assert !this.wrapped;
            this.wrapped = true;
        } else {
            index = this.first - 1;
        }
        unsafe.putInt(this.buf + index * 4, item);
        this.first = index;
    }

    /**
     * Adds item to the back of the list
     */
    public void addToTail(int item) {
        doubleCapIfNeeded();

        int index = this.last;
        unsafe.putInt(this.buf + index++ * 4, item);
        if (index == this.elements) {
            // would wrap
            this.wrapped = true;
            index = 0;
        }
        this.last = index;
    }

    /**
     * Returns true if this VecDeque is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.first == this.last && !wrapped;
    }

    /**
     * Returns true if this VecDeque is full and will reallocate on the next add
     * operation,
     * false otherwise
     */
    public boolean isFull() {
        return this.first == this.last && wrapped;
    }

    /**
     * Returns the number of elements in the VecDeque
     */
    public int size() {
        if (this.wrapped) {
            return this.wrappedFirstLen() + this.wrappedSecondLen();
        } else {
            return this.last - this.first;
        }
    }

    void doubleCapIfNeeded() {
        if (this.isFull()) {
            this.resize(this.elements * 2);
        }
    }

    /**
     * When wrapped, returns the legth of the first subsection in the array.
     * The first subarray starts at this.first, and has `wrappedFirstLen` items (up
     * to the end of the buffer)
     */
    int wrappedFirstLen() {
        return this.elements - this.first;
    }

    /**
     * When wrapped, returns the legth of the second subsection in the array.
     * The second subarray starts at index 0, and has `wrappedSecondLen` items (up
     * to index this.last)
     */
    int wrappedSecondLen() {
        return this.last;
    }

    void indexAccessCheck(int loc) {
        if (loc < 0 || loc >= this.size()) {
            throw new ArrayIndexOutOfBoundsException(loc);
        }
    }

    public void resize(int newcap) {
        if (newcap <= this.elements) {
            throw new IllegalArgumentException("new capicity must be more than the current capacity: " + newcap);
        }
        int size = this.size();
        long dst = unsafe.allocateMemory(newcap * 4);

        if (this.wrapped) {

            // System.arraycopy(this.buf, this.first, dst, 0, firstLen);
            // System.arraycopy(this.buf, 0, dst, firstLen, this.wrappedSecondLen());
            int firstLen = this.wrappedFirstLen();
            unsafe.copyMemory(this.buf + this.first * 4, dst, firstLen * 4);
            unsafe.copyMemory(this.buf, dst + firstLen * 4, this.wrappedSecondLen() * 4);
        } else {
            unsafe.copyMemory(this.buf + this.first * 4, dst, size * 4);
            // System.arraycopy(this.buf, this.first, dst, 0, size);
        }
        unsafe.freeMemory(this.buf);

        this.wrapped = false;
        this.first = 0;
        this.last = size;

        this.buf = dst;
        this.elements = newcap;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');

        int size = this.size();
        for (int i = 0; i < size; i++) {
            int val = this.getAt(i);
            sb.append(val);

            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');

        return sb.toString();
    }

    public void printInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');

        for (int i = 0; i < this.elements; i++) {
            int val = unsafe.getInt(this.buf + i * 4);
            sb.append(val);

            if (i != this.elements - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');

        System.out.println("Cheese: " + sb.toString() + "\nsize: " + this.size() + ", wrapped: " + this.wrapped
                + ", first " + this.first + ", last "
                + this.last);
    }

    @Override
    protected void finalize() throws Throwable {
        unsafe.freeMemory(this.buf);
    }
}
