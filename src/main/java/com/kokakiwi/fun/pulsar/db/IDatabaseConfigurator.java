package com.kokakiwi.fun.pulsar.db;

import java.util.List;

import com.avaje.ebean.EbeanServer;

public interface IDatabaseConfigurator
{
    public EbeanServer create(List<Class<?>> classes) throws Exception;
}
