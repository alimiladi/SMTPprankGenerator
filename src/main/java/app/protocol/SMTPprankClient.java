package app.protocol;

import app.impl.*;
import sun.nio.cs.StandardCharsets;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by ALi on 04.04.2017.
 */
public class SMTPprankClient {

    private DefaultParametersSetter defaultParametersSetter;
    private PrankMailReader prankMailReader;
    private GroupGenerator groupGenerator;
    private ArrayList<Group> groups;
    private Mail mail;

    private String smtpServerAddress;
    private String getSmtpServerPort;

    private BufferedReader reader;
    private PrintWriter writer;
    private Socket socket;

    public SMTPprankClient(DefaultParametersSetter defaultParametersSetter, PrankMailReader prankMailReader) throws IOException {
        this.defaultParametersSetter = defaultParametersSetter;
        smtpServerAddress = defaultParametersSetter.getSmtpServerAddress();
        getSmtpServerPort = defaultParametersSetter.getSmtpServerPort();
        this.prankMailReader = prankMailReader;
        groupGenerator = new GroupGenerator();
        generateRandomGroups();
        mail = prankMailReader.getRandomMail();
    }

    private void generateRandomGroups() {
        groups = new ArrayList<Group>();
        int nbGroups = defaultParametersSetter.getNumberOfGroups();
        for (int i = 0; i < nbGroups; i++) {
            groups.add(groupGenerator.generateRandomGroup());
        }
    }

    public void connect() throws IOException {
        socket = new Socket(smtpServerAddress, Integer.getInteger(getSmtpServerPort));
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
        writer.println(Protocol.CMD_EHLO);
        writer.flush();
        while(!(reader.readLine().startsWith("250 ")));
    }

    public void mailFrom(Group group) throws IOException {
        writer.print(Protocol.CMD_MAIL_FROM);
        writer.flush();
        writer.println(group.getSender().getMailAddress());
        writer.flush();
        while(!(reader.readLine().startsWith("250 ")));
    }

    public void rcptTo(Group group) throws IOException {
        for (Recipient recipient : group.getRecipients()){
            writer.print(Protocol.CMD_RCPT_TO);
            writer.flush();
            writer.println(recipient.getMailAddress());
            writer.flush();
            while(!(reader.readLine().startsWith("250 ")));
        }
    }

    public void data(Group group, Mail mail) throws IOException {
        writer.println(Protocol.CMD_DATA);
        writer.flush();
        while (!(reader.readLine().startsWith("354 ")));
        writer.println("From: " + group.getSender().getMailAddress());
        writer.flush();
        for (Recipient recipient : group.getRecipients()) {
            writer.println("To: " + recipient.getMailAddress());
            writer.flush();
        }
        writer.println("Subject: " + mail.getSubject());
        writer.flush();
        writer.println();
        writer.flush();
        writer.print(mail.getBody());
        writer.flush();
    }

    public void endOfdata() throws IOException {
        writer.println("\r\n");
        writer.flush();
        writer.println(".");
        writer.flush();
        writer.println("\r\n");
        writer.flush();
        while(!(reader.readLine().startsWith("250 ")));
    }

    public void disconnect(){
        writer.println(Protocol.CMD_QUIT);
        writer.flush();
    }

    public ArrayList<Group> getGroups (){
        return groups;
    }

    public Mail getRandomMail(){
        return mail;
    }


    class Protocol {
        public static final String CMD_EHLO = "EHLO ali";
        public static final String CMD_MAIL_FROM = "MAIL FROM: ";
        public static final String CMD_RCPT_TO = "RCPT TO: ";
        public static final String CMD_DATA = "DATA";
        public static final String CMD_QUIT = "QUIT";
    }
}
