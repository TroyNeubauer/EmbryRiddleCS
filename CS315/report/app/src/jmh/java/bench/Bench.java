package bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import java.util.*;

import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.ArrayUtils;

@Threads(12)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 1, time = 1)
@Measurement(iterations = 2, time = 2)
public class Bench {
    // Powers of 4 used to determine list size, and index into the arrays below
    @Param({ "1", "2", "3", "4", "5", "6", "8" })
    public int listSizePower;

    private ArrayList<int[]> sortedArrays = new ArrayList<>();
    private ArrayList<int[]> reverseArrays = new ArrayList<>();
    private ArrayList<int[]> randomArrays = new ArrayList<>();
    private ArrayList<int[]> almostSortedArrays = new ArrayList<>();

    @Setup(Level.Trial)
    public void setup() {
        // use a fixed seed so that the arrays are the same
        Random r = new Random(1765103881275L);
        // initialize all the arrays for the sizes we will use
        for (int i = 0; i <= 10; i++) {
            int len = (int) Math.pow(4.0, i);
            int[] baseData = new int[len];
            for (int j = 0; j < len; j++) {
                baseData[j] = r.nextInt();
            }
            int[] sorted = baseData.clone();
            Arrays.sort(sorted);

            int[] reverse = sorted.clone();
            ArrayUtils.reverse(reverse);

            int[] almostSorted = sorted.clone();
            // create almost sorted by swapping 10% of the elements randomly
            for (int j = 0; j < len / 10; j++) {
                // get a random index in the first half of the array
                int index = r.nextInt(len / 2);
                int compliment = len - index - 1;
                int tmp = almostSorted[index];
                almostSorted[index] = almostSorted[compliment];
                almostSorted[compliment] = tmp;
            }

            sortedArrays.add(i, sorted);
            reverseArrays.add(i, reverse);
            randomArrays.add(i, baseData);
            almostSortedArrays.add(i, almostSorted);
        }
    }

    void testMergeSort(int[] input, boolean isSorted, Blackhole blackhole) {
        // Sadly we have to clone the array before sorting so that we dont mess it up
        // This time counts toward the benchmark which is unfourtinate, but there is no
        // better way to do this
        // However, if the input array is already sorted (isSorted), then sorting it
        // will have no effect on future benchmarks so we can avoid the copy
        int[] arr;
        if (isSorted) {
            arr = input;
        } else {
            arr = input.clone();
        }
        mergeSort(arr);
        // Blackhole is a special class that comes from openjdk that allows us to tell
        // the JIT to not assume anything about the side effects of arr.
        // This ensures that the runtime will not delete all of our code because it has
        // no observable side effects
        blackhole.consume(arr);
    }

    void testBubbleSort(int[] input, boolean isSorted, Blackhole blackhole) {
        // same as testMergeSort, but calls bubble sort
        int[] arr;
        if (isSorted) {
            arr = input;
        } else {
            arr = input.clone();
        }
        bubbleSort(arr);
        blackhole.consume(arr);
    }

    void testQuickSort(int[] input, boolean isSorted, Blackhole blackhole) {
        int[] arr;
        if (isSorted) {
            arr = input;
        } else {
            arr = input.clone();
        }
        quickSort(arr);
        blackhole.consume(arr);
    }

