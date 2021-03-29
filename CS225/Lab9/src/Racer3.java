
public class Racer3 extends GenericRacer  {

	
	public Racer3(String inputName) {
		super(inputName);
	}
	
	

	// override the parents move method
	@Override
	public void move() {

		// declare variables
		double move; 
		// flip a coin
		int randNum = randomFrom(0, 2);

		// if 1 move 0 to 10
		if (randNum == 1) {
			move =  randomFrom(0,11);

		// if 0 move 7 * Math.cos(7)
		} else {
			move = 7 * Math.cos(7);		
		}
		setLocation(getLocation() + move); 
	}
	

}
