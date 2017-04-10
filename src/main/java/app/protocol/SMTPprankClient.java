package app.protocol;

import app.impl.*;
import sun.nio.cs.StandardCharsets;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * This class represents an SMTP client which talks to the SMTP server whose coordinates are defined in the configuration
 * file.
 * <p>
 * The client starts a socket with the server's coordinates and starts talking to it with usual SMTP commands.
 */
public class SMTPprankClient {

    private DefaultParametersSetter defaultParametersSetter;
    private PrankMailReader prankMailReader;
    private GroupGenerator groupGenerator;
    private ArrayList<Group> groups;
    private Mail mail;

    private String smtpServerAddress;
    private String smtpServerPort;

    private BufferedReader reader;
    private PrintWriter writer;
    private Socket socket;

    /**
     * Initialization of the class attributes from a defaultParameterSetter and a prankMailReader
     *
     * @param defaultParametersSetter provides the server's coordinates and the number of groups.
     * @param prankMailReader         provides a list of mails and a random mail.
     * @throws IOException
     */
    public SMTPprankClient(DefaultParametersSetter defaultParametersSetter, PrankMailReader prankMailReader) throws IOException {
        this.defaultParametersSetter = defaultParametersSetter;
        smtpServerAddress = defaultParametersSetter.getSmtpServerAddress();
        smtpServerPort = defaultParametersSetter.getSmtpServerPort();
        this.prankMailReader = prankMailReader;
        groupGenerator = new GroupGenerator();
        generateRandomGroups();
        mail = prankMailReader.getRandomMail();
    }

    /**
     * Acts as a builder for the client.
     * Finishes the establishment of a list of groups.
     */
    private void generateRandomGroups() {
        groups = new ArrayList<Group>();
        int nbGroups = defaultParametersSetter.getNumberOfGroups();
        for (int i = 0; i < nbGroups; i++) {
            groups.add(groupGenerator.generateRandomGroup());
        }
    }


    /**
     * Connects the client to the SMTP server.
     * The client sends *EHLO server" and reads the server's response.
     *
     * @throws IOException
     */
    public void connect() throws IOException {
        int port = Integer.parseInt(smtpServerPort);
        socket = new Socket(smtpServerAddress, port);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
        writer.println(Protocol.CMD_EHLO);
        writer.flush();
        while (!(reader.readLine().startsWith("250 "))) ;
    }

    /**
     * Makes the client send the command MAIL FROM: <Sender's mail address>.
     *
     * @param group The group from which to retreive the sender's email address.
     * @throws IOException
     */
    public void mailFrom(Group group) throws IOException {
        writer.print(Protocol.CMD_MAIL_FROM);
        writer.flush();
        writer.println(group.getSender().getMailAddress());
        writer.flush();
        while (!(reader.readLine().startsWith("250 "))) ;
    }

    /**
     * Makes the client send the command RCPT TO: <Recipient's email address> for all the recipients that are defined
     * in the group.
     *
     * @param group the group from which to retzreive the recipient's list.
     * @throws IOException
     */
    public void rcptTo(Group group) throws IOException {
        for (Recipient recipient : group.getRecipients()) {
            writer.print(Protocol.CMD_RCPT_TO);
            writer.flush();
            writer.println(recipient.getMailAddress());
            writer.flush();
            while (!(reader.readLine().startsWith("250 "))) ;
        }
    }

    /**
     * Makes the client send the command DATA to the server.
     * It than enters the mail coordinates, which are the From and To secctions.
     * It also prints the mail passed in argument as Subject: <subject>
     * than the body of the mail.
     *
     * @param group the group from where to take the victim's addresses.
     * @param mail  the mail from where to retrieve the subject and body to be sent.
     * @throws IOException
     */
    public void data(Group group, Mail mail) throws IOException {
        writer.println(Protocol.CMD_DATA);
        writer.flush();
        while (!(reader.readLine().startsWith("354 "))) ;
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


    /**
     * Signals the end of the data section to the server. This is done by a <CRLF>.<CRLF>.
     *
     * @throws IOException
     */
    public void endOfdata() throws IOException {
        writer.println("\r\n");
        writer.flush();
        writer.println(".");
        writer.flush();
        writer.println("\r\n");
        writer.flush();
        while (!(reader.readLine().startsWith("250 "))) ;
    }

    /**
     * Sends the command QUIT to the server.
     * Closes the streams.
     *
     * @throws IOException
     */
    public void disconnect() throws IOException {
        writer.println(Protocol.CMD_QUIT);
        writer.flush();
        reader.close();
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public Mail getRandomMail() {
        return mail;
    }

    /**
     * Helper class to store the SMTP commands.
     */
    class Protocol {
        public static final String CMD_EHLO = "EHLO server";
        public static final String CMD_MAIL_FROM = "MAIL FROM: ";
        public static final String CMD_RCPT_TO = "RCPT TO: ";
        public static final String CMD_DATA = "DATA";
        public static final String CMD_QUIT = "QUIT";
    }
}
