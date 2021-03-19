import java.util.Arrays;

public class RaceGame {
	static int turn = 0;
	final static int RACE_DISTANCE = 100;


	public static void main(String[] args) {

		// 1. declare all starting points
		Racer1 racer1 = new Racer1();
		Racer2 racer2 = new Racer2();
		Racer3 racer3 = new Racer3();
		Racer4 racer4 = new Racer4();

		// 2. while the any racer has not cross the finish line
		while (racer1.getLocation() < RACE_DISTANCE && racer2.getLocation() < RACE_DISTANCE && racer3.getLocation() < RACE_DISTANCE && racer4.getLocation() < RACE_DISTANCE) {
			turn += 1;
			racer1.move();
			racer2.move();
			racer3.move();
			racer4.move(turn);

			System.out.println("\nTurn: " + turn);
			System.out.println(racer1.getName() + " is at:" + racer1.getLocation());
			System.out.println(racer2.getName() + " is at:" + racer2.getLocation());
			System.out.println(racer3.getName() + " is at:" + racer3.getLocation());
			System.out.println(racer4.getName() + " is at:" + racer4.getLocation());

		}

		// 3. find out who won
		double[] scores = new double[] { racer1.getLocation(), racer2.getLocation(), racer3.getLocation(), racer4.getLocation() };
		String[] names = new String[] { racer1.getName(), racer2.getName(), racer3.getName(), racer4.getName() };
		long winnerCount = Arrays.stream(scores).filter(value -> (value >= RACE_DISTANCE)).count();
		int winnerIndex = 0;
		for (int i = 1; i < scores.length; i++) {
			if (scores[i] > scores[winnerIndex]) {
				winnerIndex = i;
			}
		}

		if (winnerCount == 1) {
			System.out.println("Racer " + names[winnerIndex] + " Won the race with a distance of: " + scores[winnerIndex] + " after " + turn + " turns");
		} else {
			System.out.println("Its a tie!(" + winnerCount + " racers crossed the finish line in the same turn) But racer " + names[winnerIndex] + " Went the furthest with a distance of " + scores[winnerIndex] + " after " + turn + " turns");
		}

		System.out.println("Final standings:");
		for (int i = 0; i < scores.length; i++) {
			System.out.println("\tRacer " + names[i] + " distance: " + scores[i]);
		}
	}

	public static int randomFrom(int low, int high) {
		// (int) is casting since Math.random() return a double and randNum is an int
		return (int) (Math.random() * (high - low) + low);
	}


}
