package com.kokakiwi.fun.pulsar.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kokakiwi.fun.pulsar.logger.PulsarLogger;

public class Receiver implements Runnable
{
    public final static String  URL                   = "http://psrx0392-15.0x10c.com/";
    public final static Pattern SCANNER_INFOS_PATTERN = Pattern
                                                              .compile("SCANNING\\s+(.+)\\s+AT\\s+(\\d.\\d+)");
    public final static Pattern POWER_PATTERN         = Pattern
                                                              .compile("POWER\\s(\\d+.\\d+)");
    public final static Pattern DATA_PATTERN          = Pattern
                                                              .compile("([A-F0-9]{4}\\s[A-F0-9]{4}\\s[A-F0-9]{4}\\s[A-F0-9]{4}\\s[A-F0-9]{4}\\s[A-F0-9]{4}\\s[A-F0-9]{4}\\s[A-F0-9]{4}\\s[A-F0-9]{4}\\s[A-F0-9]{4}\\s[A-F0-9]{4}\\s[A-F0-9]{4}\\s[A-F0-9]{4}\\s[A-F0-9]{4}\\s[A-F0-9]{4}\\s[A-F0-9]{4})");
    
    private boolean             listening             = false;
    private URLConnection       connection;
    private InputStream         in;
    
    private IDataListener       listener              = new DummyDataListener();
    
    public Receiver()
    {
        try
        {
            URL url = new URL(URL);
            connection = url.openConnection();
            
            in = connection.getInputStream();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void run()
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        listening = true;
        
        while (listening)
        {
            try
            {
                String line = reader.readLine();
                if (line != null)
                {
                    boolean data = false;
                    Matcher matcher;
                    if ((matcher = SCANNER_INFOS_PATTERN.matcher(line)).find())
                    {
                        String name = matcher.group(1);
                        double value = Double.parseDouble(matcher.group(2));
                        
                        listener.onInfos(name, value);
                    }
                    else if ((matcher = POWER_PATTERN.matcher(line)).find())
                    {
                        double power = Double.parseDouble(matcher.group(1));
                        
                        listener.onPower(power);
                    }
                    else if ((matcher = DATA_PATTERN.matcher(line)).find())
                    {
                        data = true;
                        String[] datas = line.split(" ");
                        
                        listener.onData(datas);
                    }
                    PulsarLogger.info(line, data);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public void start()
    {
        Thread thread = new Thread(this);
        thread.start();
    }
    
    public boolean isListening()
    {
        return listening;
    }
    
    public void setListening(boolean listening)
    {
        this.listening = listening;
    }
    
    public IDataListener getListener()
    {
        return listener;
    }
    
    public void setListener(IDataListener listener)
    {
        this.listener = listener;
    }
    
    public URLConnection getConnection()
    {
        return connection;
    }
    
    public InputStream getInputStream()
    {
        return in;
    }
    
    public static class DummyDataListener implements IDataListener
    {
        
        public void onInfos(String name, double value)
        {
            
        }
        
        public void onPower(double power)
        {
            
        }
        
        public void onData(String[] datas)
        {
            
        }
        
    }
}
