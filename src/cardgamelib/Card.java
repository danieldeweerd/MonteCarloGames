package cardgamelib;

/**
 * Class representing a single game card, with four possible colors (Diamonds, hearts, clubs or spades)
 * and thirteen possible values (2 to 10, where 11 to 14 represent jack, queen, king and ace)
 */

public class Card {

	private final Type type;
	private final int value;
	private boolean closed = false;                //	Whether the card's type and value can be seen by the player

	public Card(Type type, int number) {
		if (number > 14) {
			throw new IllegalArgumentException();    // 	No cards allowed with a value bigger than 14
		}
		this.type = type;
		this.value = number;
	}

	/**
	 * Check whether this card is greater than, lesser than or the same
	 * as the other card
	 *
	 * @param other card to compare this card to
	 * @return Comparison
	 */
	public Comparison compare(Card other) {
		if (this.value > other.getValue()) return Comparison.GREATER;
		if (this.value < other.getValue()) return Comparison.LESSER;
		else return Comparison.SAME;
	}

	public String toString() {
		return type + " " + value;
	}

	public int getValue() {
		return value;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public boolean isClosed() {
		return closed;
	}
}
