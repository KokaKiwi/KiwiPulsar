package com.kokakiwi.fun.pulsar.data;

import java.awt.image.BufferedImage;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.kokakiwi.fun.pulsar.net.IDataListener;

import com.kokakiwi.fun.pulsar.data.utils.BinaryData;
import com.kokakiwi.fun.pulsar.db.Database;
import com.kokakiwi.fun.pulsar.db.PulsarEntry;

public class GraphMaker
{
    public final static int SIZE_MAX = 1024 * 4;
    
    public static BufferedImage createGraph(Database db, IDataListener.Type type)
    {
        BufferedImage image = null;
        int start = 0;
        List<PulsarEntry> entries = db.getServer().find(PulsarEntry.class)
                .findList();
        int size = entries.size();
        
        if (entries.size() > SIZE_MAX)
        {
            start = entries.size() - SIZE_MAX;
            size = SIZE_MAX;
        }
        
        XYSeriesCollection dataset = new XYSeriesCollection();
        int t = start;
        
        {
            XYSeries series = new XYSeries("Values");
            
            {
                for (int i = start; i < entries.size(); i++)
                {
                    PulsarEntry entry = entries.get(i);
                    if (entry.getType() == type)
                    {
                        if (type == IDataListener.Type.DATA)
                        {
                            byte[] array = BinaryData.getData(entry.getLine());
                            for (int x = 0; x < array.length; x++)
                            {
                                byte b = array[x];
                                series.add(t, b);
                                t++;
                            }
                        }
                    }
                    else
                    {
                        series.add(t, 0);
                        t++;
                    }
                }
            }
            
            dataset.addSeries(series);
        }
        
        JFreeChart chart = ChartFactory.createXYLineChart("Chart", "Time",
                "Values", dataset, PlotOrientation.VERTICAL, true, true, true);
        image = chart.createBufferedImage(size, 250);
        
        return image;
    }
}