    void testHeapSort(int[] input, boolean isSorted, Blackhole blackhole) {
        int[] arr;
        if (isSorted) {
            arr = input;
        } else {
            arr = input.clone();
        }
        heapSort(arr);
        blackhole.consume(arr);
    }
    // Merge sort benchmarks

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void mergeSortSorted(Blackhole blackhole) {
        testMergeSort(sortedArrays.get(listSizePower), true, blackhole);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void mergeSortRandom(Blackhole blackhole) {
        testMergeSort(randomArrays.get(listSizePower), false, blackhole);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void mergeSortReverse(Blackhole blackhole) {
        testMergeSort(reverseArrays.get(listSizePower), false, blackhole);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void mergeSortAlmostSorted(Blackhole blackhole) {
        testMergeSort(almostSortedArrays.get(listSizePower), false, blackhole);
    }

    // Bubble sort benchmarks
    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void bubbleSortSorted(Blackhole blackhole) {
        testBubbleSort(sortedArrays.get(listSizePower), true, blackhole);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void bubbleSortRandom(Blackhole blackhole) {
        testBubbleSort(randomArrays.get(listSizePower), false, blackhole);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void bubbleSortReverse(Blackhole blackhole) {
        testBubbleSort(reverseArrays.get(listSizePower), false, blackhole);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void bubbleSortAlmostSorted(Blackhole blackhole) {
        testBubbleSort(almostSortedArrays.get(listSizePower), false, blackhole);
    }

    // Quick sort benchmarks
    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void quickSortSorted(Blackhole blackhole) {
        testQuickSort(sortedArrays.get(listSizePower), true, blackhole);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void quickSortRandom(Blackhole blackhole) {
        testQuickSort(randomArrays.get(listSizePower), false, blackhole);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void quickSortReverse(Blackhole blackhole) {
        testQuickSort(reverseArrays.get(listSizePower), false, blackhole);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void quickSortAlmostSorted(Blackhole blackhole) {
        testQuickSort(almostSortedArrays.get(listSizePower), false, blackhole);
    }

    // Heap sort benchmarks
    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void heapSortSorted(Blackhole blackhole) {
        testHeapSort(sortedArrays.get(listSizePower), true, blackhole);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void heapSortRandom(Blackhole blackhole) {
        testHeapSort(randomArrays.get(listSizePower), false, blackhole);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void heapSortReverse(Blackhole blackhole) {
        testHeapSort(reverseArrays.get(listSizePower), false, blackhole);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void heapSortAlmostSorted(Blackhole blackhole) {
        testHeapSort(almostSortedArrays.get(listSizePower), false, blackhole);
    }

    // Sorting method implementations

    static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    static void mergeSortMerge(int arr[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int left[] = new int[n1];
        int right[] = new int[n2];

        /* Copy data to temp arrays */
        for (int i = 0; i < n1; ++i)
            left[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            right[j] = arr[m + 1 + j];

        // merge the temp arrays togther
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        // copy any remaining elements from left until empty
        while (i < n1) {
            arr[k] = left[i];
            i++;
            k++;
        }

        // copy any remaining elements from right until empty
        while (j < n2) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }

    // Helper function for merge sort that sorts arr[l..r]
    static void mergeSortHelper(int arr[], int l, int r) {
        if (l < r) {
            // Find the middle of arr
            int m = l + (r - l) / 2;

            // sort both halves
            mergeSortHelper(arr, l, m);
            mergeSortHelper(arr, m + 1, r);

            // merge the sorted halves togther
            mergeSortMerge(arr, l, m, r);
        }
    }

    static void mergeSort(int[] arr) {
        // call helper function to sort entire array
        mergeSortHelper(arr, 0, arr.length - 1);
    }

    // heap sort
    public void heapSort(int arr[]) {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    void heapify(int arr[], int n, int i) {
        int maxIndex = i; // Initialize largest as root
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        // If left child is larger than root
        if (l < n && arr[l] > arr[maxIndex])
            maxIndex = l;

        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[maxIndex])
            maxIndex = r;

        // If the largest element is not root then re heapify
        if (maxIndex != i) {
            int temp = arr[i];
            arr[i] = arr[maxIndex];
            arr[maxIndex] = temp;

            heapify(arr, n, maxIndex);
        }
    }

    // quick sort
    public int partitionQuickSort(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low - 1, j = high + 1;
        while (true) {
            do {
                i++;
            } while (arr[i] < pivot);
            do {
                j--;
            } while (arr[j] > pivot);
            if (i >= j)
                return j;
            // swapping the two values
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public void quickSortHelper(int[] arr, int l, int h) {
        if (l < h) {
            int pivot = partitionQuickSort(arr, l, h);
            quickSortHelper(arr, l, pivot);
            quickSortHelper(arr, pivot + 1, h);
        }
    }

    public void quickSort(int[] arr) {
        quickSortHelper(arr, 0, arr.length - 1);
    }
}
