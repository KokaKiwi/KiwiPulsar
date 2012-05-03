package com.kokakiwi.fun.pulsar.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.kokakiwi.fun.pulsar.net.IDataListener;

public class PulsarServlet extends HttpServlet implements IDataListener
{
    private static final long  serialVersionUID = 6305415134358691775L;
    
    private final PulsarWeb    main;
    
    private final List<String> log              = Lists.newLinkedList();
    
    public PulsarServlet(PulsarWeb main)
    {
        this.main = main;
        
        main.getReceiver().setListener(this);
    }
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        String request = req.getPathInfo().substring(1);
        if (!request.isEmpty())
        {
            String[] parts = request.split("/");
            
            if (parts[0].equals("log"))
            {
                for (String str : log)
                {
                    resp.getWriter().println(str);
                }
            }
            else
            {
                resp.setStatus(404);
                resp.getWriter().print("Not found!");
            }
        }
    }
    
    public void onInfos(String name, double value)
    {
        
    }
    
    public void onPower(double power)
    {
        
    }
    
    public void onData(String[] datas)
    {
        String data = Joiner.on(' ').join(datas);
        
        log.add(data);
    }
    
    public PulsarWeb getMain()
    {
        return main;
    }
    
    public List<String> getLog()
    {
        return log;
    }
}
