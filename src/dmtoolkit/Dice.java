package dmtoolkit;

import java.util.Random;

public class Dice {

	private int numDice;
	private int numSides;
	private Random diceRand;
	public String diceToRoll;

	public Dice(String die) {
		diceToRoll = die;
		String[] dieParse = die.split("d");
		numDice = Integer.parseInt(dieParse[0]);
		numSides = Integer.parseInt(dieParse[1]);
		diceRand = new Random();
	}

	public int rollDice() {
		int result = 0;
		
		for (int i = 0; i < numDice; ++i) {
            int currentRoll = diceRand.nextInt(numSides)+1;
            result += currentRoll;
        }
        return result;
	}
}
