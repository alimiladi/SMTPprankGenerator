package app.impl;

import app.GroupGenerator;

import javax.security.auth.Subject;
import java.util.ArrayList;

/**
 * Created by ALi on 08.04.2017.
 */
public class Mail {

    private Group group;
    private Sender sender;
    private ArrayList<Recipient> recipients;
    private String subject;
    private String body;

    public Mail(Group group) {
        this.group = group;
        sender = group.getSender();
        recipients = group.getRecipients();
        subject = "";
        body = "";
    }

    public Mail(String subject, String body){
        this.subject = subject;
        this.body = body;
    }

    public Mail(){
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setGroup(Group group){
        this.group = group;
    }

    public void setSender(Sender sender){
        this.sender = sender;
    }

    public void setRecipients(ArrayList<Recipient> recipients){
        this.recipients = recipients;
    }

    public String getSubject (){
        return subject;
    }

    public String getBody(){
        return body;
    }
}
