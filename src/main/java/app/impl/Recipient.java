package app.impl;

/**
 * @author Ali Miladi
 * @author Quentin Zeller
 */

/**
 * This is a representation for a recipient object by wrapping the email address.
 */
public class Recipient {
    private String mailAddress;

    public Recipient(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getMailAddress() {
        return mailAddress;
    }
}
