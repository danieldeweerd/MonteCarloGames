package games;

import cardgamelib.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Subclass of DrinkingGame that simulates bussen
 */
public class LinearBus extends DrinkingGame {
    CardStack stack = new CardStack();
    List<Card> cardsOnTable = new ArrayList<Card>();    //	Represents cards on the table
    int numberOfCardsOnTable;
    int closedCards;
    int counter = 0;
    public long usedCards;

    public LinearBus(int numberOfCardsOnTable, int closedCards) {
        this.numberOfCardsOnTable = numberOfCardsOnTable;
        this.closedCards = closedCards;
        usedCards = numberOfCardsOnTable;
    }

    @Override
    public String getName() {
        return "Bussen";
    }

    public int run() {
        int swigs = 0;
        counter = 0;

        for (int i = 0; i < numberOfCardsOnTable; i++)                  // Lay down starting cards on table
            cardsOnTable.add(stack.draw());

        for (int i = 0; i < closedCards; i++)
            cardsOnTable.get(i).setClosed(true);

        while (counter != numberOfCardsOnTable) {                       // While the end of the bus hasn't been reached...
            Card c1 = cardsOnTable.get(counter);                        // Get the card that is on the table
            Card c2 = stack.draw();                                     // Draw a card
            usedCards++;

            Comparison pred = CardGuesser.predictOtherCard(c1);         // Predict whether c2 is greater or lesser than c2
            Comparison res = c2.compare(c1);                            // Check this
            cardsOnTable.set(counter, c2);                              // Put c2 on the table in the place of c1

            if (res == pred) {                                          // Advance one card if correct
                counter++;
            } else {
                counter = 0;                                            // Reset and take a swig if incorrect
                swigs++;
            }
        }
        return swigs;
    }
}
