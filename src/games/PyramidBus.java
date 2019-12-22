package games;

import cardgamelib.*;

import java.util.ArrayList;
import java.util.List;

/**
 * INCOMPLETE
 */
public class PyramidBus extends DrinkingGame {

    List<Card> eersteTrap = new ArrayList<Card>();
    List<Card> tweedeTrap = new ArrayList<Card>();
    List<Card> derdeTrap = new ArrayList<Card>();
    List<Card> vierdeTrap = new ArrayList<Card>();

    CardStack stack = new CardStack();

    public int run() {
        for (int i = 0; i < 4; i++)
            vierdeTrap.add(stack.draw());
        for (int i = 0; i < 3; i++)
            derdeTrap.add(stack.draw());
        for (int i = 0; i < 2; i++)
            tweedeTrap.add(stack.draw());
        eersteTrap.add(stack.draw());

        int counter = 0;
        while (counter != vierdeTrap.size())
        {
            CardGuesser.randomColor();
        }
        return 0;
    }

    public String getName() {
        return "Pyramidebus";
    }
}
