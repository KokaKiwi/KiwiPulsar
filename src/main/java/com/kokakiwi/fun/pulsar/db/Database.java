package com.kokakiwi.fun.pulsar.db;

import java.net.URI;
import java.util.List;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;
import com.google.common.collect.Lists;

public class Database
{
    private final EbeanServer server;
    
    public Database() throws Exception
    {
        ServerConfig config = new ServerConfig();
        config.setName("PulsarDatabase");
        config.setClasses(getClasses());
        
        DataSourceConfig sourceConfig = new DataSourceConfig();
        
        URI uri = new URI(System.getenv("DATABASE_URL"));
        System.out.println("[DEBUG] " + System.getenv("DATABASE_URL"));
        
        String username = uri.getUserInfo().split(":")[0];
        String password = uri.getUserInfo().split(":")[1];
        
        String dbUrl = "jdbc:postgresql://" + uri.getHost() + uri.getPath();
        
        sourceConfig.setUsername(username);
        sourceConfig.setUrl(dbUrl);
        sourceConfig.setPassword(password);
        sourceConfig.setDriver("org.postgresql.Driver");
        
        config.setDataSourceConfig(sourceConfig);
        server = EbeanServerFactory.create(config);
    }
    
    private List<Class<?>> getClasses()
    {
        return Lists.newLinkedList();
    }
}
