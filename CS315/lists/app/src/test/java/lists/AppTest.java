/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package lists;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

public class AppTest {
    @Test
    public void list() {
        checkOperations(new LinkedList<Double>());
    }

    @Test
    public void stack() {
        checkOperations(new Stack<Double>());
    }

    @Test
    public void queue() {
        checkOperations(new Queue<Double>());
    }

    public void checkOperations(List<Double> originalList) {
        for (int i = 0; i < 10000; i++) {
            ArrayList<Double> arraylist = new ArrayList<Double>();
            List<Double> list = originalList.cloneList();
            for (int j = 0; i < 1000; i++) {
                double val = Math.random();
                int index = (int) (Math.random() * arraylist.size());
                switch ((int) (Math.random() * 7)) {
                    case 0:
                        //System.out.println("add to tail " + val);
                        arraylist.add(val);
                        list.addToTail(val);
                        break;
                    case 1:
                        //System.out.println("add to head " + val);
                        arraylist.add(0, val);
                        list.addToHead(val);
                        break;
                    case 2:
                        //System.out.println("add at " + index + ":" + val);
                        arraylist.add(index, val);
                        list.addAt(index, val);
                        break;
                    case 3:
                        if (!arraylist.isEmpty()) {
                            //System.out.println("get at " + index + ":" + val);
                            arraylist.get(index);
                            list.getAt(index);
                        }
                        break;
                    default:
                        if (!arraylist.isEmpty()) {
                            Double deleted = arraylist.remove(index);
                            //System.out.println("delete at " + index + ":" + deleted);
                            list.deleteAt(index);
                        }
                        break;
                }
                assertEquals(list.size(), arraylist.size());
                Object[] ours = list.toArray();
                Object[] theirs = arraylist.toArray();
                String oursString = Arrays.toString(ours);
                assertEquals(Arrays.toString(theirs), oursString);
                //System.out.println(oursString);
                //System.out.println("");

                if (Math.random() < 0.01) {
                    arraylist.clear();
                    int toClear = list.size();
                    for (int k = 0; k < toClear; k++) {
                        list.deleteAt(0);
                    }
                }
            }
        }
    }
}
