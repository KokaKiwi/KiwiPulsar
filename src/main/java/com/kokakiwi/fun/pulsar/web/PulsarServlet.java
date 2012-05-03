package com.kokakiwi.fun.pulsar.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class PulsarServlet extends HttpServlet
{
    private static final long serialVersionUID = 6305415134358691775L;
    
    private final PulsarWeb   main;
    
    public PulsarServlet(PulsarWeb main)
    {
        this.main = main;
    }
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        if (!StaticPages.handle(req, resp)
                && !DynamicPages.handle(this, req, resp))
        {
            resp.setStatus(404);
            StaticPages.show("special/notfound.html", resp);
        }
    }
    
    public PulsarWeb getMain()
    {
        return main;
    }
}
