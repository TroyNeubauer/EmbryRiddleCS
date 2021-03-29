public class RaceGame {
	// declare race end
	private final int RACEEND = 100;
	// declare racers
	// array of GanericRacer with 4 slots
	private GenericRacer[] racers = new GenericRacer[4];

	public RaceGame() {
		// 1. declare all starting points
		// slot 1 is Racer 1
		racers[0] = new Racer1("Urza");
		// slot 2 is Racer 2
		racers[1] = new Racer2("Fenix");
		// slot 3 is Racer 3
		racers[2] = new Racer3("Drek");
		// slot 4 is Racer 4
		racers[3] = new Racer4("Dijkstra");
	}
	
	// check to see if the game is complete
	public boolean gameOver() {
		boolean gameover = false;
		// check every one
		for (int i = 0; i < racers.length; i++) {
			// if someone crossed the finish line 
			if (racers[i].getLocation()>=RACEEND) {
				gameover = true;
			}
		}
		return gameover;
	}

	// move each racer
	public void moveRacers() {
		for (int i = 0; i < racers.length; i++) {
			racers[i].move();
		}
	}
	// getter for racers
	public GenericRacer[] getRacers() {
		return racers;
	}

    public String getWinner() {
        for (GenericRacer racer : getRacers()) {
            if (racer.getLocation() >= RACEEND) {
                return racer.getName();
            }

        }
        return "";
    }
}
