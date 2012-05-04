package com.kokakiwi.fun.pulsar.db;

import javax.persistence.*;

import com.kokakiwi.fun.pulsar.net.IDataListener;

@Entity
@Table(name = "pulsar_entries")
public class PulsarEntry
{
    @Id
    @Basic
    @Column(name = "id")
    private int                id;
    
    @Basic
    @Column(name = "type")
    private IDataListener.Type type;
    
    @Basic
    @Column(name = "line")
    private String             line;
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public IDataListener.Type getType()
    {
        return type;
    }
    
    public void setType(IDataListener.Type type)
    {
        this.type = type;
    }
    
    public String getLine()
    {
        return line;
    }
    
    public void setLine(String line)
    {
        this.line = line;
    }
}
