
public class Racer4 extends GenericRacer {

	private int turn = 0;
	
	public Racer4(String inputName) {
		super(inputName);
	}
	

	// override the parents move method
	@Override
	public void move() {

		// declare variables
		double move; 
		// move
		move =  (45/(Math.pow(2,turn++)));
		
		if (move < 1) {
			move = 1;
		}
		
		setLocation(getLocation() + move); 
	}
	
}
