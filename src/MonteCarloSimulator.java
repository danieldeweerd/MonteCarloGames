import cardgamelib.DrinkingGame;
import games.LinearBus;

public class MonteCarloSimulator {

    private static final int NUMBER_OF_TEST_RUNS = 20000;           // Number of times to simulate the game

    /**
     * Simulate a drinking game a large amount of times to get
     * the average amount of taken sips
     *
     * @param drinkingGame
     * @return sips
     */

    public static int simulate(DrinkingGame drinkingGame) {
        int totalAmountOfSips = 0;
        for (int i = 0; i < NUMBER_OF_TEST_RUNS; i++) {
            int slokken = drinkingGame.run();
            totalAmountOfSips += slokken;
        }
        return totalAmountOfSips / NUMBER_OF_TEST_RUNS;
    }

    public static void main(String[] args) {
        int numberOfClosedCards = 3;
        int numberOfCardsOnTable = 8;
        DrinkingGame bus = new LinearBus(numberOfCardsOnTable, numberOfClosedCards);
        int slokkenGemiddeld = simulate(bus);
        System.out.println(numberOfCardsOnTable + " KAARTEN WAARVAN "
                + numberOfClosedCards + " DICHT: " + slokkenGemiddeld);

    }
}
