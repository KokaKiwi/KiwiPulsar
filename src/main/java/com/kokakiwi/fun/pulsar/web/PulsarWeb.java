package com.kokakiwi.fun.pulsar.web;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.kokakiwi.fun.pulsar.db.Database;
import com.kokakiwi.fun.pulsar.net.Receiver;

public class PulsarWeb
{
    private final Server   server;
    private final Receiver receiver;
    private final Database database;
    
    public PulsarWeb() throws Exception
    {
        receiver = new Receiver();
        database = new Database();
        
        server = new Server(Integer.valueOf(System.getenv("PORT")));
        ServletContextHandler context = new ServletContextHandler(
                ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new PulsarServlet(this)), "/*");
    }
    
    public void start() throws Exception
    {
        receiver.start();
        
        server.start();
        server.join();
    }
    
    public void stop()
    {
        
    }
    
    public Server getServer()
    {
        return server;
    }
    
    public Receiver getReceiver()
    {
        return receiver;
    }
    
    public static void main(String[] args)
    {
        try
        {
            new PulsarWeb().start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
