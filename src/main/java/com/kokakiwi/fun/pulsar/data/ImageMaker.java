package com.kokakiwi.fun.pulsar.data;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import com.kokakiwi.fun.pulsar.data.utils.BinaryData;
import com.kokakiwi.fun.pulsar.db.Database;
import com.kokakiwi.fun.pulsar.db.PulsarEntry;
import com.kokakiwi.fun.pulsar.net.IDataListener;

public class ImageMaker
{
    public final static int CHUNKS_PER_WIDTH = 1;
    public final static int WIDTH            = CHUNKS_PER_WIDTH * 16;
    
    public static BufferedImage create(Database db)
    {
        BufferedImage image = null;
        
        List<PulsarEntry> entries = db.getServer().find(PulsarEntry.class)
                .findList();
        
        int height = entries.size() / CHUNKS_PER_WIDTH + 1;
        
        image = new BufferedImage(WIDTH, height, BufferedImage.TYPE_INT_RGB);
        image.getGraphics().setColor(Color.black);
        image.getGraphics().drawRect(0, 0, image.getWidth(), image.getHeight());
        
        int x = 0;
        int y = 0;
        for (int i = 0; i < entries.size(); i++)
        {
            PulsarEntry entry = entries.get(i);
            drawLine(image, x, y, entry);
            
            x += 16;
            if (x >= WIDTH)
            {
                x = 0;
                y++;
            }
        }
        
        return image;
    }
    
    public static void drawLine(BufferedImage image, int x, int y,
            PulsarEntry entry)
    {
        if (entry.getType() == IDataListener.Type.DATA)
        {
            byte[] array = BinaryData.getData(entry.getLine());
            drawLine(image, x, y, array);
        }
    }
    
    public static void drawLine(BufferedImage image, int x, int y, byte[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            byte rgb = array[i];
            
            Color c = new Color(rgb);
            pixel(image, x + i, y, c);
        }
    }
    
    public static void pixel(BufferedImage image, int x, int y, Color c)
    {
        Graphics g = image.getGraphics();
        
        g.setColor(c);
        g.drawRect(x, y, 1, 1);
    }
}
