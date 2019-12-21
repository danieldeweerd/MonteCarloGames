import cardgamelib.DrinkingGame;

import games.LinearBus;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;


import java.util.*;

public class MonteCarloSimulator {

    private static final int NUMBER_OF_TEST_RUNS = 1000000;           // Number of times to simulate the game
    static java.util.List<Integer> data = new ArrayList<Integer>();
    static double[] frequencyTable = new double[100];
    static double[] possibleValues = new double[100];

    /**
     * Simulate a drinking game a large amount of times to get
     * the average amount of taken sips
     *
     * @param drinkingGame A game to simulate
     * @return sips
     */

    public static int simulate(DrinkingGame drinkingGame) {
        int totalAmountOfSips = 0;
        long btime = System.currentTimeMillis();
        for (int i = 0; i < NUMBER_OF_TEST_RUNS; i++) {
            int slokken = drinkingGame.run();
            //System.out.println(slokken);
            data.add(slokken);
            totalAmountOfSips += slokken;
        }
        long elapsed = System.currentTimeMillis() - btime;
        System.out.println("Elapsed: " + elapsed);
        System.out.println(Collections.frequency(data, 0));

        for (int i = 0; i < frequencyTable.length; i++) {
            frequencyTable[i] = Collections.frequency(data, i);
        }

        return totalAmountOfSips / NUMBER_OF_TEST_RUNS;
    }


    public static void main(String[] args) {
        int numberOfCardsOnTable = 8;
        DrinkingGame bus = new LinearBus(numberOfCardsOnTable);
        int slokkenGemiddeld = simulate(bus);
        System.out.println("AVERAGE: " + slokkenGemiddeld);
        System.out.println("0 FREQ: " + Collections.frequency(data, 0));
        System.out.println("10 FREQ: " + Collections.frequency(data, 10));

        scatterPlot();

    }

    public static void scatterPlot()
    {
        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).build();

        // Customize Chart
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        chart.getStyler().setChartTitleVisible(false);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSW);
        chart.getStyler().setMarkerSize(16);


        chart.addSeries("Gaussian Blob", frequencyTable);

        new SwingWrapper(chart).displayChart();

    }
}
