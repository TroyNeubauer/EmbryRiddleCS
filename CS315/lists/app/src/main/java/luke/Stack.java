package luke;

/*
 * Stack.java
 *
 * Author: Luke Newcomb
 * Submission date: Sep. 27, 2022
 * References:
 *
 */

import java.util.Iterator;

/**
 * A First-In-Last-Out Stack.
 */
public class Stack<Item> implements Iterable<Item> {
	SLList<Item> list;
	int size = 0;

	/**
	 * Constructs a new empty Stack
	 */
	public Stack() {
		list = new SLList<Item>();
	}

	@Override
	public Iterator<Item> iterator() {
		return new StackIterator<Item>(this);
	}

	/**
	 * Adds a new item to the top of the stack.
	 *
	 * @param item The item to be pushed.
	 */
	public void push(Item item) {
		size++;
		list.addToHead(item);
	}

	/**
	 * Removes an item from the top of the stack.
	 *
	 * @return The item from the stack, or null if the stack was empty.
	 */
	public Item pop() {
		size--;

		if (size < 0) {
			size = 0;
		}

		return list.deleteAt(0);
	}

	/**
	 * Checks if the stack is empty.
	 *
	 * @return A boolean, true if the stack is empty, false if not.
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Checks the size of the stack.
	 *
	 * @return The amount of elements on the stack.
	 */
	public int size() {
		return size;
	}

	public static void main(String[] args) {
		System.out.println("All tests passed: " + (StackTest.testStack() ? "yes" : "no"));
	}

	protected static class StackIterator<Item> implements Iterator<Item> {

		private Stack<Item> stack = null;

		private int pos = 0;

		public StackIterator(Stack<Item> stack) {
			this.stack = stack;
		}

		@Override
		public boolean hasNext() {
			return pos < stack.size();
		}

		@Override
		public Item next() {
			return stack.list.getAt(pos++);
		}
	}
}
