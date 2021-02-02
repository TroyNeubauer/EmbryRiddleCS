import java.util.Scanner; 
 
public class LabOneWithInput { 
 
 	public static void main(String[] args) { 
 	 
 	 	// Create a scanner for command line input 
 	 	Scanner in = new Scanner(System.in); 
 	 	 
 	 	// Prompt for, and store, the name 
 	 	System.out.println("What is your name?"); 
 	 	String name = in.nextLine(); 
 	 	 
 	 	// Print the name back 
 	 	System.out.println("Hello, " + name + "!\n"); 
 	 	 
 	 	// Close the scanner 
 	 	in.close(); 
 
 	} 
 
}  
