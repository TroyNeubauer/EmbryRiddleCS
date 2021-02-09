// ***************************************************************
// Name: Troy Neubauer
// Date: 2/8/2021
//
// CS 225, Section 1, Homework 2
//
//  For this assignment, the STUDENT is required to create methods
//  to calculate the box parameters (volume, surface area, edge length),
//  and then find the largest of these parameters. 
//
//  The calculateVolume Method is provided for you as an example of how 
//  to define a method. 
//
//  The STUDENT is expected to complete the calculateVolume method, and
//  then create similar methods to calculate the surface area, edge, length
//  and find the largest parameter.
//
//  Do not run this file. There is no main method. Don't make one.
//  Run the Manager class instead. 
//
//  Once completed, this file will:
//    -- store the length, width, and height parameters for a box,
//    -- provide student name and course section (either -01 or -02)
//    -- calculate the box parameters (volume, surface area, edge length), and
//    -- calculate the largest parameter.
// ***************************************************************

public class Box {

	private double length, height, width;

	public String getStudentInfo() {
		return "Troy Neubauer CS 225, Section 01\n\n";
	}

	public double calculateVolume() {
		return length * width * height;
	}

	public double calculateSurfaceArea() {
		return 2 * (length * height + width * height + length * width);
	}

	public double calculateEdgeLength() {
		return 4 * (length + height + width);
	}

	public double calculateLargestParameter() {
		return Math.max(Math.max(calculateVolume(), calculateSurfaceArea()), calculateEdgeLength());
	}

	// ******* You don't need to modify anything below this line.
	// ******* This section contains the mundane "setter" and "getter" methods.
	// ******* Eclipse has a tool to create these very easily.

	// ****** These are the "setters."
	public void setLength(double newLength) {
		length = newLength;
	}

	public void setHeight(double newHeight) {
		height = newHeight;
	}

	public void setWidth(double newWidth) {
		width = newWidth;
	}

	// ****** These are the "getters."
	public double getLength() {
		return length;
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}
}