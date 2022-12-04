package luke;

/*
 * DLList.java
 *
 * Author: Luke Newcomb
 * Submission date: Sep. 27, 2022
 * References:
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * A doubly-linked list.
 */
public class DLList<Item> implements List<Item>, Iterable<Item> {
	/**
	 * A node in the linked list.
	 */
	private class Node {
		Item value;
		Node next;
		Node prev;

		public Node(Item item) {
			value = item;
			next = null;
			prev = null;
		}

		public Node(Item item, Node next, Node prev) {
			value = item;
			this.next = next;
			this.prev = prev;
		}
	}

	private Node head, tail;

	/**
	 * Constructs a new empty doubly-linked list.
	 */
	public DLList() {
		head = null;
		tail = null;
	}

	@Override
	public Iterator<Item> iterator() {
		return new DLListIterator<Item>(this);
	}

	/**
	 * Gets the item data at the specified location in the list.
	 *
	 * @param loc  The location (0-indexed) of the node in the list from which the data is requested
	 * @return     The data at the specified location, or null if an invalid location is provided.
	 */
	public Item getAt(int loc) {
		int currentLoc = 0;
		Node curr = head;

		if (loc < 0) {
			return null;
		}

		while (currentLoc < loc) {
			if (curr == null) {
				return null;
			}

			curr = curr.next;
			currentLoc++;
		}

		if (curr == null) {
			return null;
		}

		return curr.value;
	}

	/**
	 * Deletes the node at the specified location in the list.
	 *
	 * @param loc  The location (0-indexed) of the node in the list that should be deleted.
	 * @return     The data from the node that was deleted, or null if an invalid location was provided.
	 */
	public Item deleteAt(int loc) {
		if (loc < 0) {
			return null;
		} else if (loc == 0) {
			if (head == null) {
				return null;
			}

			Node node = head;

			if (tail == head) {
				tail = null;
			}

			head = head.next;

			if (head != null) {
				head.prev = null;
			}

			return node.value;
		}

		int currentLoc = 0;
		Node curr = head;

		while (currentLoc < loc) {
			if (curr == null) {
				return null;
			}

			curr = curr.next;
			currentLoc++;
		}

		if (curr == null) {
			return null;
		}

		if (curr == tail) {
			tail = curr.prev;
		}

		Node node = curr;
		curr.prev.next = curr.next;

		if (curr.next != null) {
			curr.next.prev = curr.prev;
		}

		return curr.value;
	}

	/**
	 * Adds an item to the head (start) of the list.
	 *
	 * @param item The data to be stored.
	 */
	public void addToHead(Item item) {
		Node newNode = new Node(item, head, null);

		if (head != null) {
			head.prev = newNode;
		}

		head = newNode;

		if (tail == null) {
			tail = newNode;
		}
	}

	/**
	 * Adds an item to the tail (end) of the list.
	 *
	 * @param item The data to be stored.
	 */
	public void addToTail(Item item) {
		if (head == null) {
			addToHead(item);
		} else {
			Node newNode = new Node(item, null, tail);
			tail.next = newNode;
			tail = newNode;
		}
	}

	/**
	 * Adds an item to the list at the specified location.
	 * <p>
	 * If the location provided is not in the range 0..length, the item will be inserted
	 * at the closest position. A value less than 0 will be added to the head, and a value
	 * greater than the length will be added to the tail.
	 *
	 * @param loc  The location (0-indexed) that the item should be inserted into the list.
	 * @param item The data to be stored.
	 */
	public void addAt(int loc, Item item) {
		if (loc <= 0) {
			addToHead(item);
		} else {
			if (head == null) {
				addToHead(item);

				return;
			}

			int currentLoc = 1;
			Node curr = head.next;

			while (currentLoc < loc) {
				if (curr == null) {
					addToTail(item);

					return;
				}

				curr = curr.next;
				currentLoc++;
			}

			if (curr == null) {
				addToTail(item);

				return;
			}
			
			Node newNode = new Node(item, curr, curr.prev);
			curr.prev.next = newNode;
		}
	}

	/**
	 * Creates a new array of every item contained in the list, from head to tail.
	 *
	 * @return The array
	 */
	public Item[] toArrayFwd() {
		ArrayList<Item> items = new ArrayList<Item>();

		Node curr = head;

		while (curr != null) {
			items.add(curr.value);

			curr = curr.next;
		}

		return (Item[])items.toArray();
	}

	/**
	 * Creates a new array of every item contained in the list, from tail to head;
	 *
	 * @return The array
	 */
	public Item[] toArrayRev() {
		ArrayList<Item> items = new ArrayList<Item>();

		Node curr = tail;

		while (curr != null) {
			items.add(curr.value);

			curr = curr.prev;
		}

		return (Item[])items.toArray();
	}

	/**
	 * Prints the list in forward order, from head to tail.
	 */
	public void printFwd() {
		Object[] items = toArrayFwd();

		for (int i = 0; i < items.length; i++) {
			System.out.print(items[i].toString());

			if (i < items.length - 1) {
				System.out.print(", ");
			}
		}

		System.out.println();
	}

	/**
	 * Prints the list in reverse order, from tail to head.
	 */
	public void printRev() {
		Object[] items = toArrayRev();

		for (int i = 0; i < items.length; i++) {
			System.out.print(items[i].toString());

			if (i < items.length - 1) {
				System.out.print(", ");
			}
		}

		System.out.println();
	}

	/**
	 * Checks if the list is empty.
	 *
	 * @return A boolean, true if the list is empty, false if not.
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Removes all elements from the list.
	 */
	public void clear() {
		tail = null;
		head = null;
	}

	/**
	 * Checks the length of the list.
	 *
	 * @return The amount of elements in the list.
	 */
	public int length() {
		int length = 0;

		Node curr = head;

		while (curr != null) {
			curr = curr.next;
			length++;
		}

		return length;
	}

	public static void main(String[] args) {
		System.out.println("All tests passed: " + (ListTest.testList(new DLList<String>()) ? "yes" : "no"));
	}

	protected static class DLListIterator<Item> implements Iterator<Item> {

		private DLList<Item> list = null;

		private int pos = 0;

		public DLListIterator(DLList<Item> list) {
			this.list = list;
		}

		@Override
		public boolean hasNext() {
			return list.getAt(pos) != null;
		}

		@Override
		public Item next() {
			return list.getAt(pos++);
		}
	}
}
