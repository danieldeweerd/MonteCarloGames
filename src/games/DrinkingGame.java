package games;

/**
 * Abstract class for the Monte Carlo Simulator to use
 * Currently there's only one drinking game in the project but this could be expanded later on
 */

public abstract class DrinkingGame {

	/**
	 * Play the game once
	 */
	public abstract void run();

	/**
	 * @return The amount of sips that should have been taken while playing the game
	 */
	public abstract int getSips();

	/**
	 * @return the name of the game
	 */
	public abstract String getName();

}
