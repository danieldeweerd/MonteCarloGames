import cardgamelib.DrinkingGame;

import games.LinearBus;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;


import java.util.*;

public class MonteCarloSimulator {

    private final int NUMBER_OF_SAMPLES = 1000000;           // Number of times to simulate the game
    java.util.List<Integer> data = new ArrayList<Integer>();

    double[] absoluteFrequencyArray = null;
    double[] relativeFrequencyArray = null;

    /**
     * Simulate a drinking game a large amount of times to get
     * the average amount of taken sips
     *
     * @param drinkingGame A game to simulate
     * @return sips
     */

    public int simulate(DrinkingGame drinkingGame, boolean drawPlot) {
        long time = System.currentTimeMillis();

        int totalAmountOfSips = 0;
        for (int i = 0; i < 100; i++)
            System.out.print("|");
        System.out.println();
        int j = 0;
        for (int i = 0; i < NUMBER_OF_SAMPLES; i++) {
            int sips = drinkingGame.run();
            data.add(sips);
            totalAmountOfSips += sips;

            j++;
            if (j == NUMBER_OF_SAMPLES / 100) {
                System.out.print("|");
                j = 0;
            }
        }
        LinearBus b = (LinearBus) drinkingGame;
        System.out.println();

        int meanSips = totalAmountOfSips / NUMBER_OF_SAMPLES;

        absoluteFrequencyArray = new double[meanSips * 20];
        for (int i = 0; i < absoluteFrequencyArray.length; i++) {
            absoluteFrequencyArray[i] = Collections.frequency(data, i);
        }

        relativeFrequencyArray = new double[meanSips * 20];
        for (int i = 0; i < relativeFrequencyArray.length; i++) {
            relativeFrequencyArray[i] = absoluteFrequencyArray[i] / NUMBER_OF_SAMPLES;
        }

        double[] drawingArray = new double[meanSips * 5];
        for (int i = 0; i < drawingArray.length; i++) {
            drawingArray[i] = relativeFrequencyArray[i];
        }

        long elapsed = System.currentTimeMillis() - time;
        System.out.println("Elapsed: " + elapsed + "ms");
        System.out.println("Average cards used: " + b.usedCards / NUMBER_OF_SAMPLES);

        if (drawPlot)
            drawScatterPlot(drawingArray);

        return meanSips;
    }


    public static void main(String[] args) {
        new MonteCarloSimulator();
    }

    public MonteCarloSimulator() {
        //avgAsFunctionOfCards(8);
        probabilityDistribution(8, 8);
        //closedCardsRelation(8);
    }

    public void probabilityDistribution(int numberOfCards, int closedCards)
    {
        LinearBus game = new LinearBus(numberOfCards, closedCards);
        int mean = simulate(game, true);

        System.out.println("MEAN: " + mean);
        System.out.println("MEDIAN: " + findMedian());
        System.out.println("MODE: " + findMode());
    }

    public void avgAsFunctionOfCards(int max)
    {
       double[] values = new double[max];
       for (int i = 0; i < values.length; i++)
           values[i] = i + 1;

        double[] results = new double[values.length];
        for (int i = 0; i < values.length; i++)
        {
            results[i] = simulate(new LinearBus((int) values[i], 0), false);
        }

        for (int i = 0; i < values.length; i++)
        {
            System.out.println( (int) values[i] + " " + (int) results[i]);
        }
        XYChart chart = QuickChart.getChart("Verband tussen aantal kaarten en aantal slokken", "X", "Y", "y(x)", values, results);
        new SwingWrapper(chart).displayChart();
    }

    public void closedCardsRelation(int max)
    {
        double[] values = new double[max];
        for (int i = 0; i < values.length; i++)
            values[i] = i + 1;

        double[] results = new double[values.length];
        for (int i = 0; i < values.length; i++)
        {
            results[i] = simulate(new LinearBus(8,i), false);
        }

        XYChart chart = QuickChart.getChart("Verband tussen aantal gesloten kaarten en aantal slokken", "X", "Y", "y(x)", values, results);
        new SwingWrapper(chart).displayChart();
    }

    public void drawScatterPlot(double[] table) {
        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).build();

        // Customize Chart
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        chart.getStyler().setChartTitleVisible(false);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSW);
        chart.getStyler().setMarkerSize(16);

        chart.addSeries("Frequency", table);
        chart.setXAxisTitle("Aantal genomen slokken per spel");
        chart.setYAxisTitle("Frequentie");

        new SwingWrapper(chart).displayChart();
    }

    public int findMedian() {
        int totalOccurences = 0;
        for (int i = 0; i < absoluteFrequencyArray.length; i++) {
            totalOccurences += absoluteFrequencyArray[i];
            if (totalOccurences >= (NUMBER_OF_SAMPLES / 2)) {
                return i;
            }
        }
        return 0;
    }

    public int findMode() {
        double max = 0;
        int maxi = 0;
        for (int i = 0; i < relativeFrequencyArray.length; i++) {
            if (relativeFrequencyArray[i] > max) {
                max = relativeFrequencyArray[i];
                maxi = i;
            }
        }
        return maxi;
    }
}
