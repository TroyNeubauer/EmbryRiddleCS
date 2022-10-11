package lists;

public class Queue<T> {
    VecDeque<T> inner;

    Queue() {
        this.inner = new VecDeque<T>();
    }

    void enqueue(T item) {
        this.inner.addToTail(item);
    }

    T dequeue() {
        return this.inner.deleteFront();
    }

    public boolean isEmpty() {
        return this.inner.isEmpty();
    }

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
