package cardgamelib;

/**
 * Abstract class for the Monte Carlo Simulator to use
 * Currently there's only one drinking game in the project but this could be expanded later on
 */

public abstract class DrinkingGame {

	/**
	 * @return The amount of sips that should have been taken while playing the game
	 */
	public abstract int run();

	public abstract String getName();
}
