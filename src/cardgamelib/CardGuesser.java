package cardgamelib;

import java.util.Random;

/**
 * Utility class to make either reasonable or random guesses based
 * on cards
 */

public class CardGuesser {
    static Random RANDOM = new Random();

    /**
     * Input a card and guess whether another card is greater or smaller
     *
     * @param card to base prediction on
     * @return a reasonable guess whether another cardcard will be greater or smaller than the given card
     */
    public static Comparison predictOtherCard(Card card) {
        if (card.isClosed())                    // If the value of the card is invisible, return a random value
            return randomComp();
        if (card.getValue() > 8)
            return Comparison.LESSER;
        else
            return Comparison.GREATER;
    }

    /**
     * Get a random guess
     *
     * @return GREATER or LESSER comparison at random
     */
    public static Comparison randomComp() {
        int x = RANDOM.nextInt(2);
        if (x == 0)
            return Comparison.GREATER;
        else
            return Comparison.LESSER;
    }

    /**
     * Get a random color
     *
     * @return random color
     */
    public static Color randomColor() {
        int x = RANDOM.nextInt(2);
        if (x == 0)
            return Color.BLACK;
        else
            return Color.RED;
    }

    public static boolean random() {
        return RANDOM.nextBoolean();
    }
}
