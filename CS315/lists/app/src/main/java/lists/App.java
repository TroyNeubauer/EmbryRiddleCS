/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package lists;

public class App {

    public static void main(String[] args) {
        LinkedList<Integer> l = new LinkedList<Integer>();
        l.addToHead(1);
        l.addToTail(10);
        l.addAt(1, 5);

        l.printFwd();
        l.printFwd();
        l.printFwd();
        l.printFwd();
    }
}
