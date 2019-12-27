package games;

import cardgamelib.CardStack;

/**
 * Abstract class for the Monte Carlo Simulator to use
 * Currently there's only one drinking game in the project but this could be expanded later on
 */

public abstract class DrinkingGame {

	CardStack stack = null;

	/**
	 * Play the game once
	 */
	public abstract void run();

	/**
	 * @return The amount of sips that should have been taken while playing the game
	 */
	public abstract int getSips();

	/**
	 * @return The amount of cards drawn in the game;
	 */
	public abstract int getNumberOfCards();

	/**
	 * @return the name of the game
	 */
	public abstract String getName();


}
