
/*
CS 315: Programming Assignment #1

This first programming assignment serves to not only implement the data structures discussed within the module, but to also practice test-driven design.

You will be asked to implement three data structures.

    A singly or doubly-linked list
    A list-based queue or array-based
    A list-based stack or array-based

Each data structure that you implement must follow the API presented within Module 1. i.e. the Java interfaces for Stack, Queue, and List.

You shall follow test driven design. You should create test client(s) to test each of the algorithms you implement to demonstrate that it works. 

    A test client should be a separate java file (i.e. a separate class) with methods implemented to test the correctness of your data structure.
    Each test should ensure that the expected states of the data structure are tested including operations performed on an empty data structure, the first item of the data structure, the last item of the data structure, etc.
    For instance, if you are implementing an add method to a stack, you should test against the case that the stack is initially empty, the stack has at least one item in it, and the stack is full.
    Test-driven design expects for you to define your test cases prior to implementing your solution. You then build your solution to pass the tests.
        You should also build incrementally.
        Build part of your algorithm to solve the next test, pass the test, and then move on to the next task.
        This keeps you from building something complex and then debugging it, which is much harder.

Linked List (100 pts)

    Implement a singly or doubly linked list in Java. Implement the interface presented in class (List.java) defining each of the methods and the internal structures of your linked list.
    Implement a test client to assess whether you’ve successfully implemented your list.

Queue (100 pts)

    Implement an array-based or list-based FIFO queue that implements the API on page 121 of the textbook.
    Implement a test client to assess whether you’ve successfully implemented your queue.

Stack (100 pts)

    Implement an array-based or list-based stack data structure. Your stack implement the API defined on page 121 of the textbook.
    Implement a test client to assess whether you’ve successfully implemented your stack.

Grading Criteria:

Each part will be graded out of 100 points, which will then be graded using the percentages listed above.

    Data structure: 40 pts
        Did the correct data structure get implemented?
        Was it implemented correctly?
        Was it implemented completely?
    Test client: 40 points
        Did the test client evaluate each method implemented?
        Did it test each implemented method for all special cases relevant to that method?
    Code style: 15 pts
        Each file should include its author’s name, the submission date, and cite any external source code referenced when implementing your solution.
        Each method should have a comment block above it defining briefly the purpose of the method, its inputs, and its outputs.
        Source code should make proper use of whitespace including proper tabbing and use of new lines. It should be consistent and readable.
        Uses meaningful variable names.
    Screenshots of test client output: 5pts
*/
class Lists {
    public static void main(String[] args) {

    }

    static class Node<V> {
        V node;
    }

    static class LinkedList {
        private Node<V> head;
        private Node<V> tail; 

    }
}
