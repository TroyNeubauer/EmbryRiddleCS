
public class Racer1 extends GenericRacer {
	
	public Racer1(String name) {
		super(name);
	}

	// override the parents move method
	@Override
	public void move() {

		// declare variables
		int move; 
		// flip a coin
		int randNum = randomFrom(0, 2);

		// if 1 move 4
		if (randNum == 1) {
			move = 4;

		// if 0 move 8
		} else {
			move = 8;
		}
		setLocation(getLocation() + move); 
	}

}
