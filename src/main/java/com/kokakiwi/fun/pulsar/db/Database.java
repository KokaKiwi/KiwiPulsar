package com.kokakiwi.fun.pulsar.db;

import java.util.List;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebeaninternal.api.SpiEbeanServer;
import com.avaje.ebeaninternal.server.ddl.DdlGenerator;
import com.google.common.collect.Lists;
import com.kokakiwi.fun.pulsar.db.config.LocalPostgreSQLConfigurator;
import com.kokakiwi.fun.pulsar.db.config.RemoteMySQLConfigurator;
import com.kokakiwi.fun.pulsar.net.IDataListener;
import com.kokakiwi.fun.pulsar.web.PulsarWeb;

public class Database implements IDataListener
{
    public final static IDatabaseConfigurator CONFIGURATOR = new RemoteMySQLConfigurator();
    
    private final PulsarWeb                   main;
    private final SpiEbeanServer              server;
    
    public Database(PulsarWeb main) throws Exception
    {
        this.main = main;
        
        server = (SpiEbeanServer) CONFIGURATOR.create(getClasses());
        
        createTables();
        register();
    }
    
    private void register()
    {
        main.getReceiver().addListener(this);
    }
    
    private void createTables()
    {
        try
        {
            server.find(PulsarEntry.class).findRowCount();
        }
        catch (Exception e)
        {
            DdlGenerator generator = server.getDdlGenerator();
            generator.runScript(false, generator.generateCreateDdl());
        }
    }
    
    private List<Class<?>> getClasses()
    {
        List<Class<?>> classes = Lists.newLinkedList();
        
        classes.add(PulsarEntry.class);
        
        return classes;
    }
    
    public EbeanServer getServer()
    {
        return server;
    }
    
    public void onInfos(String name, double value)
    {
        
    }
    
    public void onPower(double power)
    {
        
    }
    
    public void onData(String[] datas)
    {
        
    }
    
    public void onOther(String line)
    {
        
    }
    
    public void onLine(IDataListener.Type type, String line)
    {
        PulsarEntry entry = new PulsarEntry();
        entry.setType(type);
        entry.setLine(line);
        
        server.save(entry);
    }
}
