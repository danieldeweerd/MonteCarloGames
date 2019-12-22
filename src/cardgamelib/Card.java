package cardgamelib;

/**
 * Class representing a single game card, with four possible colors (Diamonds, hearts, clubs or spades)
 * and thirteen possible values (2 to 10, where 11 to 14 represent jack, queen, king and ace)
 */

public class Card {

    private final Type type;
    private final int value;
    private boolean closed = false;                     //	Whether the card's type and value can be seen by the player

    public Card(Type type, int number) {
        if (number > 14) {
            throw new IllegalArgumentException();       // 	No cards allowed with a value bigger than 14
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

    /**
     * Check whether this card is in between two given other cards
     *
     * @param c1 first card
     * @param c2 second card
     * @return Inside or outside
     */
    public boolean isInside(Card c1, Card c2) {
        int value1 = c1.getValue();
        int value2 = c2.getValue();
        int max = Math.max(value1, value2);
        int min = Math.min(value1, value2);
        return (this.value > min && this.value < max);
    }

    public String toString() {
        return type + " " + value;
    }

    public int getValue() {
        return value;
    }

    /**
     * Indicate whether the card value and type are visible to the player
     *
     * @param closed - whether the card is visible or not
     */
    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public boolean isClosed() {
        return closed;
    }

    public Color getColor() {
        if (type == Type.CLUBS || type == Type.SPADES)
            return Color.BLACK;
        else
            return Color.RED;
    }
}
