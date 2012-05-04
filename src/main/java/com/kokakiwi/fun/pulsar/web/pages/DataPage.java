package com.kokakiwi.fun.pulsar.web.pages;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kokakiwi.fun.pulsar.db.PulsarEntry;
import com.kokakiwi.fun.pulsar.net.IDataListener;
import com.kokakiwi.fun.pulsar.web.IDynamicPage;
import com.kokakiwi.fun.pulsar.web.PulsarServlet;

public class DataPage implements IDynamicPage
{
    
    public boolean handle(PulsarServlet servlet, HttpServletRequest req,
            HttpServletResponse resp, List<String> params) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        List<PulsarEntry> entries = servlet.getMain().getDatabase().getServer()
                .find(PulsarEntry.class).where()
                .eq("type", IDataListener.Type.DATA).findList();
        for (PulsarEntry entry : entries)
        {
            sb.append(entry.getLine());
            sb.append('\n');
        }
        
        resp.getWriter().print(sb);
        
        return true;
    }
    
}
