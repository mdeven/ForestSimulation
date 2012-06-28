package forest;

import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;
import javax.swing.*;

/**
 * A simple demonstration application showing how to create a line chart using
 * data from an
 * {@link XYDataset}.
 *
 */
public class Graphs extends javax.swing.JFrame {

    static XYSeries SeriesPlantHeight = new XYSeries("Average Plant Height");
    static XYSeries SeriesTreePopulation = new XYSeries("Tree Population");
   // static XYSeries SeriesLeafArea = new XYSeries("20xAverage Leaf Area");
    static XYSeries SeriesGiraffePopulation = new XYSeries("Giraffe Population");
    static XYSeries SeriesGiraffeHeight = new XYSeries("Average Giraffe Height");
    static XYSeries SeriesGiraffeNeckLength = new XYSeries("Average Giraffe Necklength");
    static XYSeries SeriesLionPopulation=new XYSeries("Lion Population");
    static XYSeriesCollection dataset = new XYSeriesCollection();

    /**
     * Creates a new demo.
     *
     * @param title the frame title.
     */
    public Graphs(final String title) {

        super(title);

        final XYDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    /**
     * Creates a sample dataset.
     *
     * @return a sample dataset.
     */
    public XYDataset createDataset() {


        SeriesPlantHeight.add(0.0, 0.0);
        SeriesTreePopulation.add(0.0, 0.0);
      //  SeriesLeafArea.add(0.0, 0.0);
        SeriesGiraffePopulation.add(0.0, 0.0);
        SeriesGiraffeHeight.add(0.0, 0.0);
        SeriesGiraffeNeckLength.add(0.0, 0.0);
        SeriesLionPopulation.add(0.0,0.0);
        dataset.addSeries(SeriesPlantHeight);
        dataset.addSeries(SeriesTreePopulation);
        //dataset.addSeries(SeriesLeafArea);
        dataset.addSeries(SeriesGiraffePopulation);
        dataset.addSeries(SeriesGiraffeHeight);
        dataset.addSeries(SeriesGiraffeNeckLength);
        dataset.addSeries(SeriesLionPopulation);
        return dataset;

    }

    /**
     * Creates a chart.
     *
     * @param dataset the data for the chart.
     *
     * @return a chart.
     */
    public JFreeChart createChart(final XYDataset dataset) {

        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Graphs", // chart title
                "Years Passed", // x axis label
                "Y", // y axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                true, // include legend
                true, // tooltips
                false // urls
                );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);

        //final StandardLegend legend = (StandardLegend) chart.getLegend();
        //legend.setDisplaySeriesShapes(true);

        // get a reference to the plot for further customisation...
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.darkGray);

        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesShapesVisible(1, false);
        renderer.setSeriesShapesVisible(2, false);
        renderer.setSeriesShapesVisible(3, false);
        renderer.setSeriesShapesVisible(4, false);
        renderer.setSeriesShapesVisible(5, false);
        renderer.setSeriesShapesVisible(6,false);
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        //final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        // rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.

        return chart;

    }
}
