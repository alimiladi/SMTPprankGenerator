package app;

import app.impl.Group;
import app.protocol.DefaultParametersSetter;
import app.protocol.PrankMailReader;
import app.protocol.SMTPprankClient;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ALi on 09.04.2017.
 */
public class App {

    public static void main(String [] args) throws IOException {
        DefaultParametersSetter defaultParametersSetter = new DefaultParametersSetter();
        PrankMailReader prankMailReader = new PrankMailReader();
        SMTPprankClient client = new SMTPprankClient(defaultParametersSetter, prankMailReader);

        ArrayList<Group> groups = client.getGroups();

        for ( Group group : groups){
            client.connect();
            client.mailFrom(group);
            client.rcptTo(group);
            client.data(group, client.getRandomMail());
            client.endOfdata();
            client.disconnect();
        }


    }
}
