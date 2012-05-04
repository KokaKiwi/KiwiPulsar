package com.kokakiwi.fun.pulsar.db.config;

import java.util.List;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;
import com.kokakiwi.fun.pulsar.db.IDatabaseConfigurator;

public class RemoteMySQLConfigurator implements IDatabaseConfigurator
{
    
    public EbeanServer create(List<Class<?>> classes) throws Exception
    {
        ServerConfig config = new ServerConfig();
        config.setName("PulsarDatabase");
        config.setClasses(classes);
        config.setRegister(false);
        config.setDefaultServer(false);
        
        DataSourceConfig sourceConfig = new DataSourceConfig();
        
        String dbUrl = "jdbc:mysql://188.40.41.225/kokabsol_pulsar";
        
        sourceConfig.setUrl(dbUrl);
        sourceConfig.setUsername("kokabsol_pulsar");
        sourceConfig.setPassword("3K.GXA,%bTd]"); // Don't try to connect, the
                                                  // server has an access list
                                                  // ;)
        
        sourceConfig.setDriver("com.mysql.jdbc.Driver");
        
        config.setDataSourceConfig(sourceConfig);
        EbeanServer server = EbeanServerFactory.create(config);
        
        return server;
    }
    
}
