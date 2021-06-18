package com.example.retailrewards.models;

import org.hibernate.annotations.TypeDef;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;


@Entity

public class Purchase extends AbstractEntity {

    @ManyToOne
    private Customer customer;

    @NonNull
    private String date;
    @NonNull
    private double amountSpent;

    @Transient
    private int rewards;



    public int getRewards() {
        return rewards;
    }

    public void setRewards(int rewards) {
        this.rewards = rewards;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(double amountSpent) {
        this.amountSpent = amountSpent;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    public Purchase(){}

    public Purchase(Customer aCustomer, String date, double aSpent){
        this.customer = aCustomer;
        this.date = date;
        this.amountSpent = aSpent;
    }


}
