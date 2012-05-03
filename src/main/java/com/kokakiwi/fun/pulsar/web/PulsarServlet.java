package com.kokakiwi.fun.pulsar.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class PulsarServlet extends HttpServlet
{
    private static final long serialVersionUID = 6305415134358691775L;
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        resp.getWriter().print("Hello from Java!\n");
    }
}
