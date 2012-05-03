package com.kokakiwi.fun.pulsar.web.utils;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.kokakiwi.fun.pulsar.web.StaticPages;

public class WebUtils
{
    public static void send(String file, HttpServletResponse resp)
            throws IOException
    {
        InputStream in = StaticPages.class.getResourceAsStream("/" + file);
        send(in, resp);
    }
    
    public static void send(InputStream in, HttpServletResponse resp)
            throws IOException
    {
        IOUtils.copy(in, resp.getOutputStream());
    }
}
