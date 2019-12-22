import cardgamelib.DrinkingGame;
import games.HigherLower;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

public class Main {

    public Main() {
        showProbabilityDistribution(new HigherLower(8, 8));
    }

    public static void main(String[] args) {
        new Main();
    }

    private void showProbabilityDistribution(DrinkingGame game) {
        MonteCarloSimulation sim = new MonteCarloSimulation(game);
        sim.run();

        System.out.println("MEAN: " + sim.getMean());
        System.out.println("MEDIAN: " + sim.getMedian());
        System.out.println("MODE: " + sim.getMode());

        drawScatterPlot(sim.trimmedRelativeFrequencies, "Aantal slokken per spel - " + game.getName(), "Frequentie", "trimmedRelative", false);
        drawScatterPlot(sim.cumulativeDistribution, "Maximaal aantal slokken per spel - " + game.getName(), "Kans", "cumulative", false);

    }

    private void drawRelationBetweenCardsAndSips(int max, boolean save) {
        double[] values = new double[max];
        for (int i = 0; i < values.length; i++)
            values[i] = i + 1;

        double[] results = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            HigherLower bus = new HigherLower((int) values[i], 0);
            MonteCarloSimulation sim = new MonteCarloSimulation(bus);
            sim.run();
            results[i] = sim.getMean();
        }

        XYChart chart = QuickChart.getChart("Verband tussen aantal kaarten in de bus en aantal slokken", "Aantal kaarten op tafel", "Aantal slokken", "y(x)", values, results);
        new SwingWrapper(chart).displayChart();

        if (!save)
            return;

        try {
            BitmapEncoder.saveBitmapWithDPI(chart, "Kaarten-slokken", BitmapEncoder.BitmapFormat.PNG, 300);
        } catch (Exception ignore) {
        }

    }

    private void drawScatterPlot(double[] data, String xTitle, String yTitle, String fileName, boolean save) {
        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).build();

        // Customize Chart
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
