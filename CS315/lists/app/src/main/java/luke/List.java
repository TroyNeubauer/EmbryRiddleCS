package luke;

/*
 * List.java
 *
 * Author: Luke Newcomb
 * Submission date: Sep. 27, 2022
 * References: m1-lists List interface class from Dr. Stansbury
 *
 */

/**
 * A list where items can be added and removed.
 */
public interface List<Item> {
	/**
	 * Gets the item data at the specified location in the list.
	 *
	 * @param loc  The location (0-indexed) of the node in the list from which the data is requested
	 * @return     The data at the specified location, or null if an invalid location is provided.
	 */
	Item getAt(int loc);

	/**
	 * Deletes the node at the specified location in the list.
	 *
	 * @param loc  The location (0-indexed) of the node in the list that should be deleted.
	 * @return     The data from the node that was deleted, or null if an invalid location was provided.
	 */
	Item deleteAt(int loc);

	/**
	 * Adds an item to the head (start) of the list.
	 *
	 * @param item The data to be stored.
	 */
	void addToHead(Item item);

	/**
	 * Adds an item to the tail (end) of the list.
	 *
	 * @param item The data to be stored.
	 */
	void addToTail(Item item);

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
	void addAt(int loc, Item item);

	/**
	 * Prints the list in forward order, from head to tail.
	 */
	void printFwd();

	/**
	 * Prints the list in reverse order, from tail to head.
	 */
	void printRev();

	/**
	 * Checks if the list is empty.
	 *
	 * @return A boolean, true if the list is empty, false if not.
	 */
	boolean isEmpty();

	/*-------------------------------------------
	 *         Helpful functions
	 *-------------------------------------------*/

	/**
	 * Removes all elements from the list.
	 */
	void clear();

	/**
	 * Checks the length of the list.
	 *
	 * @return The amount of elements in the list.
	 */
	int length();

	/**
	 * Creates a new array of every item contained in the list, from head to tail.
	 *
	 * @return The array
	 */
	Item[] toArrayFwd();

	/**
	 * Creates a new array of every item contained in the list, from tail to head;
	 *
	 * @return The array
	 */
	Item[] toArrayRev();
}
