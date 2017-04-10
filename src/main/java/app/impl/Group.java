package app.impl;

import java.util.ArrayList;

/**
 * @author Ali Miladi
 * @author Quentin Zeller
 *
 * This class is deals with a group of victims.
 */
public class Group {

    private GroupGenerator groupGenerator;
    private Sender sender;
    private ArrayList<Recipient> recipients;

    /**
     * Group constructor.
     *
     * @param sender the sender object.
     * @param recipients a list of recipients.
     *
     * @throws IllegalArgumentException in case where there is less than two recipients.
     */
    public Group(Sender sender, ArrayList<Recipient> recipients) throws IllegalArgumentException{
        if(recipients.size() < 2){
            throw new IllegalArgumentException("At least two recipients !! ");
        }
        this.recipients = recipients;
        this.sender = sender;
    }

    /**
     * Getter for the sender object.
     *
     * @return the sender of this group.
     */
    public Sender getSender(){
        return sender;
    }

    /**
     * Getter for the recipients list.
     *
     * @return the list of recipients of this group.
     */
    public ArrayList<Recipient> getRecipients (){
        return recipients;
    }

}
