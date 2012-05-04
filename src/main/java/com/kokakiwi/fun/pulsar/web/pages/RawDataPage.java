package com.kokakiwi.fun.pulsar.web.pages;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kokakiwi.fun.pulsar.db.PulsarEntry;
import com.kokakiwi.fun.pulsar.net.IDataListener;
import com.kokakiwi.fun.pulsar.web.IDynamicPage;
import com.kokakiwi.fun.pulsar.web.PulsarServlet;
import com.kokakiwi.fun.pulsar.web.utils.WebUtils;

public class RawDataPage implements IDynamicPage
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
        }
        
        String data = sb.toString().replaceAll("[^0-9A-F]+", "");
        byte[] array = hexStringToByteArray(data);
        InputStream dataStream = new ByteArrayInputStream(array);
        resp.setContentType("application/octet-stream");
        resp.setContentLength(array.length);
        WebUtils.send(dataStream, resp);
        
        return true;
    }
    
    public static byte[] hexStringToByteArray(String s)
    {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2)
        {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
                    .digit(s.charAt(i + 1), 16));
        }
        return data;
    }
    
}
