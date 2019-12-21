package cardgamelib;

import java.util.Collections;
import java.util.Stack;

/**
 * Class representing a full 52-card stack
 */

public class CardStack {
	private Stack<Card> stack = new Stack<Card>();

	public CardStack() {
		addCards();
	}

	/**
	 * Draw a card from the card stack
	 *
	 * @return a randomly drawn card
	 */
	public Card draw() {
		Card ret = stack.pop();
		if (stack.size() == 0)            //	Create a new full card stack if the stack is empty
			addCards();
		return ret;
	}

	/**
	 * Add a copy of every possible card to the stack and shuffle it
	 */
	private void addCards() {
		for (int i = 2; i < 15; i++) {
			stack.add(new Card(Type.HEARTS, i));
			stack.add(new Card(Type.DIAMONDS, i));
			stack.add(new Card(Type.SPADES, i));
			stack.add(new Card(Type.CLUBS, i));
		}
		Collections.shuffle(stack);
	}
}
