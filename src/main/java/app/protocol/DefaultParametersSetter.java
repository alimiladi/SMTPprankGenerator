package app.protocol;

import app.Interfaces.Victim;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ALi on 09.04.2017.
 */
public class DefaultParametersSetter {
    private String smtpServerAddress;
    private String smtpServerPort;
    private int numberOfGroups;
    private ArrayList<String> witnessesToCC;
    private BufferedReader defaultParameterSetterBufferedReader;

    public DefaultParametersSetter() throws IOException {
        defaultParameterSetterBufferedReader = new BufferedReader((new FileReader("config.properties")));
        setDefaultParameters();
    }

    private void setDefaultParameters() throws IOException {
        String witnessMailAddess ;
        smtpServerAddress = defaultParameterSetterBufferedReader.readLine();
        smtpServerPort = defaultParameterSetterBufferedReader.readLine();
        numberOfGroups = Integer.getInteger(defaultParameterSetterBufferedReader.readLine());
        while(!(witnessMailAddess = defaultParameterSetterBufferedReader.readLine()).equals(null)){
            witnessesToCC.add(witnessMailAddess);
        }
    }

    public String getSmtpServerAddress(){
        return smtpServerAddress;
    }

    public String getSmtpServerPort(){
        return smtpServerPort;
    }

    public ArrayList<String> getWitnessesToCC(){
        return witnessesToCC;
    }
    public int getNumberOfGroups(){
        return numberOfGroups;
    }
}
