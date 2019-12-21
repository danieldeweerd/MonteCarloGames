package games;

import cardgamelib.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Subclass of DrinkingGame that simulates bussen
 */
public class LinearBus extends DrinkingGame {
    CardStack stack  = new CardStack();
    List<Card> cardsOnTable = new ArrayList<Card>();    //	Represents cards on the table
    int numberOfCardsOnTable;
    int counter = 0;

    public LinearBus(int numberOfCardsOnTable) {
        this.numberOfCardsOnTable = numberOfCardsOnTable;

        for (int i = 0; i < numberOfCardsOnTable; i++)
            cardsOnTable.add(stack.draw());                        // Lay down initial set of cards
    }

    @Override
    public String getName() {
        return "Bussen";
    }

    public int run() {
        stack = new CardStack();
        int slokken = 0;
        counter = 0;
        while (counter != numberOfCardsOnTable) {                       // While the end of the bus hasn't been reached...
            Card c1 = cardsOnTable.get(counter);                        // Get the c
            Card c2 = stack.draw();


            Comparison pred = CardGuesser.predictOtherCard(c1);
            Comparison res = c2.compare(c1);

//            System.out.println("--------------");
//            System.out.println("Card on table " + c1);
//            System.out.println("Prediction: " + pred);
//            System.out.println("Drawn card: " + c2.toString());
            boolean js = pred == res;
//            System.out.println("Result: " + js);

            if (res == pred) {
                counter++;
//                System.out.println("Verder");
            } else {
                counter = 0;
//                System.out.println("SLOK!");
                slokken++;
            }

        }
        return slokken;
    }


}
