public class Racer3 {
	String name = "Drek";
	int move;
	int location;

	public void move() {
		if (RaceGame.randomFrom(0, 2) == 1) {
			move = RaceGame.randomFrom(0, 11);
		} else {
			move = (int) (7 * (Math.acos(7)));
		}
		location = location + move;
	}
	
	public int getLocation() {
		return location;
	}
	
	public String getName() {
		return name;
	}

}
