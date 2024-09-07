package com.M1.MDB.Web.Service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.search.ComparisonTerm;
import jakarta.mail.search.ReceivedDateTerm;
import jakarta.mail.search.SearchTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.M1.MDB.Web.Model.Transaction;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailServiceImpl implements EmailService {

    private static final String EMAIL = "transactionuser1@gmail.com";
    private static final String APP_PASSWORD = "raqb wbsf hngp hffc";
    private static final String REGEX = "Rs\\.(\\d+\\.\\d{2})\\s+has\\s+been\\s+(debited|credited)\\s+from\\s+account\\s+(\\*\\*\\d+)\\s+to\\s+VPA\\s+(\\S+)\\s+on\\s+(\\d{2}-\\d{2}-\\d{2})";

    @Autowired
    private TransactionService transactionService;

    @Override
    public void processMostRecentEmail() {
        Store store = null;
        Folder folder = null;

        try {
            store = getImapStore();
            folder = getFolderFromStore(store, "INBOX");

            Message[] messages = folder.search(getMessagesSearchTerm());
            if (messages.length > 0) {
                Message mostRecentMessage = messages[messages.length - 1];
                String content = extractTextFromMessage(mostRecentMessage);

                Pattern pattern = Pattern.compile(REGEX);
                Matcher matcher = pattern.matcher(content);

                if (matcher.find()) {
                    String amount = matcher.group(1);
                    String direction = matcher.group(2);
                    String from = matcher.group(3);
                    String to = matcher.group(4);
                    String dateStr = matcher.group(5);

                    String type = direction.equals("debited") ? "expense" : "income";
                    Date date = new SimpleDateFormat("dd-MM-yy").parse(dateStr);

                    Transaction transaction = new Transaction();
                    transaction.setUserId("666352838afab607fb765990d");
                    transaction.setAmount(Double.parseDouble(amount));
                    transaction.setType(type);
                    transaction.setFrom(from);
                    transaction.setTo(to);
                    transaction.setDate(date);

                    transactionService.addTransaction(transaction);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeFolder(folder);
            closeStore(store);
        }
    }

    private Store getImapStore() throws Exception {
        Session session = Session.getInstance(getImapProperties());
        Store store = session.getStore("imaps");
        store.connect("imap.gmail.com", EMAIL, APP_PASSWORD);
        return store;
    }

    private Properties getImapProperties() {
        Properties props = new Properties();
        props.put("mail.imaps.host", "imap.gmail.com");
        props.put("mail.imaps.ssl.trust", "imap.gmail.com");
        props.put("mail.imaps.port", "993");
        props.put("mail.imaps.starttls.enable", "true");
        props.put("mail.imaps.connectiontimeout", "10000");
        props.put("mail.imaps.timeout", "10000");
        return props;
    }

    private Folder getFolderFromStore(Store store, String folderName) throws MessagingException {
        Folder folder = store.getFolder(folderName);
        folder.open(Folder.READ_ONLY);
        return folder;
    }

    private SearchTerm getMessagesSearchTerm() {
        Date yesterdayDate = new Date(new Date().getTime() - (1000 * 60 * 60 * 24));
        return new ReceivedDateTerm(ComparisonTerm.GT, yesterdayDate);
    }

    private String extractTextFromMessage(Message message) throws MessagingException, IOException {
        StringBuilder textCollector = new StringBuilder();
        if (message.isMimeType("text/plain")) {
            textCollector.append(message.getContent().toString());
        } else if (message.isMimeType("multipart/*")) {
            Multipart multiPart = (Multipart) message.getContent();
            for (int i = 0; i < multiPart.getCount(); i++) {
                collectTextFromPart(textCollector, multiPart.getBodyPart(i));
            }
        }
        return textCollector.toString();
    }

    private void collectTextFromPart(StringBuilder textCollector, Part part) throws MessagingException, IOException {
        if (part.isMimeType("text/plain")) {
            textCollector.append(part.getContent().toString());
        } else if (part.isMimeType("multipart/*")) {
            Multipart multiPart = (Multipart) part.getContent();
            for (int i = 0; i < multiPart.getCount(); i++) {
                collectTextFromPart(textCollector, multiPart.getBodyPart(i));
            }
        }
    }

    private void closeFolder(Folder folder) {
        if (folder != null && folder.isOpen()) {
            try {
                folder.close(true);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    private void closeStore(Store store) {
        if (store != null && store.isConnected()) {
            try {
                store.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}
