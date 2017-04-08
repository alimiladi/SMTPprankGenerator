package app.impl;
import app.Interfaces.Victim;

/**
 * Created by ALi on 04.04.2017.
 */
public class Sender implements Victim {
    private String mailAddress;
    public Sender (String mailAddress){
        this.mailAddress = mailAddress;
    }

    public String getMailAddress(){
        return mailAddress;
    }
}
