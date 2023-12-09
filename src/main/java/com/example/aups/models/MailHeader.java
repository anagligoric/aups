package com.example.aups.models;

public class MailHeader {
    private String recipient;
    private String sender;
    private String subject;

    public MailHeader(String recipient, String sender, String subject) {
        this.recipient = recipient;
        this.sender = sender;
        this.subject = subject;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
