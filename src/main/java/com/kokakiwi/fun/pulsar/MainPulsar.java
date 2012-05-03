package com.kokakiwi.fun.pulsar;

import com.kokakiwi.fun.pulsar.net.Receiver;

public class MainPulsar
{
    private final Receiver receiver;
    
    public MainPulsar()
    {
        receiver = new Receiver();
    }
    
    public void start()
    {
        receiver.start();
    }
    
    public void stop()
    {
        
    }
    
    public static void main(String[] args)
    {
        new MainPulsar().start();
    }
    
}
