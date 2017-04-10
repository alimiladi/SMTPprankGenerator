package app.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
/**
 * @author Ali Miladi
 * @author Quentin Zeller
 */

/**
 * This utility class is used to easily generate a group of victims out of a list of victim's e-mail addresses.
 * The file is called "victims.utf8" and contains two sets of email addresses.
 * The first set is made of addresses of the potential senders of the prank mails, and the second one represents the
 * recipients.
 * The two sets of email addresses are divided separated by a "***" separator.
 * The end of the file is also delimited by a "***" delimiter.
 */
public class GroupGenerator {
    private ArrayList<Recipient> recipients;
    private ArrayList<Sender> potentialSenders;
    private BufferedReader victimsBufferedReader;

    /**
     * A groups generator constructor.
     *
     * @throws IOException
     */
    public GroupGenerator() throws IOException {
        String dir = System.getProperty("user.dir");
        victimsBufferedReader = new BufferedReader(new FileReader(dir + "/src/main/resources/victims.utf8"));
        makeSendersAndRecipientsLists();
    }

    /**
     * Getter for the recipients list of the generated group.
     *
     * @return the recipients list.
     */
    public ArrayList<Recipient> getRecipients() {
        return recipients;
    }

    /**
     * This method returns a sender from the list of senders defined in the file *victims.utf8".
     * It simply picks a random sender and returns it.
     *
     * @return a random sender from the senders list.
     */
    public Sender getRandomSender() {
        Random random = new Random();
        return potentialSenders.get(Math.abs(random.nextInt() % potentialSenders.size()));
    }

    /**
     * Utility method called in the constructor.
     * Used as a builder for this class, in deed it parses the file victims.utf8 and initializes ths lists of senders
     * and recipients.
     *
     * @throws IOException
     * @throws IllegalArgumentException in case where there ara less than two recipients in the file.
     */
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


    /**
     * Generates a random group. Which makes it random is the sender's address.
     * The recipients addresses are always the same.
     *
     * @return a random group of victims.
     */
    public Group generateRandomGroup() {
        return new Group(getRandomSender(), getRecipients());
    }
}
