// ***************************************************************
// Name: Keith Garfield
// Date: 9/11/2019
//
// Modify this file as needed. See comments throughout the code.
//
// You will modify the Box class to make the methods calculate 
// the correct results per the homework instructions. 
//
// This file will:
//    -- create an object of the Box class, 
//    -- prompt the user for the length, width, and height of the box, 
//    -- use the methods in the box object to calculate the volume,
//       surface area, and edge length of the box, and
//    -- perform a series of test cases.
//
//
//  Note: This file use very poor user input code in that it does not check
//        for bad user input. We'll fix that in Chapter 12 of the text.
//
// ***************************************************************

import java.util.Scanner;

public class Manager {

	public static void main(String[] args) {
		Manager mgr = new Manager();
		Box box = new Box();

		Scanner input = new Scanner(System.in);

		System.out.print("Input the length of the box: ");
		box.setLength(input.nextDouble());

		System.out.print("Input the height of the box: ");
		box.setHeight(input.nextDouble());

		System.out.print("Input the width of the box: ");
		box.setWidth(input.nextDouble());

		System.out.println(box.getStudentInfo());
		System.out.println("Volume: " + box.calculateVolume());
		System.out.println("Area: " + box.calculateSurfaceArea());
		System.out.println("Edge length: " + box.calculateEdgeLength());
		System.out.println("Largest parameter: " + box.calculateLargestParameter() + "\n\n");

		// Testing your code: Your output must match the expected output from the
		// assignment.
		// Test Case 1: width = 0, length = 0, height = 0
		mgr.testCases(1, 0.0, 0.0, 0.0);
		mgr.testCases(2, 15.0, 10.0, 10.0);
		mgr.testCases(3, 10, 1.0, 1.0);

		// Test Case 2: width = 15, length = 10, height = 10
		// STUDENT to complete this test case, using Test Case 1 as an example.
		// Test Case 3: width = 10, length = 1, height = 1
		// STUDENT to complete this test case, using Test Case 1 as an example.

		input.close();
	}

	public void testCases(int caseID, double bWidth, double bLength, double bHeight) {
		Box box = new Box();

		System.out.println(
				"Test Case " + caseID + ": width = " + bWidth + ", length = " + bLength + ", height = " + bHeight);
		box.setWidth(bWidth);
		box.setLength(bLength);
		box.setHeight(bHeight);

		// STUDENT to complete the test results for Area, Edge Length, and
		// Largest Parameter, using the Volume method call as an example.
		System.out.println("Volume: " + box.calculateVolume());
		System.out.println("Area: " + box.calculateSurfaceArea());
		System.out.println("Edge length: " + box.calculateEdgeLength());
		System.out.println("Largest parameter: " + box.calculateLargestParameter() + "\n\n");
	}

}