package com.kokakiwi.fun.pulsar.web.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kokakiwi.fun.pulsar.web.IDynamicPage;
import com.kokakiwi.fun.pulsar.web.PulsarServlet;
import com.kokakiwi.fun.pulsar.web.utils.WebUtils;

public class DataPage implements IDynamicPage
{
    
    public boolean handle(PulsarServlet servlet, HttpServletRequest req,
            HttpServletResponse resp, List<String> params) throws IOException
    {
        File file = new File("data.log");
        InputStream in = new FileInputStream(file);
        WebUtils.send(in, resp);
        
        return true;
    }
    
}
