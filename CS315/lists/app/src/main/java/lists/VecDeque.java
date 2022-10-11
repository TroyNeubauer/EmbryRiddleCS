package lists;

import java.util.Arrays;
import java.util.Iterator;

/**
 *
 */
//
public class VecDeque<T> implements Iterable<T> {
    /**
     * The ring buffer of data
     */
    private T[] buf;

    /**
     * The index of the first element
     */
    int first;

    /**
     * One index past the last element
     */
    int last;

    /**
     * Set to true if sequential values in the ring buffer wrap back to the front
     * When true this indicates that last <= first, and if last == first, the buffer
     * is full
     * If wrapped == false and last == first, this indicates that the buffer is
     * empty
     */
    boolean wrapped;

    public VecDeque() {
        this(16);
    }

    public VecDeque(int cap) {
        if (cap == 0) {
            throw new IllegalArgumentException();
        }
        this.buf = (T[]) new Object[cap];
    }

    VecDeque(T[] buf) {
        this.buf = buf;
    }

    void indexAccessCheck(int loc) {
        if (loc < 0 || loc >= this.size()) {
            throw new ArrayIndexOutOfBoundsException(loc);
        }
    }

    /**
     * Returns the index where the actual data at location `loc` resides, accounting
     * for wrapping
     */
    int wrappedIndex(int loc) {
        if (!this.wrapped || loc < this.wrappedFirstLen()) {
            // in first sub array between `this.first` and the end of the array
            return loc - this.first;
        } else {
            // element resides in the start of the array before `this.first`
            return loc - this.wrappedFirstLen();
        }
    }

    public T getAt(int loc) {
        indexAccessCheck(loc);
        return this.buf[this.wrappedIndex(loc)];
    }

    public T deleteFront() {
        T result = this.buf[this.first];
        this.buf[this.first] = null;
        this.first++;
        if (this.first == this.buf.length) {
            this.first = 0;
            this.wrapped = false;
        }
        return result;
    }

    public T deleteBack() {
        int index;
        if (this.last == 0) {
            // Deleting the last element
            // This creates a space at the end of the array so we are no longer wrapped
            this.wrapped = false;
            index = this.buf.length - 1;
        } else {
            index = this.last - 1;
        }

        T result = this.buf[index];
        this.buf[index] = null;
        this.last = index;
        return result;
    }

    public void addToHead(T item) {
        doubleCapIfNeeded();
        int index;
        if (this.first == 0) {
            // adding to last element, so we are now wrapped
            index = this.buf.length - 1;
            assert !this.wrapped;
            this.wrapped = true;
        } else {
            index = this.first - 1;
        }
        this.buf[index] = item;
        this.first = index;
    }

    public void addToTail(T item) {
        doubleCapIfNeeded();

        int index = this.last;
        this.buf[index++] = item;
        if (index == this.buf.length) {
            // would wrap
            this.wrapped = true;
            index = 0;
        }
        this.last = index;
    }

    void doubleCapIfNeeded() {
        if (this.isFull()) {
            this.resize(this.buf.length * 2);
        }
    }

    public void printFwd() {
        T[] arr = this.toArray();
        System.out.println(Arrays.toString(arr));
    }

    public void printRev() {
        T[] arr = this.toArray();
        // swap array then print (why isnt reversing in array in the standard library!?)
        for (int i = 0; i < arr.length / 2; i++) {
            int opposite = arr.length - 1 - i;
            T tmp = arr[i];
            arr[i] = arr[opposite];
            arr[opposite] = tmp;
        }
        System.out.println(Arrays.toString(arr));
    }

    public boolean isEmpty() {
        return this.first == this.last && !wrapped;
    }

    public boolean isFull() {
        return this.first == this.last && wrapped;
    }

    public T[] toArray() {
        T[] result = (T[]) new Object[this.size()];
        this.writeToArray(result);
        return result;
    }

    public void writeToArray(T[] dst) {
        int size = this.size();
        if (dst.length < size) {
            throw new IllegalArgumentException("dst buffer to short to hold real size " + size);
        }
        if (this.wrapped) {
            int firstLen = this.wrappedFirstLen();
            System.arraycopy(this.buf, this.first, dst, 0, firstLen);
            System.arraycopy(this.buf, 0, dst, firstLen, this.wrappedSecondLen());
        } else {
            System.arraycopy(this.buf, this.first, dst, 0, size);
        }
    }

    public VecDeque<T> cloneList() {
        int size = this.size();
        T[] result = (T[]) new Object[size];
        System.arraycopy(this.buf, 0, result, 0, size);
        return new VecDeque<T>(result);
    }

    public int size() {
        if (this.wrapped) {
            return this.wrappedFirstLen() + this.wrappedSecondLen();
        } else {
            return this.last - this.first;
        }
    }

    /**
     * When wrapped, returns the legth of the first subsection in the array.
     * The first subarray starts at this.first, and has `wrappedFirstLen` items (up
     * to the end of the buffer)
     */
    int wrappedFirstLen() {
        return this.buf.length - this.first;
    }

    /**
     * When wrapped, returns the legth of the second subsection in the array.
     * The second subarray starts at index 0, and has `wrappedSecondLen` items (up
     * to index this.last)
     */
    int wrappedSecondLen() {
        return this.last;
    }

    public void resize(int newcap) {
        if (newcap <= this.buf.length) {
            throw new IllegalArgumentException("new capicity must be more than the current capacity: " + newcap);
        }
        int size = this.size();
        T[] result = (T[]) new Object[newcap];
        this.writeToArray(result);

        this.wrapped = false;
        this.first = 0;
        this.last = size;
        this.buf = result;
    }

    @Override
    public String toString() {
        return "VecDeque: " + Arrays.toString(this.buf) + "\nsize: " + this.size() + ", wrapped: " + this.wrapped
                + ", first " + this.first + ", last "
                + this.last;
    }

    @Override
    public Iterator<T> iterator() {
        return new VecDequeIterator<>(this);
    }

    static class VecDequeIterator<T> implements Iterator<T> {
        VecDeque<T> v;
        int index;
        int size;

        VecDequeIterator(VecDeque<T> v) {
            this.v = v;
            this.index = v.first;
            this.size = v.size();
        }

        @Override
        public boolean hasNext() {
            return this.index < this.size;
        }

        @Override
        public T next() {
            return this.v.getAt(this.index++);
        }
    }
}
