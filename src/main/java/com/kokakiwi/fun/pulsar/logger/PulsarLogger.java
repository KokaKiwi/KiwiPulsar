package com.kokakiwi.fun.pulsar.logger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class PulsarLogger
{
    private final static Logger LOGGER      = Logger.getLogger("Pulsatron");
    private final static Logger DATA_LOGGER = Logger.getLogger("Pulsatron-Data");
    
    static
    {
        LOGGER.setUseParentHandlers(false);
        DATA_LOGGER.setUseParentHandlers(false);
        
        for (Handler handler : LOGGER.getHandlers())
        {
            LOGGER.removeHandler(handler);
        }
        for (Handler handler : DATA_LOGGER.getHandlers())
        {
            DATA_LOGGER.removeHandler(handler);
        }
        
        try
        {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("d-M-y-H-m");
            String d = dateFormat.format(date);
            
            FileHandler logFileHandler = new FileHandler("session-" + d
                    + ".log", true);
            FileHandler dataFileHandler = new FileHandler("data-" + d + ".log",
                    true);
            
            PulsarLoggerFormatter formatter = new PulsarLoggerFormatter();
            logFileHandler.setFormatter(formatter);
            dataFileHandler.setFormatter(formatter);
            
            LOGGER.addHandler(logFileHandler);
            DATA_LOGGER.addHandler(dataFileHandler);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void info(String line, boolean data)
    {
        LOGGER.info(line);
        if (data)
        {
            DATA_LOGGER.info(line);
        }
    }
    
    public static class PulsarLoggerFormatter extends Formatter
    {
        
        @Override
        public String format(LogRecord record)
        {
            StringBuilder sb = new StringBuilder();
            
            sb.append(record.getMessage());
            sb.append('\n');
            
            return sb.toString();
        }
        
    }
}
