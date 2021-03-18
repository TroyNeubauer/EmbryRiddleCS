
public class Racer3 extends GenericRacer  {

	
	public Racer3(String inputName) {
		super(inputName);
	}

	@Override
	public void move() {
		int move;
		if (RaceGame.randomFrom(0, 2) == 1) {
			move = RaceGame.randomFrom(0, 11);
		} else {
			move = (int) (7 * (Math.acos(7)));
		}
		setLocation(getLocation() + move);
	}
		

}
