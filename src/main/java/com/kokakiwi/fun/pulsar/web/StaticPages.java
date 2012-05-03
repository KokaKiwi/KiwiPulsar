package com.kokakiwi.fun.pulsar.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kokakiwi.fun.pulsar.web.utils.WebUtils;

public class StaticPages
{
    public static boolean handle(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException
    {
        boolean handled = false;
        
        String request = req.getPathInfo().substring(1);
        if (request.isEmpty())
        {
            show("special/home.html", resp);
            handled = true;
        }
        else
        {
            if (request.endsWith(".html") && test("static/" + request))
            {
                show("static/" + request, resp);
                handled = true;
            }
            else
            {
                if (test("static/" + request + ".html"))
                {
                    show("static/" + request + ".html", resp);
                    handled = true;
                }
                else if (test("static/" + request))
                {
                    WebUtils.send("static/" + request, resp);
                    handled = true;
                }
            }
        }
        
        return handled;
    }
    
    public static void show(String page, HttpServletResponse resp)
            throws IOException
    {
        WebUtils.send("special/header.html", resp);
        WebUtils.send(page, resp);
        WebUtils.send("special/footer.html", resp);
    }
    
    public static boolean test(String file)
    {
        return StaticPages.class.getResource("/" + file) != null;
    }
}
