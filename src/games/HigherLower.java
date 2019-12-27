package games;

import cardgamelib.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Game where the player has to guess whether a drawn card is higher or lower than a given carc
 * for n cards in a row
 */
public class HigherLower extends DrinkingGame {
    private int numberOfCardsOnTable;
    private int closedCards;
    private int counter = 0;
    private int sips = 0;
    private boolean intelligentGuessing;

    List<Card> cardsOnTable = new ArrayList<Card>();    //	Represents cards on the table
    CardStack stack = null;

    public HigherLower(int numberOfCardsOnTable, int closedCards, boolean intelligentGuessing) {
        this.numberOfCardsOnTable = numberOfCardsOnTable;
        this.closedCards = closedCards;
        this.intelligentGuessing = intelligentGuessing;
    }

    public void run() {
        stack = new CardStack();
        int sips = 0;
        counter = 0;

        for (int i = 0; i < numberOfCardsOnTable; i++)                  // Lay down starting cards on table
            cardsOnTable.add(stack.drawOpen());

        for (int i = 0; i < closedCards; i++)
            cardsOnTable.get(i).setClosed(true);

        while (counter != numberOfCardsOnTable) {                       // While the end of the bus hasn't been reached...
            Card c1 = cardsOnTable.get(counter);                        // Get the card that is on the table
            Card c2 = stack.drawOpen();                                 // Draw a card

            Comparison pred = CardGuesser.predictOtherCard(c1);         // Predict whether c2 is greater or lesser than c2
            Comparison res = c2.compare(c1);                            // Check this
            cardsOnTable.set(counter, c2);                              // Put c2 on the table in the place of c1
            if (! intelligentGuessing)
                c2.setClosed(true);

            if (res == pred) {                                          // Advance one card if correct
                counter++;
            } else {
                counter = 0;                                            // Reset and take a swig if incorrect
                sips++;
            }
        }
        this.sips = sips;
    }

    @Override
    public String getName() {
        return "Rechte bus met " + numberOfCardsOnTable + " kaarten, waarvan " + closedCards + " aan het begin gesloten";
    }

    @Override
    public int getSips() {
        return sips;
    }

    @Override
    public int getNumberOfCards() {
        return stack.getNumberOfCardsDrawn();
    }
}
