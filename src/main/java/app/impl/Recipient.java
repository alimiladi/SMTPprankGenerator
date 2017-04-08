package app.impl;

import app.Interfaces.Victim;

/**
 * Created by ALi on 04.04.2017.
 */
public class Recipient implements Victim {
    private String mailAddress;

    public Recipient(String mailAddress){
        this.mailAddress = mailAddress;
    }

    public String getMailAddress(){
        return mailAddress;
    }
}
