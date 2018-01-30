package test;

import java.util.Scanner;

import dmtoolkit.Dice;

public class DiceTest {

	public static void main(String[] args) {
		
		Scanner kb = new Scanner(System.in);
		try {
			System.out.println("What dice are you rolling? Input as numdDice:d:numSides");
			
			Dice testDice = new Dice(kb.nextLine());
			
			System.out.println("Rolling " + testDice.diceToRoll + "...");
			System.out.println("Result: " + testDice.rollDice());
		} finally {
			kb.close();
		}
	}

}