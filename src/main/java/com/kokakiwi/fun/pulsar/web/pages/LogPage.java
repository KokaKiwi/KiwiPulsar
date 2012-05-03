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

public class LogPage implements IDynamicPage
{
    public boolean handle(PulsarServlet servlet, HttpServletRequest req,
            HttpServletResponse resp, List<String> params) throws IOException
    {
        boolean handled = true;
        
        File file = new File("session.log");
        InputStream in = new FileInputStream(file);
        WebUtils.send(in, resp);
        
        return handled;
    }
}
