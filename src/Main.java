import games.DrinkingGame;
import games.HigherLower;
import games.CombinedBus;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

public class Main {

    public Main() {
        showProbabilityDistribution(new HigherLower(8, 8, true));
        showProbabilityDistribution(new CombinedBus(8, 6, 4, 2));
        drawRelationBetweenCardsAndSipsInHigherLower(12, false);
    }

    public static void main(String[] args) {
        new Main();
    }

    /**
     * Display a histrogram and a cumulative freqeuency graph for the amount of sips to be taken
     * while playing the given game
     * @param game
     */

    private void showProbabilityDistribution(DrinkingGame game) {
        MonteCarloSimulation sim = new MonteCarloSimulation(game, 1000000);
        sim.run();

        System.out.println("MEAN: " + sim.getMean());
        System.out.println("MEDIAN: " + sim.getMedian());
        System.out.println("MODE: " + sim.getMode());

        drawHistogram(sim.trimmedRelativeFrequencies, "Aantal slokken per spel - " + game.getName(), "Frequentie", "trimmedRelative", false);
        drawHistogram(sim.cumulativeDistribution, "Maximaal aantal slokken per spel - " + game.getName(), "Kans", "cumulative", false);

    }

    /**
     * Display a line graph showing the relation between the number of cards in a bus
     * and the average number of sips that the player has to take
     * @param max The maximum number of cards to calculate a result for
     * @param save Whether to save the graph
     */
    private void drawRelationBetweenCardsAndSipsInHigherLower(int max, boolean save) {
        /*
        * Initialize array containing values 1, 2, ..., max
         */
        double[] values = new double[max];
        for (int i = 0; i < values.length; i++)
            values[i] = i + 1;

        /*
        * Calculate the average number of sips to be taken for
        * every value up to the given max value
         */
        double[] results = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            int cards = (int) values[i];
            HigherLower bus = new HigherLower(cards, cards, false);
            MonteCarloSimulation sim = new MonteCarloSimulation(bus, 10000);
            sim.run();
            results[i] = sim.getMean();
        }

        /*
        * Draw graph
         */

        XYChart chart = QuickChart.getChart("Verband tussen aantal kaarten in de bus en aantal slokken", "Aantal kaarten op tafel", "Aantal slokken", "y(x)", values, results);
        new SwingWrapper(chart).displayChart();
        System.out.println();

        if (!save)
            return;

        /*
        * Save graph to disk if save == true
         */

        try {
            BitmapEncoder.saveBitmapWithDPI(chart, "Kaarten-slokken", BitmapEncoder.BitmapFormat.PNG, 300);
        } catch (Exception ignore) {
        }

    }

    /**
     * Draw a histogram from an array using XChart
     * @param data The data to use for the histogram
     * @param xTitle Label for the X axis
     * @param yTitle Label for the Y axis
     * @param fileName  Filename to save the graph
     * @param save Whether to save the graph to disk
     */
    private void drawHistogram(double[] data, String xTitle, String yTitle, String fileName, boolean save) {

        XYChart chart = new XYChartBuilder().width(800).height(600).build();

        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        chart.getStyler().setChartTitleVisible(false);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSW);
        chart.getStyler().setMarkerSize(8);

        chart.addSeries("Frequency", data);
        chart.setXAxisTitle(xTitle);
        chart.setYAxisTitle(yTitle);

        new SwingWrapper(chart).displayChart();

        if (!save)
            return;
        try {
            BitmapEncoder.saveBitmapWithDPI(chart, fileName, BitmapEncoder.BitmapFormat.PNG, 300);
        } catch (Exception ignored) {
        }
    }


}
