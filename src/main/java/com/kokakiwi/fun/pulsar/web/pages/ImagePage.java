package com.kokakiwi.fun.pulsar.web.pages;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kokakiwi.fun.pulsar.data.GraphMaker;
import com.kokakiwi.fun.pulsar.data.ImageMaker;
import com.kokakiwi.fun.pulsar.net.IDataListener;
import com.kokakiwi.fun.pulsar.web.IDynamicPage;
import com.kokakiwi.fun.pulsar.web.PulsarServlet;

public class ImagePage implements IDynamicPage
{
    
    public boolean handle(PulsarServlet servlet, HttpServletRequest req,
            HttpServletResponse resp, List<String> params) throws IOException
    {
        BufferedImage image = null;
        String type = req.getParameter("type");
        
        if (type != null)
        {
            if (type.equals("chart-data"))
            {
                image = GraphMaker.createGraph(servlet.getMain().getDatabase(),
                        IDataListener.Type.DATA);
            }
            else if (type.equals("chart-power"))
            {
                image = GraphMaker.createGraph(servlet.getMain().getDatabase(),
                        IDataListener.Type.DATA);
            }
            else
            {
                image = ImageMaker.create(servlet.getMain().getDatabase());
            }
        }
        else
        {
            image = ImageMaker.create(servlet.getMain().getDatabase());
        }
        
        if (image != null)
        {
            resp.setContentType("image/png");
            ImageIO.write(image, "png", resp.getOutputStream());
        }
        
        return true;
    }
    
}
