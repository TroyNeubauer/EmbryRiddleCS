
public class Racer2 extends GenericRacer {


	public Racer2(String inputName) {
		super(inputName);
	}
	
	
	// override the parents move method
	@Override
	public void move() {

		// declare variables
		double move; 
		// move 2 to 10
		move = randomFrom(2, 11);
		// move
		setLocation(getLocation() + move); 
	}
}
