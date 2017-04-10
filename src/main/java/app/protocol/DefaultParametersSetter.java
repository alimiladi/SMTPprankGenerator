package app.protocol;

import app.Interfaces.Victim;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by ALi on 09.04.2017.
 */
public class DefaultParametersSetter {
    private String smtpServerAddress;
    private String smtpServerPort;
    private int numberOfGroups;
    private Properties defaultProperties;


    public DefaultParametersSetter() throws IOException {
        defaultProperties = new Properties();
        String dir = System.getProperty("user.dir");
        InputStream inputStream = new FileInputStream(dir + "/src/main/resources/config.properties");
        defaultProperties.load(inputStream);
        setDefaultParameters();
    }

    private void setDefaultParameters() throws IOException {
        smtpServerAddress = defaultProperties.getProperty("smtpServerAddress");
        smtpServerPort = defaultProperties.getProperty("smtpServerPort");
        numberOfGroups = Integer.parseInt(defaultProperties.getProperty("groups"));
    }

    public String getSmtpServerAddress(){
        return smtpServerAddress;
    }

    public String getSmtpServerPort(){
        return smtpServerPort;
    }

    public int getNumberOfGroups (){
        return numberOfGroups;
    }


}
