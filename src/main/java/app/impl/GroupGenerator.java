package app.impl;

import app.impl.Group;
import app.impl.Recipient;
import app.impl.Sender;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ALi on 04.04.2017.
 */
public class GroupGenerator {
    private ArrayList<Recipient> recipients;
    private ArrayList<Sender> potentialSenders;

    private BufferedReader victimsBufferedReader;

    public GroupGenerator() throws IOException {
        victimsBufferedReader = new BufferedReader(new FileReader("victims.utf8"));
        recipients = makeRecipientList();
        potentialSenders = makeSendersList();
    }

    public ArrayList<Recipient> getRecipients() {
        return recipients;
    }

    public Sender getSender() {
        Random random = new Random();
        return potentialSenders.get(random.nextInt());
    }

    private ArrayList<Sender> makeSendersList() throws IOException {
        String mailAddress;
        ArrayList<Sender> senders = new ArrayList<Sender>();
        while (!(mailAddress = victimsBufferedReader.readLine()).equals("***")) {
            senders.add(new Sender(mailAddress));
        }
        return senders;
    }

    private ArrayList<Recipient> makeRecipientList() throws IOException, IllegalArgumentException {
        String mailAddress;
        ArrayList<Recipient> recipients = new ArrayList<Recipient>();
        while (!(mailAddress = victimsBufferedReader.readLine()).equals("***")) {
        }
        while (!(mailAddress = victimsBufferedReader.readLine()).equals(null)) {
            recipients.add(new Recipient(mailAddress));
        }
        if (recipients.size() < 2) {
            throw new IllegalArgumentException("At least two recipients !! ");
        }
        return recipients;
    }

    public Group generateGroup() {

        return new Group(getSender(), getRecipients());
    }
}
