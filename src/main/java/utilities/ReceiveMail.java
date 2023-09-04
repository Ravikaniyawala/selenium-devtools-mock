package utilities;

import javax.mail.Session;
import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReceiveMail{

    public static String getUrlFromEmail(String user,
                                         String password)
    {
        String result = "";
        final String host = "pop.gmail.com";
        try {

            //create properties field
            Properties properties = new Properties();

            properties.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.put("mail.pop3.socketFactory.fallback", "false");
            properties.put("mail.pop3.socketFactory.port", "995");
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.host", "pop.gmail.com");
            properties.put("mail.pop3.user", user);
            properties.put("mail.store.protocol", "pop3");
            properties.put("mail.pop3.ssl.protocols", "TLSv1.2");

            Authenticator auth = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user,password);
                }
            };

            // 3. Creating mail session.
            Session emailSession = Session.getDefaultInstance(properties, auth);

            //Session emailSession = Session.getDefaultInstance(properties);

            //create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("pop3");

            store.connect(host, user, password);

            //create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_WRITE);

            // retrieve the messages from the folder in an array
            Message[] messages = emailFolder.getMessages();

            System.out.println("messages.length---" + messages.length);


            Message message = messages[messages.length-1];
            System.out.println("---------------------------------");
            //System.out.println("Email Number " + (i + 1));
            System.out.println("Subject: " + message.getSubject());
            System.out.println("From: " + message.getFrom()[0]);

            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getUrl(mimeMultipart);
            System.out.println("Url: " + result);

            for (int i = 0; i < messages.length; i++) {
                message = messages[i];
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);

                String subject = message.getSubject();
                System.out.print("Do you want to delete this message [y/n] ? ");
                message.setFlag(Flags.Flag.DELETED, true);
                System.out.println("Marked DELETE for message: " + subject);

            }


            emailFolder.close(true);
            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String getUrl(
            MimeMultipart mimeMultipart)  throws MessagingException, IOException {
        String result = "";
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result = result + "\n" + bodyPart.getContent();
                break; // without break same text appears twice in my tests
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
            } else if (bodyPart.getContent() instanceof MimeMultipart){
                result = result + getUrl((MimeMultipart)bodyPart.getContent());
            }
        }

        final Pattern urlPattern = Pattern.compile(
                "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                        + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                        + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

        Matcher matcher = urlPattern.matcher(result);
        int matchStart = 0, matchEnd = 0;
        while (matcher.find()) {
            matchStart = matcher.start(1);
            matchEnd = matcher.end();
            // now you have the offsets of a URL match
        }

        String url = result.substring(matchStart,matchEnd);
        return url;
    }

    /*public static void main(String[] args) {

        String host = "mail.gmail.com";//change accordingly
        String mailStoreType = "pop3";
        String username= "abcd@gmail.com";
        String password= "xxxxx";//change accordingly

        receiveEmail("pop.gmail.com", "pop3", "fsnz.test.email@gmail.com", "Pass@1234");

    }*/
}
