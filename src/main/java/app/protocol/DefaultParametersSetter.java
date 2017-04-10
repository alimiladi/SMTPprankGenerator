package app.protocol;

import java.io.*;
import java.util.Properties;

/**
 * @author Ali Miladi
 * @author Quentin Zeller
 */

/**
 * This is the parameter's setter of the application.
 * It is in fact responsible of finding out which SMTP server address is going to be used, which port number and how many
 * groups of victims are there.
 * It simply reads the properties file in the resources folder and offers getters to use them as String objects.
 */
public class DefaultParametersSetter {
    private String smtpServerAddress;
    private String smtpServerPort;
    private int numberOfGroups;
    private Properties defaultProperties;


    /**
     * Reads the properties with the help of a Properties object.
     *
     * @throws IOException
     */
    public DefaultParametersSetter() throws IOException {
        defaultProperties = new Properties();
        String dir = System.getProperty("user.dir");
        InputStream inputStream = new FileInputStream(dir + "/src/main/resources/config.properties");
        defaultProperties.load(inputStream);
        setDefaultParameters();
    }

    /**
     * Helper method that initializes the SMTP server coordinates ( address and port number ) and the number of groups
     * of victims to be used by the app.
     *
     * @throws IOException
     */
    private void setDefaultParameters() throws IOException {
        smtpServerAddress = defaultProperties.getProperty("smtpServerAddress");
        smtpServerPort = defaultProperties.getProperty("smtpServerPort");
        numberOfGroups = Integer.parseInt(defaultProperties.getProperty("groups"));
    }

    public String getSmtpServerAddress() {
        return smtpServerAddress;
    }

    public String getSmtpServerPort() {
        return smtpServerPort;
    }

    public int getNumberOfGroups() {
        return numberOfGroups;
    }


}
