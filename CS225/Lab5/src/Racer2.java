
public class Racer2 extends GenericRacer {


	public Racer2(String inputName) {
		super(inputName);
	}

	@Override
	public void move() {
		setLocation(getLocation() + RaceGame.randomFrom(2, 11));
		
	}
	
	
	
}
