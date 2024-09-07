package com.M1.MDB.Web.Service;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EmailParser {

    private static final String REGEX = "Rs\\.(\\d+\\.\\d{2})\\s+has\\s+been\\s+(debited|credited)\\s+from\\s+account\\s+(\\*\\*\\d+)\\s+to\\s+VPA\\s+(\\S+)\\s+on\\s+(\\d{2}-\\d{2}-\\d{2})";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    public ParsedEmail parse(String text) {
        Matcher matcher = PATTERN.matcher(text);

        if (matcher.find()) {
            String amount = matcher.group(1);
            String type = matcher.group(2);
            String from = matcher.group(3);
            String to = matcher.group(4);
            String date = matcher.group(5);

            return new ParsedEmail(amount, type, from, to, date);
        } else {
            throw new IllegalArgumentException("Text does not match the expected pattern");
        }
    }

    public static class ParsedEmail {
        private final String amount;
        private final String type;
        private final String from;
        private final String to;
        private final String date;

        public ParsedEmail(String amount, String type, String from, String to, String date) {
            this.amount = amount;
            this.type = type;
            this.from = from;
            this.to = to;
            this.date = date;
        }

        public String getAmount() {
            return amount;
        }

        public String getType() {
            return type;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }

        public String getDate() {
            return date;
        }
    }
}
