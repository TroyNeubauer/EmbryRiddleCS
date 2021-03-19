
public class Lab3PostLab {
	
	public static void main(String[] args) {
		System.out.println("17 F = " + fahrenheitToCelsius(47) + "C");
		System.out.println("39K = " + kelvinToFahrenheit(39) + "F");
	}
	
	public static double fahrenheitToCelsius(double f) {
		return (f - 32.0) * (5.0 / 9.0);
	}

	
	public static double kelvinToFahrenheit(double k) {
		return (k - 273.15) * (9.0 / 5.0) + 32.0; 
	}
	

	

}
