package app.impl;


/**
 * @author Ali Miladi
 * @author Quentin Zeller
 */

/**
 * This is a representation for a sender object by wrapping the email address.
 */
public class Sender{
    private String mailAddress;

    public Sender(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getMailAddress() {
        return mailAddress;
    }
}
