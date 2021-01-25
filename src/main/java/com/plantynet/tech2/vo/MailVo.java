package com.plantynet.tech2.vo;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@ConfigurationProperties(prefix = "mail")
@Getter
@Setter
@ToString
public class MailVo
{
    private String hostName;//host_name, host-name 등 알아서 맞춰줌...
    private int port;
    private List<String> strList;
    private Map<String, String> strMap;
    private List<Map<String, String>> mapList;

    /*public String getHostName()
    {
        return hostName;
    }
    public void setHostName(String hostName)
    {
        this.hostName = hostName;
    }
    public int getPort()
    {
        return port;
    }
    public void setPort(int port)
    {
        this.port = port;
    }
    public List<String> getStrList()
    {
        return strList;
    }
    public void setStrList(List<String> strList)
    {
        this.strList = strList;
    }
    public Map<String, String> getStrMap()
    {
        return strMap;
    }
    public void setStrMap(Map<String, String> strMap)
    {
        this.strMap = strMap;
    }
    public List<Map<String, String>> getMapList()
    {
        return mapList;
    }
    public void setMapList(List<Map<String, String>> mapList)
    {
        this.mapList = mapList;
    }
    
    @Override
    public String toString()
    {
        return "TestVo [hostName=" + hostName + ", port=" + port + ", strList=" + strList + ", strMap=" + strMap + ", mapList=" + mapList + "]";
    }*/
}