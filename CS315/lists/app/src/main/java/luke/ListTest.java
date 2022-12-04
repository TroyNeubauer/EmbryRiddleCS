package luke;

/*
 * ListTest.java
 *
 * Author: Luke Newcomb
 * Submission date: Sep. 27, 2022
 * References:
 *
 */

import java.util.Arrays;
import java.util.Iterator;

/**
 * A class for running unit tests on any class that implements the List interface.
 */
public class ListTest {

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
		<L extends List<String> & Iterable<String>> boolean test(L l);
	}

	/**
	 * Performs all the tests on a class implementing List, specifically for more robust testing, a List where Item = String.
	 *
	 * @param l The list whose implementation will be tested for bugs.
	 * @return A boolean representing if the tests succeeded or not, true if they did, false if they failed.
	 */
	public static <L extends List<String> & Iterable<String>> boolean testList(L l) {

		TestItem[] tests = new TestItem[] {
			new TestItem(new TestFunc() { public <L extends List<String> & Iterable<String>> boolean test(L l) { return ListTest.isEmpty(l); } }, "isEmpty"),
			new TestItem(new TestFunc() { public <L extends List<String> & Iterable<String>> boolean test(L l) { return ListTest.addToHead(l); } }, "addToHead"),
			new TestItem(new TestFunc() { public <L extends List<String> & Iterable<String>> boolean test(L l) { return ListTest.addToTail(l); } }, "addToTail"),
			new TestItem(new TestFunc() { public <L extends List<String> & Iterable<String>> boolean test(L l) { return ListTest.addAt(l); } }, "addAt"),
			new TestItem(new TestFunc() { public <L extends List<String> & Iterable<String>> boolean test(L l) { return ListTest.toArrayFwd(l); } }, "toArrayFwd"),
			new TestItem(new TestFunc() { public <L extends List<String> & Iterable<String>> boolean test(L l) { return ListTest.toArrayRev(l); } }, "toArrayRev"),
			new TestItem(new TestFunc() { public <L extends List<String> & Iterable<String>> boolean test(L l) { return ListTest.deleteAt(l); } }, "deleteAt"),
			new TestItem(new TestFunc() { public <L extends List<String> & Iterable<String>> boolean test(L l) { return ListTest.getAt(l); } }, "getAt"),
			new TestItem(new TestFunc() {public <L extends List<String> & Iterable<String>> boolean test(L l) { return ListTest.iterable(l); } }, "iterable"),
		};

		boolean allPassed = true;

		for (TestItem test: tests) {
			allPassed = debugTest(test.func.test(l), test.testName) && allPassed;
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
	 * A unit test that tests the isEmpty method on List.
	 * <p>
	 * Also tests/relies upon List.addtoHead() and List.clear()
	 *
	 * @param l The list being tested.
	 * @return True if the test passed, false otherwise.
	 */
	private static boolean isEmpty(List<String> l) {
		if (!l.isEmpty()) {
			return false;
		}

		l.addToHead("a");

		if (l.isEmpty()) {
			return false;
		}

		l.clear();

		return l.isEmpty();
	}

	/**
	 * A unit test that tests the addtoHead method on List.
	 * <p>
	 * Also tests/relies upon List.length(), List.isEmpty(), and List.clear()
	 *
	 * @param l The list being tested.
	 * @return True if the test passed, false otherwise.
	 */
	private static boolean addToHead(List<String> l) {
		l.addToHead("c");
		l.addToHead("b");
		l.addToHead("a");

		String[] expected = new String[] {"a", "b", "c"};

		if (l.length() != 3) {
			return false;
		}

		for (int i = 0; i < expected.length; i++) {
			if (!expected[i].equals(l.getAt(i))) {
				return false;
			}
		}

		l.clear();

		return l.isEmpty();
	}

	/**
	 * A unit test that tests the addtotail method on List.
	 * <p>
	 * Also tests/relies upon List.length(), List.isEmpty(), and List.clear()
	 *
	 * @param l The list being tested.
	 * @return True if the test passed, false otherwise.
	 */
	private static boolean addToTail(List<String> l) {
		l.addToTail("a");
		l.addToTail("b");
		l.addToTail("c");

		String[] expected = new String[] {"a", "b", "c"};

		if (l.length() != 3) {
			return false;
		}

		for (int i = 0; i < expected.length; i++) {
			if (!expected[i].equals(l.getAt(i))) {
				return false;
			}
		}

		l.clear();

		return l.isEmpty();
	}

	/**
	 * A unit test that tests the addAt method on List.
	 * <p>
	 * Also tests/relies upon List.getAt(), List.length(), List.isEmpty(), and List.clear()
	 *
	 * @param l The list being tested.
	 * @return True if the test passed, false otherwise.
	 */
	private static boolean addAt(List<String> l) {
		l.addAt(-2, "1");

		if (!l.getAt(0).equals("1")) {
			return false;
		}

		l.clear();

		l.addAt(4, "2");

		if (!l.getAt(0).equals("2")) {
			return false;
		}

		l.addAt(0, "1");
		l.addAt(6, "2");
		l.addAt(-2, "0");
		l.addAt(1, "0.5");

		if (l.length() != 5) {
			return false;
		}

		String[] expected = new String[] {"0", "0.5", "1", "2", "2"};

		for (int i = 0; i < expected.length; i++) {
			if (!expected[i].equals(l.getAt(i))) {
				return false;
			}
		}

		l.clear();

		return l.isEmpty();
	}

	/**
	 * A unit test that tests the toArrayFwd method on List.
	 * <p>
	 * Also tests/relies upon List.addToTail(), List.isEmpty(), and List.clear()
	 *
	 * @param l The list being tested.
	 * @return True if the test passed, false otherwise.
	 */
	private static boolean toArrayFwd(List<String> l) {
		l.addToTail("a");
		l.addToTail("b");
		l.addToTail("c");
		l.addToTail("d");
		l.addToTail("e");

		String[] expected = new String[] {"a", "b", "c", "d", "e"};
		Object[] result = l.toArrayFwd();

		if (expected.length != result.length) {
			return false;
		}

		for (int i = 0; i < expected.length; i++) {
			if (!expected[i].equals(result[i])) {
				return false;
			}
		}

		l.clear();

		return l.isEmpty();
	}

	/**
	 * A unit test that tests the toArrayRev method on List.
	 * <p>
	 * Also tests/relies upon List.addToTail(), List.isEmpty(), and List.clear()
	 *
	 * @param l The list being tested.
	 * @return True if the test passed, false otherwise.
	 */
	private static boolean toArrayRev(List<String> l) {
		l.addToTail("a");
		l.addToTail("b");
		l.addToTail("c");
		l.addToTail("d");
		l.addToTail("e");

		String[] expected = new String[] {"e", "d", "c", "b", "a"};
		Object[] result = l.toArrayRev();

		if (expected.length != result.length) {
			return false;
		}

		for (int i = 0; i < expected.length; i++) {
			if (!expected[i].equals(result[i])) {
				return false;
			}
		}

		l.clear();

		return l.isEmpty();
	}

	/**
	 * A unit test that tests the deleteAt method on List.
	 * <p>
	 * Also tests/relies upon List.addToTail(), List.addToHead(), List.toArrayFwd(), List.toArrayRev(), List.isEmpty(), and List.clear()
	 *
	 * @param l The list being tested.
	 * @return True if the test passed, false otherwise.
	 */
	private static boolean deleteAt(List<String> l) {
		l.deleteAt(0);

		if (!l.isEmpty()) {
			return false;
		}

		l.addToTail("1");
		l.deleteAt(0);
		
		if (!l.isEmpty()) {
			return false;
		}

		l.addToTail("2");

		l.deleteAt(-1);
		l.deleteAt(2);

		if (l.isEmpty()) {
			return false;
		}

		l.addToTail("3");

		l.deleteAt(0);

		if (l.length() != 1 || !l.getAt(0).equals("3")) {
			return false;
		}

		l.addToTail("4");

		l.deleteAt(1);

		if (!l.getAt(0).equals("3") || l.length() != 1) {
			return false;
		}

		l.addToTail("4");
		l.addToTail("5");

		l.deleteAt(1);

		if (!l.getAt(0).equals("3") || !l.getAt(1).equals("5") || l.length() != 2) {
			return false;
		}

		l.clear();

		l.addToHead("a");
		l.addToTail("b");
		l.addToTail("c");
		l.addToTail("d");
		l.addToTail("e");
		l.addToTail("f");

		l.deleteAt(0);
		l.deleteAt(2);
		l.deleteAt(3);

		String[] expected = new String[] {"b", "c", "e"};
		Object[] resultFwd = l.toArrayFwd();
		Object[] resultRev = l.toArrayRev();

		if (expected.length != resultFwd.length || expected.length != resultRev.length) {
			return false;
		}

		for (int i = 0; i < expected.length; i++) {
			if (!expected[i].equals(resultFwd[i])) {
				return false;
			}
			if (!expected[expected.length - 1 - i].equals(resultRev[i])) {
				return false;
			}
		}

		l.clear();

		return l.isEmpty();
	}

	/**
	 * A unit test that tests the getAt method on List.
	 * <p>
	 * Also tests/relies upon List.addToTail(), List.getAt(), List.isEmpty(), and List.clear()
	 *
	 * @param l The list being tested.
	 * @return True if the test passed, false otherwise.
	 */
	private static boolean getAt(List<String> l) {
		l.addToTail("a");
		l.addToTail("b");
		l.addToTail("c");
		l.addToTail("d");
		l.addToTail("e");

		String[] expected = new String[] {"a", "b", "c", "d", "e"};

		for (int i = 0; i < expected.length; i++) {
			if (!expected[i].equals(l.getAt(i))) {
				return false;
			}
		}

		if (l.getAt(-1) != null) {
			return false;
		}

		if (l.getAt(42) != null) {
			return false;
		}

		l.clear();

		if (l.getAt(0) != null) {
			return false;
		}

		return l.isEmpty();
	}

	/**
	 * A unit test that tests the Iterable implementation on List.
	 * <p>
	 * Also tests/relies upon List.getAt()
	 *
	 * @param l The list being tested.
	 * @return True if the test passed, false otherwise.
	 */
	private static <L extends List<String> & Iterable<String>> boolean iterable(L l) {
		l.addToTail("a");
		l.addToTail("b");
		l.addToTail("c");
		l.addToTail("d");
		l.addToTail("e");

		String[] expected = new String[] {"a", "b", "c", "d", "e"};
		Iterator<String> expectedI = Arrays.stream(expected).iterator();

		for (String s : l) {
			if (!s.equals(expectedI.next())) {
				return false;
			}
		}

		l.clear();

		return l.isEmpty();
	}
}
