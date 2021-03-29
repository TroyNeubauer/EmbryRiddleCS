
public class GenericRacer {

	// declare location
	private double location;
	// declare name
	private String name;

	public GenericRacer(String inputName) {
		//set location to zero when GenericRacer is made
		location = 0;
		//set name to inputed name
		name = inputName;
	}
	
	public void move() {
		//do nothing
	}
	
	// get the location  
	public double getLocation() {
		return location;
	}
	// set the location  
	public void setLocation(double location) {
		this.location = location;
	}
	// get the name  
	public String getName() {
		return name;
	}
	// set the name
	public void setName(String name) {
		this.name = name;
	}

	// example of a random number generator given a lower and higher bound
	public int randomFrom (int low, int high) {

		int randNum = 0;

		// (int) is casting since Math.random() return a double and randNum is an int
		randNum = (int) (Math.random()*(high-low) + low);

		return randNum;
	}
	 
	 
}
