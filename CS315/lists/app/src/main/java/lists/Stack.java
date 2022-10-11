package lists;

public class Stack<T> {
	VecDeque<T> inner;

	/**
	 * Create an empty stack
	 */
	Stack() {
		this.inner = new VecDeque<T>();
	}

	public void push(T item) {
		this.inner.addToTail(item);
	}

	public T pop() {
		return this.inner.deleteBack();
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
