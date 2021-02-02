import java.util.Scanner;

public class SurfaceAreaCalculator {
	
	private static double getDouble(Scanner scanner, String message, String errorMessage)
	{
		while (true)
		{
			try
			{
				System.out.print(message);
				double value = scanner.nextDouble();
				return value;
			}
			catch (Exception e)
			{
				scanner.nextLine();
				System.out.println();
			}
			System.out.println(errorMessage);
		}
	}
	
	private static void runIteration(double length, double width, double height, String message, double expectedSA, double expectedSASI)
	{
		System.out.println(message);
		double sa = 2 * (length * width + width * height + length * height);
		System.out.println("Surface area is " + sa + " in^2");
		
		final double IN_TO_CM = 2.54;
		System.out.println("SI Surface area is " + (sa * IN_TO_CM * IN_TO_CM) + " cm^2");
		
		if (expectedSA != -1)
		{
			//We are in a test case. Compare the expected value with the calculated one
			if (sa != expectedSA)
			{
				System.err.println("Test case \"" + message + "\" failed! Expected SA " + expectedSA + " but got " + sa);
				System.exit(1);
			}
			else
			{
				System.out.println("Test case passed");
			}
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		System.out.println("Runnign built in test cases...");
		runIteration(0, 0, 0, "Test case 1", 0, 0);
		runIteration(15, 10, 0, "Test case 2", 300, 1935.48);
		runIteration(10, 1, 1, "Test case 3", 42, 270.97);

		
		System.out.println("Entening user input phase...");
		Scanner scanner = new Scanner(System.in);
		double length = getDouble(scanner, "Please enter the length of your packag in inches: ", "Please enter a decimal number");
		double width = getDouble(scanner, "Please enter the width of your packag in inches: ", "Please enter a decimal number");
		double hegith = getDouble(scanner, "Please enter the height of your packag in inches: ", "Please enter a decimal number");
		
		runIteration(length, width, hegith, "User results: ", -1, -1);
		
		scanner.close();
	}
}
