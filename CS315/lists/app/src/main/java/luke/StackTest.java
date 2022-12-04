package luke;

/*
 * StackTest.java
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
 * A class for running unit tests on Stack.
 */
public class StackTest {

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
	 * A adapter for calling arbitrary functions and storing them in a single data type.
	 */
	interface TestFunc {
		public boolean test();
	}

	/**
	 * Performs all of the tests on Stack, specifically for more robust testing, a Stack where Item = String.
	 *
	 * @return A boolean representing if the tests succeeded or not, true if they did, false if they failed.
	 */
	public static boolean testStack() {

		TestItem[] tests = new TestItem[] {
			new TestItem(new TestFunc() { public boolean test() { return StackTest.isEmpty(); } }, "isEmpty"),
			new TestItem(new TestFunc() { public boolean test() { return StackTest.size(); } }, "size"),
			new TestItem(new TestFunc() { public boolean test() { return StackTest.pushPop(); } }, "pushPop"),
			new TestItem(new TestFunc() { public boolean test() { return StackTest.iterable(); } }, "iterable" ),
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
	 * A unit test that tests the isEmpty method on Stack.
	 * <p>
	 * Also tests/relies upon Stack.push() and Stack.isEmpty()
	 *
	 * @return True if the test passed, false otherwise.
	 */
	private static boolean isEmpty() {
		Stack<String> s = new Stack<String>();

		if (!s.isEmpty()) {
			return false;
		}

		s.push("a");

		return !s.isEmpty();
	}

	/**
	 * A unit test that tests the size method on Stack.
	 * <p>
	 * Also tests/relies upon Stack.push()
	 *
	 * @return True if the test passed, false otherwise.
	 */
	private static boolean size() {
		Stack<String> s = new Stack<String>();

		if (s.size() != 0) {
			return false;
		}

		s.push("a");

		if (s.size() != 1) {
			return false;
		}

		s.push("b");

		if (s.size() != 2) {
			return false;
		}

		s.push("c");
		s.push("d");
		s.push("e");
		s.push("f");

		return s.size() == 6;
	}

	/**
	 * A unit test that tests the pop method on Stack.
	 * <p>
	 * Also tests/relies upon Stack.push() and Stack.isEmpty()
	 *
	 * @return True if the test passed, false otherwise.
	 */
	private static boolean pushPop() {
		Stack<String> s = new Stack<String>();

		s.push("1");

		if (!s.pop().equals("1")) {
			return false;
		}

		s.push("1");
		s.push("2");

		if (!s.pop().equals("2")) {
			return false;
		}

		s.pop();

		s.push("a");
		s.push("b");
		s.push("c");
		s.push("d");
		s.push("e");

		String[] expected = new String[] {"e", "d", "c", "b", "a"};

		for (int i = 0; i < expected.length; i++) {
			if (!expected[i].equals(s.pop())) {
				return false;
			}
		}

		if (s.pop() != null) {
			return false;
		}
		
		return s.isEmpty();
	}

	/**
	 * A unit test that tests the Iterable implementation on Stack.
	 * <p>
	 * Also tests/relies upon Stack.pop()
	 *
	 * @return True if the test passed, false otherwise.
	 */
	private static boolean iterable() {
		Stack<String> s = new Stack<>();

		s.push("a");
		s.push("b");
		s.push("c");
		s.push("d");
		s.push("e");
		s.push("f");

		String[] expected = new String[] {"f", "e", "d", "c", "b", "a"};
		Iterator<String> expectedI = Arrays.stream(expected).iterator();

		for (String r : s) {
			String next = expectedI.next();
			if (!r.equals(next)) {
				System.out.println(r + " != " + next);
				return false;
			}
		}

		return true;
	}
}
