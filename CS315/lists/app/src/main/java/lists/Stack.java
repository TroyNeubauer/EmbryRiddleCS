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

	/**
	 * Writes the elements inside this VecDeque an array using shallow cloning
	 */
	public T[] toArray() {
		return this.inner.toArray();
	}
}
