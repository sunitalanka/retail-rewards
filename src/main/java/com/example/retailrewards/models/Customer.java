package com.example.retailrewards.models;

import org.springframework.lang.NonNull;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends AbstractEntity{

   @NonNull
   private String name;

   @NonNull
   private String phoneNumber;

   @NotBlank
   @Email
   private String email;

   @OneToMany(mappedBy = "customer")
   private List<Purchase> purchasesList = new ArrayList<>();

   @Transient
   private int rewards;




   public Customer(){}

   public Customer(String name, String email, String phoneNumber){
      this.name = name;
      this.email = email;
      this.phoneNumber = phoneNumber;

   }


   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public List<Purchase> getPurchasesList() {
      return purchasesList;
   }

   public void setPurchasesList(List<Purchase> purchasesList) {
      this.purchasesList = purchasesList;
   }

   @Override
   public String toString(){
      return "Customer "+purchasesList;
   }

}
