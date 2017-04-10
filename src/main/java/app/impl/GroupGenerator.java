package app.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * This utility class is used to easily generate a group of victims out of a list of victim's e-mail addresses.
 * The file is called
 */
public class GroupGenerator {
    private ArrayList<Recipient> recipients;
    private ArrayList<Sender> potentialSenders;

    private BufferedReader victimsBufferedReader;

    public GroupGenerator() throws IOException {
        String dir = System.getProperty("user.dir");
        victimsBufferedReader = new BufferedReader(new FileReader(dir + "/src/main/resources/victims.utf8"));
        makeSendersAndRecipientsLists();
    }

    public ArrayList<Recipient> getRecipients() {
        return recipients;
    }

    public Sender getRandomSender() {
        Random random = new Random();
        return potentialSenders.get(Math.abs(random.nextInt() % potentialSenders.size()));
    }

    private void makeSendersAndRecipientsLists() throws IOException, IllegalArgumentException {
        String mailAddress = new String();
        String separator = new String("***");
        potentialSenders = new ArrayList<Sender>();
        recipients = new ArrayList<Recipient>();
        while (!(mailAddress = victimsBufferedReader.readLine()).equals(separator)) {
            potentialSenders.add(new Sender(mailAddress));
        }
        while (!(mailAddress = victimsBufferedReader.readLine()).equals(separator)) {
            recipients.add(new Recipient(mailAddress));
        }
        if (recipients.size() < 2) {
            throw new IllegalArgumentException("At least two recipients !! ");
        }
        victimsBufferedReader.close();
    }

    public Group generateRandomGroup() {
        return new Group(getRandomSender(), getRecipients());
    }
}
