package app.protocol;

import app.impl.Mail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ALi on 08.04.2017.
 */
public class PrankMailReader {

    private BufferedReader prankMailBufferedReader;
    private ArrayList<Mail> mails;


    public PrankMailReader() throws IOException {
        String dir = System.getProperty("user.dir");
        prankMailBufferedReader = new BufferedReader(new FileReader(dir + "/src/main/resources/prankMails.utf8"));
        parseMails();
    }

    public void parseMails() throws IOException {
        mails = new ArrayList<Mail>();
        String line;
        StringBuilder body = new StringBuilder();
        Mail currentMail = new Mail();
        while (!(line = prankMailBufferedReader.readLine()).equals(null)) {
            currentMail.setSubject(line);
            while (line != "###") {
                body.append(line);
                body.append('\n');
                line = prankMailBufferedReader.readLine();
            }
            currentMail.setBody(body.toString());
            mails.add(currentMail);
        }
    }

    public ArrayList<Mail> getMails(){
        return mails;
    }

    public Mail getRandomMail(){
        Random random = new Random();
        return mails.get(random.nextInt());
    }
}
