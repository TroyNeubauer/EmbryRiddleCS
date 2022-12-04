package luke;

/*
 * Queue.java
 *
 * Author: Luke Newcomb
 * Submission date: Sep. 27, 2022
 * References:
 *
 */

import java.util.Iterator;

/**
 * A First-In-First-Out Queue.
 */
public class Queue<Item> implements Iterable<Item> {
	DLList<Item> list;
	int size = 0;

	/**
	 * Constructs a new empty Queue
	 */
	public Queue() {
		list = new DLList<Item>();
	}

	/**
	 * Adds a new item to the end of the queue.
	 *
	 * @param item The item to be enqueued.
	 */
	public void enqueue(Item item) {
		list.addToTail(item);
		size++;
	}

	/**
	 * Removes an item from the front of the queue.
	 *
	 * @return The item from the queue, or null if the queue was empty.
	 */
	public Item dequeue() {
		Item i = list.deleteAt(0);
		size--;
		
		if (size < 0) {
			size = 0;
		}

		return i;
	}

	/**
	 * Checks if the queue is empty.
	 *
	 * @return A boolean, true if the queue is empty, false if not.
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Checks the size of the queue.
	 *
	 * @return The amount of elements in the queue.
	 */
	public int size() {
		return size;
	}

	public static void main(String[] args) {
		System.out.println("All tests passed: " + (QueueTest.testQueue() ? "yes" : "no"));
	}

	@Override
	public Iterator<Item> iterator() {
		return new QueueIterator<Item>(this);
	}

	protected static class QueueIterator<Item> implements Iterator<Item> {

		private Queue<Item> queue = null;

		private int pos = 0;

		public QueueIterator(Queue<Item> queue) {
			this.queue = queue;
		}

		@Override
		public boolean hasNext() {
			return pos < queue.size();
		}

		@Override
		public Item next() {
			return queue.list.getAt(pos++);
		}
	}
}
