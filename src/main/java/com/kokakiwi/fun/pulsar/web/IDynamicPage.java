package com.kokakiwi.fun.pulsar.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IDynamicPage
{
    public boolean handle(PulsarServlet servlet, HttpServletRequest req,
            HttpServletResponse resp, List<String> params) throws IOException;
}
