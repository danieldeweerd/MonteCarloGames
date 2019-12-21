package cardgamelib;

/**
 * Abstract class for the Monte Carlo Simulator to use
 * Currently there's only one game in the project but this
 * could be expanded later on
 */

public abstract class Game {

	/**
	 * @return The amount of sips that should have been taken while playing the game
	 */
	public abstract int run();

	public abstract String getName();
}
