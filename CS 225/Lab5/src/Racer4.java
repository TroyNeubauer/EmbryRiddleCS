
public class Racer4 extends GenericRacer {
	private int turn = 0;

	public Racer4(String inputName) {
		super(inputName);
	}

	@Override
	public void move() {
		int move = (int) (Math.round(45.0 / (Math.pow(2.0, turn))));
		setLocation(getLocation() + Math.max(move, 1));
		turn++;
	}
		
}
