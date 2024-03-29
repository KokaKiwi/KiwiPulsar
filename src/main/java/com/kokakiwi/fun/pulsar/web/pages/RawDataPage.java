package com.kokakiwi.fun.pulsar.web.pages;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kokakiwi.fun.pulsar.data.utils.BinaryData;
import com.kokakiwi.fun.pulsar.web.IDynamicPage;
import com.kokakiwi.fun.pulsar.web.PulsarServlet;
import com.kokakiwi.fun.pulsar.web.utils.WebUtils;

public class RawDataPage implements IDynamicPage
{
    
    public boolean handle(PulsarServlet servlet, HttpServletRequest req,
            HttpServletResponse resp, List<String> params) throws IOException
    {
        byte[] array = BinaryData.getData(servlet.getMain().getDatabase());
        InputStream dataStream = new ByteArrayInputStream(array);
        resp.setContentType("application/octet-stream");
        resp.setContentLength(array.length);
        WebUtils.send(dataStream, resp);
        
        return true;
    }
    
}
