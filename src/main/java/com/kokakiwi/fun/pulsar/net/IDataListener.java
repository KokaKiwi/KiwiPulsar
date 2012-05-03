package com.kokakiwi.fun.pulsar.net;

public interface IDataListener
{
    public void onInfos(String name, double value);
    
    public void onPower(double power);
    
    public void onData(String[] datas);
    
    public void onLine(String line);
}
