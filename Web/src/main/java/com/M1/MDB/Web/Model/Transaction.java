package com.M1.MDB.Web.Model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Document(collection = "Transactions")
public class Transaction {
    // Getters and setters
    @Id
    private String id;
    private String userId;
    private double amount;
    private Date date;
    private String type;
    private String category;
    private String from; // Sender UPI ID
    private String to;   // Receiver UPI ID

    public Transaction() {}

    public Transaction(String userId, double amount, Date date, String type, String category, String from, String to) {
        this.userId = userId;
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.category = category;
        this.from = from;
        this.to = to;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
