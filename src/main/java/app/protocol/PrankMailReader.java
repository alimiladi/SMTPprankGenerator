package app.protocol;

import app.impl.Mail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Ali Miladi
 * @author Quentin Zeller
 */

/**
 * This represents a wrapper for a file reader.
 * It is responsible for reading the file "prankMails.utf8" and storing a list of Mail objects.
 */
public class PrankMailReader {

    private BufferedReader prankMailBufferedReader;
    private ArrayList<Mail> mails;

    /**
     * Internally calls a helper method.
     *
     * @throws IOException
     */
    public PrankMailReader() throws IOException {
        String dir = System.getProperty("user.dir");
        prankMailBufferedReader = new BufferedReader(new FileReader(dir + "/src/main/resources/prankMails.utf8"));
        parseMails();
    }

    /**
     * Helper method that is responsible for initializing the list of mails attribute of this class.
     * It simply reads the "prankMails.utf8" file each time creating a Mail object and appending it to the mails list.
     * The mails have a specific form.
     * The first line is always the mail subject, and the following represent the body.
     * The mails are separated by the separator "###" which also denotes the end of the file.
     *
     * @throws IOException
     */
    private void parseMails() throws IOException {
        mails = new ArrayList<Mail>();
        String line = new String();
        Mail currentMail = new Mail();
        String separator = "###";
        while (!(line = prankMailBufferedReader.readLine()).equals("")) {
            StringBuilder body = new StringBuilder();
            currentMail.setSubject(line);
            line = prankMailBufferedReader.readLine();
            while (!(line.equals(separator))) {
                body.append(line);
                body.append('\n');
                line = prankMailBufferedReader.readLine();
            }
            currentMail.setBody(body.toString());
            mails.add(currentMail);
        }
    }

    public ArrayList<Mail> getMails() {
        return mails;
    }

    /**
     * A random mail getter for the purpose of the application.
     *
     * @return a randomly selected mail from the list pf mails.
     */
    public Mail getRandomMail() {
        Random random = new Random();
        return mails.get(Math.abs(random.nextInt() % mails.size()));
    }
}
