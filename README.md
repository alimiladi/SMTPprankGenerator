# SMTPprankGenerator
A prank e-mail generator.
It send forged email using a third-party web server.
## Requirement
You chould have the last version of Java installed or at least the [Java runtime env. 7](https://www.java.com/fr/download/faq/release_dates.xml).

The code is build using maven. It's not an absolute requirement but easier to build with [Apache Maven](https://maven.apache.org/).

OSes such as Windows, OSx or UNIX/Linux should no cause any problem.
## Manual
You need to compile the project using Maven. The recommended action is tu use `mvn clean install` to build the entire project.
Then you can launch the .jar generated by the manipulation.
You will find also some configuration file in the ressource folder.
They contains :
- `config.properties` that is a key-value file that specify the server address and port of the smtp server we want to use. And how much mail we send.
- `prankMail.utf8` that contain the subject and the body of every message we want to send. All message should be separated by \###. The subject should begin by `Subject:` and the body sseparated form the subject by one carriage return.
- `victims.utf8` : That are the people we send the emails. Each set of victims are separated by ***.

As you certainly see they are multiple mails that we send to different group. They are recognized by the separator of each property file. At each separator we know it's part of a new mail that consist of a sender, receivers and text message

There is nothing more to know for a proper use of the application.
If you want to test it without annoying everyone, you can considere to use the [SMTP server emulator called MockMock](https://github.com/tweakers-dev/MockMock) from [tweakers-dev](https://github.com/tweakers-dev) team.
## The code
The `ressource` folder is where is contained the configuration file
The app folder is where the main app is, and contains 3 packages:
- the package `impl`, that contains itself 5 classes. `Group` a class that deal with a group of victime, `GroupGenerator` that generate a group of victim out of a list of victim email, `Mail` that construct the mail object with Subject and else, `Recipient` that consist of a recipient mail address, `Sender` that consist of a sender mail address.
- the package `interfaces` that ocntain the interface `victim`.
- The package `protocol` that contain the `SMTPprankClient` , the `PrankMailREader` and the `DefaultParameterSetterClass`
