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

    public ArrayList<Mail> getMails(){
        return mails;
    }

    public Mail getRandomMail(){
        Random random = new Random();
        return mails.get(Math.abs(random.nextInt() % mails.size()));
    }
}
