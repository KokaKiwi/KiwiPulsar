package com.kokakiwi.fun.pulsar.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kokakiwi.fun.pulsar.web.pages.LogPage;

public class DynamicPages
{
    private final static Map<String, DynamicPage> pages = Maps.newLinkedHashMap();
    
    static
    {
        register("log", new LogPage());
    }
    
    public static void register(String regex, DynamicPage page)
    {
        pages.put(regex, page);
    }
    
    public static boolean handle(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException
    {
        boolean handled = false;
        String request = req.getPathInfo().substring(1);
        
        for (String regex : pages.keySet())
        {
            Matcher matcher = Pattern.compile(regex).matcher(request);
            if (matcher.find())
            {
                DynamicPage page = pages.get(regex);
                List<String> params = Lists.newLinkedList();
                for (int i = 0; i < matcher.groupCount(); i++)
                {
                    params.add(matcher.group(i + 1));
                }
                if (page.handle(req, resp, params))
                {
                    handled = true;
                    break;
                }
            }
        }
        
        return handled;
    }
}
