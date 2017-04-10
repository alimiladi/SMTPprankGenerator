package app.impl;
/**
 * @author Ali Miladi
 * @author Quentin Zeller
 */

/**
 * This class deals with a mail object.
 * A mail for us has just a subject and a body. We don't deal with senders and recipients in this level, there will be
 * added later.
 */
public class Mail {

    private String subject;
    private String body;

    /**
     * Sets the subject of a mail.
     *
     * @param subject ths subject to be assigned.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Sets the body of the mail.
     *
     * @param body the body to be assigned.
     */
    public void setBody(String body) {
        this.body = body;
    }


    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
}
