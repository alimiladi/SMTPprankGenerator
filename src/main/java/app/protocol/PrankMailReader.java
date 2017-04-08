package app.protocol;

import app.GroupGenerator;
import app.impl.Mail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ALi on 08.04.2017.
 */
public class PrankMailReader {

    private BufferedReader prankMailBufferedReader;
    private ArrayList<Mail> mails;


    public PrankMailReader() throws IOException {
        prankMailBufferedReader = new BufferedReader(new FileReader("prankMails.utf8"));
        parseMails();
    }

    public void parseMails() throws IOException {
        String line;
        StringBuilder body = new StringBuilder();
        Mail currentMail = new Mail();
        while (!(line = prankMailBufferedReader.readLine()).equals(null)) {
            currentMail.setSubject(line);
            while (!line.equals("###")) {
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
}
