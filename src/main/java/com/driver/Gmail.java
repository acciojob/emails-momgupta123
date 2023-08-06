package com.driver;

import java.util.ArrayList;
import java.util.Date;

public class Gmail extends Email {

    private int inboxCapacity; // maximum number of mails inbox can store
    private ArrayList<Mail> inbox;
    private ArrayList<Mail> trash;

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        inbox = new ArrayList<>();
        trash = new ArrayList<>();
    }

    public void receiveMail(Date date, String sender, String message) {
        if (inbox.size() >= inboxCapacity) {
            Mail oldestMail = inbox.get(0);
            inbox.remove(0);
            trash.add(oldestMail);
        }
        inbox.add(new Mail(date, sender, message));
    }

    public void deleteMail(String message) {
        for (int i = 0; i < inbox.size(); i++) {
            Mail mail = inbox.get(i);
            if (mail.getMessage().equals(message)) {
                inbox.remove(i);
                trash.add(mail);
                break;
            }
        }
    }

    public String findLatestMessage() {
        if (inbox.isEmpty()) {
            return null;
        }
        Mail latestMail = inbox.get(inbox.size() - 1);
        return latestMail.getMessage();
    }

    public String findOldestMessage() {
        if (inbox.isEmpty()) {
            return null;
        }
        Mail oldestMail = inbox.get(0);
        return oldestMail.getMessage();
    }

    public int findMailsBetweenDates(Date start, Date end) {
        int count = 0;
        for (Mail mail : inbox) {
            if (mail.getDate().after(start) && mail.getDate().before(end)) {
                count++;
            }
        }
        return count;
    }

    public int getInboxSize() {
        return inbox.size();
    }

    public int getTrashSize() {
        return trash.size();
    }

    public void emptyTrash() {
        trash.clear();
    }

    public int getInboxCapacity() {
        return inboxCapacity;
    }

    private static class Mail {
        private final Date date;
        private final String sender;
        private final String message;

        public Mail(Date date, String sender, String message) {
            this.date = date;
            this.sender = sender;
            this.message = message;
        }

        public Date getDate() {
            return date;
        }

        public String getSender() {
            return sender;
        }

        public String getMessage() {
            return message;
        }
    }
}
