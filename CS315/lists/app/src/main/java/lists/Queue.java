package lists;

public class Queue<T> {
    VecDeque<T> inner;

    public Queue() {
        this.inner = new VecDeque<T>();
    }

    /**
     * Adds an item to the back of the queue
     */
    public void enqueue(T item) {
        this.inner.addToTail(item);
    }

    /**
     * Removes the item from the front of the queue
     * Throws a NoSuchElementException if index is out of range
     */
    public T dequeue() {
        return this.inner.deleteFront();
    }

	/**
	 * Returns true if this VecDeque is empty, false otherwise
	 */
    public boolean isEmpty() {
        return this.inner.isEmpty();
    }

	/**
	 * Returns the number of elements in the VecDeque
	 */
    public int size() {
        return this.inner.size();
    }

    @Override
    public String toString() {
        return this.inner.toString();
    }

    public T[] toArray() {
        return this.inner.toArray();
    }
}
