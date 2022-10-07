/*
Input:

Your program shall be activated through the use of the following command line input, consisting of parameters to your main method.

            > java BinarySearch <key>  <sorted_array>

e.g.

            > java BinarySearch 14 7 12 14 15 19 25

For this example, the key is 14 and the sorted array’s elements are  [7, 12, 14, 15, 19, 25].

For more information on command line arguments in java, please refer to Oracle’s documentation:

https://docs.oracle.com/javase/tutorial/essential/environment/cmdLineArgs.html (Links to an external site.)

Output

Your program shall output an integer representing the index location where our search key was found OR the integer “-1” if not found.
*/
public class BinarySearch {
    public static void main(String[] args) {
        try {
            for (int i = 0; i < values.length; i++) {
                values[i] = Integer.parseInt(args[i + 1]);
            }

            int last = Integer.MIN_VALUE;
            for (int val : values) {
                if (val < last) {
                    System.out.println("Usage: BinarySearch <key> <sorted numbers>");
                    System.out.println("The array you specified is not sorted");
                    System.exit(1);
                }
                last = val;
            }

            System.out.println(search(values, target));
        } catch(Exception e) {
            System.out.println("Usage: BinarySearch <key> <sorted numbers>");
            System.out.println("At least 2 integer arguments are required");
        }
    }

    /* 
     * Searches for `target` in `values` and returs the index if found, else -1
     */
    static int search(int[] values, int target) {
        return search(values, 0, values.length, target);
    }

    static int search(int values[], int low, int high, int target) {
        if (high >= low) {
            int mid = low + (high - low) / 2;
 
            if (values[mid] == target)
                return mid;
 
            if (values[mid] > target)
                return search(values, low, mid - 1, target);
 
            return search(values, mid + 1, high, target);
        }
 
        return -1;
    }
}
