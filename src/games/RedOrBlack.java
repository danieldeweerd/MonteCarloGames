package games;

import cardgamelib.CardGuesser;
import cardgamelib.CardStack;
import cardgamelib.Color;

/**
 * Game where the player has to guess whether the next drawn card will be red or black
 * for n cards in a row
 */

public class RedOrBlack extends DrinkingGame {

    private int numberOfCards;
    private int numberOfSips = 0;
    CardStack stack = new CardStack();

    public RedOrBlack(int numberOfCards) {
        this.numberOfCards = numberOfCards;
    }

    @Override
    public void run() {
        int counter = 0;
        numberOfSips = 0;
        while (counter < numberOfCards) {
            Color prediction = CardGuesser.randomColor();
            Color result = stack.drawOpen().getColor();
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
        return numberOfSips;
    }

    @Override
    public String getName() {
        return "Rood of zwart: " + numberOfCards + " kaarten";
    }
}
