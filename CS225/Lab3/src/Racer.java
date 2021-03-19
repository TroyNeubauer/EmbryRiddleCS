import java.util.Arrays;

public class Racer {

	
	
	public static void main(String[] args) {
		
		// 1. declare all starting points
		double r1 = 0.0, r2 = 0.0, r3 = 0.0, r4 = 0.0;
		
		// 2. while the any racer has not cross the finish line
		final double RACE_DISTANCE = 100.0;
		int turn = 0;
		do {
			// 2-1. take a turn
			// 2-2. move racer1, racer2, racer3, racer4
			r1 += Racer1Move();
			r2 += Racer2Move();
			r3 += Racer3Move();
			r4 += Racer4Move(turn);
			
			turn++;
		} while (r1 < RACE_DISTANCE && r2 < RACE_DISTANCE && r3 < RACE_DISTANCE && r4 < RACE_DISTANCE);
		
		
		// 3. find out who won
		double[] scores = new double[] { r1, r2, r3, r4 };
		long winnerCount = Arrays.stream(scores).filter(value -> (value >= RACE_DISTANCE)).count();
		int winnerIndex = 0;
		for (int i = 1; i < scores.length; i++) {
			if (scores[i] > scores[winnerIndex]) {
				winnerIndex = i;
			}
		}

		if (winnerCount == 1) {
			System.out.println("Racer" + (winnerIndex + 1) + " Won the race with a distance of: " + scores[winnerIndex] + " after " + turn + " turns");
		} else {
			System.out.println("Its a tie!(" + winnerCount + " racers crossed the finish line in the same turn) But racer " + (winnerIndex + 1) + " Went the furthest with a distance of " + scores[winnerIndex] + " after " + turn + " turns");
		}

		System.out.println("Final standings:");
		for (int i = 0; i < scores.length; i++) {
			System.out.println("\tRacer" + (i + 1) + " distance: " + scores[i]);
		}
	}
	
	// example of a random number generator given a lower and higher bound
	public static int randomFrom (int low, int high) {
		// (int) is casting since Math.random() return a double and randNum is an int
		return (int) (Math.random()*(high-low) + low);		
	}
	
	
	// example for Racer1 
	public static int Racer1Move() {
		return randomFrom(0, 2) == 0 ? 4 : 8;		
	}

	public static int Racer2Move() {
		return randomFrom(2, 10);
	}
	
	public static double Racer3Move() {
		if (randomFrom(0, 2) == 0)
			return randomFrom(0, 10);
		else
			return 7.0 * Math.cos(7);
	}

	public static double Racer4Move(int turn) {
		return Math.max(1.0, 45 / Math.pow(2, turn));
	}

	

}
