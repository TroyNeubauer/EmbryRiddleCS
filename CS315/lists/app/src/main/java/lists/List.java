package lists;

public interface List<Item> {
	public Item getAt(int loc);

	public Item deleteAt(int loc);

	public void addToHead(Item item);

	public void addToTail(Item item);

	public void addAt(int loc, Item item);

	public void printFwd();

	public void printRev();

	public boolean isEmpty();

	public Item[] toArray();

	// added by troy
	public List<Item> cloneList();

	public int size();
}
