
public class Racer1 {
	
	// declare location
	private int location;
	// declare name
	private String name = "Urza";
		
	public void move() {

		// if 1 move 4
		int move;
		if (RaceGame.randomFrom(0, 2) == 1) {
			move = 4;

			// if 0 move 8
		} else {
			move = 8;
		}
		location += move;
	}
	
	// get the location of racer 1
	public int getLocation() {
		return location;
	}
	// set the location of racer 1
	public void setLocation(int location) {
		this.location = location;
	}
	// get the name of Racer 1
	public String getName() {
		return name;
	}

	// set the name of Racer 1
	public void setName(String name) {
		this.name = name;
	}
}
