package games;

import cardgamelib.Card;
import cardgamelib.CardGuesser;
import cardgamelib.CardStack;

/**
 * Game where the player has to guess whether a card will be inside or outside two
 * given cards for n cards in a row
 * Work in progress
 */

public class InsideOutside extends DrinkingGame {

    int numberOfCards;
    int numberOfSips = 0;

    CardStack stack = new CardStack();

    public InsideOutside(int numberOfCards) {
        this.numberOfCards = numberOfCards;
    }

    @Override
    public void run() {
        int counter = 0;
        while (counter < 2) {
            Card c1 = stack.drawOpen();
            Card c2 = stack.drawOpen();
            Card c3 = stack.drawClosed();

            boolean prediction = CardGuesser.random();
            boolean result = c3.isInside(c1, c2);

            if (prediction == result) {
                counter++;
            } else {
                counter = 0;
                numberOfSips++;
            }

        }
    }

    @Override
    public int getSips() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getNumberOfCards() {
        return stack.getNumberOfCardsDrawn();
    }
}
