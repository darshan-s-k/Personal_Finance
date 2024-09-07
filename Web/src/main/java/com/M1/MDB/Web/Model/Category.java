package com.M1.MDB.Web.Model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "Categories")
public class Category {

    @Id
    private String id;
    private String userId;
    private String name;
    private double budget;
    private double spent;

    public Category() {}

    public Category(String userId, String name, double budget, double spent) {
        this.userId = userId;
        this.name = name;
        this.budget = budget;
        this.spent = spent;
    }

    // Getters and setters

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setSpent(double spent) {
        this.spent = spent;
    }
}
