package com.kokakiwi.fun.pulsar.db.config;

import java.net.URI;
import java.util.List;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;
import com.kokakiwi.fun.pulsar.db.IDatabaseConfigurator;

public class LocalPostgreSQLConfigurator implements IDatabaseConfigurator
{
    
    public EbeanServer create(List<Class<?>> classes) throws Exception
    {
        ServerConfig config = new ServerConfig();
        config.setName("PulsarDatabase");
        config.setClasses(classes);
        config.setRegister(false);
        config.setDefaultServer(false);
        
        DataSourceConfig sourceConfig = new DataSourceConfig();
        
        URI uri = new URI(System.getenv("DATABASE_URL"));
        
        if (uri.getUserInfo() != null)
        {
            String username = uri.getUserInfo().split(":")[0];
            sourceConfig.setUsername(username);
            
            if (uri.getUserInfo().split(":").length > 1)
            {
                String password = uri.getUserInfo().split(":")[1];
                sourceConfig.setPassword(password);
            }
            else
            {
                sourceConfig.setPassword("");
            }
        }
        
        String dbUrl = "jdbc:postgresql://" + uri.getHost() + uri.getPath();
        
        sourceConfig.setUrl(dbUrl);
        sourceConfig.setDriver("org.postgresql.Driver");
        
        config.setDataSourceConfig(sourceConfig);
        EbeanServer server = EbeanServerFactory.create(config);
        
        return server;
    }
    
}
