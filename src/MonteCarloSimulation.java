import cardgamelib.DrinkingGame;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class that 'plays' a game a large number of times in order to collect statistics
 * about how often you have to drink during the game
 */

public class MonteCarloSimulation {

    private final int numberOfSamples = 20000;                            // Number of times to simulate the game

    private final DrinkingGame game;
    private java.util.List<Integer> sipsData = new ArrayList<Integer>();        // Store the amount of times the player has to drink, for every game
    double[] absoluteFrequencies = null;
    double[] relativeFrequencies = null;
    double[] cumulativeDistribution = null;
    double[] trimmedRelativeFrequencies = null;

    private int mean = 0;
    private int median = 0;
    private int mode = 0;

    public MonteCarloSimulation(DrinkingGame game) {
        this.game = game;
    }

    public int getMean() {
        return mean;
    }

    public int getMedian() {
        return median;
    }

    public int getMode() {
        return mode;
    }

    /**
     * Simulate a drinking game a large amount of times
     */

    public void run() {
        int totalAmountOfSips = 0;

        /*
         * Draw reference progress bar
         */

        for (int i = 0; i < 100; i++)
            System.out.print("|");
        System.out.println();

        int progressBarCounter = 0;
        for (int i = 0; i < numberOfSamples; i++) {
            game.run();                                                                 // For every simulation, take the average
            int sips = game.getSips();
            sipsData.add(sips);
            totalAmountOfSips += sips;

            progressBarCounter++;                                                       // Draw progress bar
            if (progressBarCounter == numberOfSamples / 100) {
                System.out.print("|");
                progressBarCounter = 0;
            }
        }

        System.out.println();                                                           // Finish progress bar


        this.mean = totalAmountOfSips / numberOfSamples;

        absoluteFrequencies = new double[mean * 20];                                    // Calculate frequency of each result
        for (int i = 0; i < absoluteFrequencies.length; i++) {
            absoluteFrequencies[i] = Collections.frequency(sipsData, i);
        }

        relativeFrequencies = new double[mean * 20];                                    // Calculate relative frequency of each result
        for (int i = 0; i < relativeFrequencies.length; i++) {
            relativeFrequencies[i] = absoluteFrequencies[i] / numberOfSamples;
        }

        trimmedRelativeFrequencies = new double[mean * 5];                              // Make array with frequencies of a smaller
        for (int i = 0; i < trimmedRelativeFrequencies.length; i++) {                   // range of values to use for the graph
            trimmedRelativeFrequencies[i] = relativeFrequencies[i];
        }

        cumulativeDistribution = new double[mean * 5];                                  // Calculate cumulative distribution graph
        for (int i = 0; i < trimmedRelativeFrequencies.length; i++) {
            double cumulative = 0.0;
            for (int j = 0; j < i; j++) {
                cumulative += trimmedRelativeFrequencies[j];
            }
            cumulativeDistribution[i] = cumulative;
        }

        calculateMedian();
        calculateMode();
    }

    /**
     * Calculate and store the middle value
     */
    private void calculateMedian() {
        int totalOccurrences = 0;
        for (int i = 0; i < absoluteFrequencies.length; i++) {
            totalOccurrences += absoluteFrequencies[i];
            if (totalOccurrences >= (numberOfSamples / 2)) {
                median = i;
                return;
            }
        }
    }

    /**
     * Calculate and store the most common value
     */

    private void calculateMode() {
        double max = 0;
        int maxi = 0;
        for (int i = 0; i < relativeFrequencies.length; i++) {
            if (relativeFrequencies[i] > max) {
                max = relativeFrequencies[i];
                maxi = i;
            }
        }
        mode = maxi;
    }
}
