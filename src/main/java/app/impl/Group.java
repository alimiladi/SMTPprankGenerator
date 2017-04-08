package app.impl;

import app.GroupGenerator;

import java.util.ArrayList;

/**
 * Created by ALi on 08.04.2017.
 */
public class Group {

    private GroupGenerator groupGenerator;
    private Sender sender;
    private ArrayList<Recipient> recipients;

    public Group(GroupGenerator groupGenerator){
        this.groupGenerator = groupGenerator;
        sender = groupGenerator.getSender();
        recipients = groupGenerator.getRecipients();
    }

    public Group(Sender sender, ArrayList<Recipient> recipients) throws IllegalArgumentException{
        if(recipients.size() < 2){
            throw new IllegalArgumentException("At least two recipients !! ");
        }
        this.recipients = recipients;
        this.sender = sender;
    }

    public Sender getSender(){
        return sender;
    }

    public ArrayList<Recipient> getRecipients (){
        return recipients;
    }

}
