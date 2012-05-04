package com.kokakiwi.fun.pulsar.data.utils;

import java.util.List;

import com.kokakiwi.fun.pulsar.db.Database;
import com.kokakiwi.fun.pulsar.db.PulsarEntry;
import com.kokakiwi.fun.pulsar.net.IDataListener;

public class BinaryData
{
    public static byte[] getData(Database db)
    {
        StringBuilder sb = new StringBuilder();
        List<PulsarEntry> entries = db.getServer().find(PulsarEntry.class)
                .where().eq("type", IDataListener.Type.DATA).findList();
        for (PulsarEntry entry : entries)
        {
            sb.append(entry.getLine());
        }
        
        String data = sb.toString().replaceAll("[^0-9A-F]+", "");
        byte[] array = hexStringToByteArray(data);
        
        return array;
    }
    
    public static byte[] getData(String line)
    {
        String data = line.replaceAll("[^0-9A-F]+", "");
        byte[] array = hexStringToByteArray(data);
        
        return array;
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
