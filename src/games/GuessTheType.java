package games;

import java.util.Random;

public class GuessTheType extends DrinkingGame {

    int numberOfSips = 0;
    int numberOfCards;

    public GuessTheType(int numberOfCards) {
        this.numberOfCards = numberOfCards;
    }

    @Override
    public void run() {
        numberOfSips = 0;
        Random r = new Random();
        int counter = 0;

        for(int i = 0; i < numberOfCards; i++) {
            int rand = r.nextInt(4);
            while (rand != 3) {
                rand = r.nextInt(4);
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
        return null;
    }

    @Override
    public int getNumberOfCards()
    {
        return 3;
    }
}
