package luke;

/*
 * QueueTest.java
 *
 * Author: Luke Newcomb
 * Submission date: Sep. 27, 2022
 * References:
 *
 */

import java.util.Arrays;
import java.util.Iterator;

/**
 * A class for running unit tests on Queue.
 */
public class QueueTest {

	/**
	 * A unit test and its associated function, and name.
	 */
	static class TestItem {
		TestFunc func;
		String testName;

		public TestItem(TestFunc func, String testName) {
			this.func = func;
			this.testName = testName;
		}
	}

	/**
	 * An adapter for calling arbitrary functions and storing them in a single data type.
	 */
	interface TestFunc {
		public boolean test();
	}

	/**
	 * Performs all the tests on Queue, specifically for more robust testing, a Queue where Item = String.
	 *
	 * @return A boolean representing if the tests succeeded or not, true if they did, false if they failed.
	 */
	public static boolean testQueue() {

		TestItem[] tests = new TestItem[] {
			new TestItem(new TestFunc() { public boolean test() { return QueueTest.enqueue(); } }, "enqueue"),
			new TestItem(new TestFunc() { public boolean test() { return QueueTest.isEmpty(); } }, "isEmpty"),
			new TestItem(new TestFunc() { public boolean test() { return QueueTest.size(); } }, "size"),
			new TestItem(new TestFunc() { public boolean test() { return QueueTest.dequeue(); } }, "dequeue"),
			new TestItem(new TestFunc() { public boolean test() { return QueueTest.iterable(); } }, "iterable" ),
		};

		boolean allPassed = true;

		for (TestItem test: tests) {
			allPassed = debugTest(test.func.test(), test.testName) && allPassed;
		}
		
		return allPassed;
	}

	/**
	 * Prints a debug message for a specific unit test.
	 *
	 * @param success  If the test has succeeded or not.
	 * @param testName The name of the test that just ran.
	 * @return The value of the success boolean.
	 */
	private static boolean debugTest(boolean success, String testName) {
		System.out.println("Test " + testName + " ... " + (success ? "ok" : "failed"));

		return success;
	}

	/**
	 * A unit test that tests the enqueue method on Queue.
	 * <p>
	 * Also tests/relies upon Queue.toArray()
	 *
	 * @return True if the test passed, false otherwise.
	 */
	private static boolean enqueue() {
		Queue<String> q = new Queue<String>();

		q.enqueue("a");
		q.enqueue("b");
		q.enqueue("c");
		q.enqueue("d");
		q.enqueue("e");

		String[] expected = new String[] {"a", "b", "c", "d", "e"};
		Iterator<String> expectedI = Arrays.stream(expected).iterator();

		if (expected.length != q.size()) {
			return false;
		}

		for (String s : q) {
			String next = expectedI.next();
			if (!s.equals(next)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * A unit test that tests the isEmpty method on Queue.
	 * <p>
	 * Also tests/relies upon Queue.enqueue()
	 *
	 * @return True if the test passed, false otherwise.
	 */
	private static boolean isEmpty() {
		Queue<String> q = new Queue<String>();

		if (!q.isEmpty()) {
			return false;
		}

		q.enqueue("a");

		return !q.isEmpty();
	}

	/**
	 * A unit test that tests the size method on Queue.
	 * <p>
	 * Also tests/relies upon Queue.enqueue()
	 *
	 * @return True if the test passed, false otherwise.
	 */
	private static boolean size() {
		Queue<String> q = new Queue<String>();

		if (q.size() != 0) {
			return false;
		}

		q.enqueue("a");

		if (q.size() != 1) {
			return false;
		}

		q.enqueue("b");

		if (q.size() != 2) {
			return false;
		}

		q.enqueue("c");
		q.enqueue("d");
		q.enqueue("e");
		q.enqueue("f");

		return q.size() == 6;
	}

	/**
	 * A unit test that tests the dequeue method on Queue.
	 * <p>
	 * Also tests/relies upon Queue.enqueue(), Queue.isEmpty()
	 *
	 * @return True if the test passed, false otherwise.
	 */
	private static boolean dequeue() {
		Queue<String> q = new Queue<String>();

		q.enqueue("a");
		if (!q.dequeue().equals("a")) {
			return false;
		}

		if (!q.isEmpty()) {
			return false;
		}

		q.enqueue("b");
		if (!q.dequeue().equals("b")) {
			return false;
		}

		if (!q.isEmpty()) {
			return false;
		}

		q.enqueue("c");
		q.enqueue("d");
		q.enqueue("e");
		q.enqueue("f");

		String[] expected = new String[] {"c", "d", "e", "f"};

		for (String s : expected) {
			if (!s.equals(q.dequeue())) {
				return false;
			}
		}

		if (q.dequeue() != null) {
			return false;
		}

		return q.isEmpty();
	}

	/**
	 * A unit test that tests the Iterable implementation on Queue.
	 * <p>
	 * Also tests/relies upon Queue.dequeue()
	 *
	 * @return True if the test passed, false otherwise.
	 */
	private static boolean iterable() {
		Queue<String> q = new Queue<>();

		q.enqueue("a");
		q.enqueue("b");
		q.enqueue("c");
		q.enqueue("d");
		q.enqueue("e");
		q.enqueue("f");

		String[] expected = new String[] {"a", "b", "c", "d", "e", "f"};
		Iterator<String> expectedI = Arrays.stream(expected).iterator();

		for (String s : q) {
			if (!s.equals(expectedI.next())) {
				return false;
			}
		}

		return true;
	}
}
