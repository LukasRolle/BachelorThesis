/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fontys.logwear.demofrontend.Model;

/**
 *
 * @author Lukas Rolle(LukasRolleSE@gmail.com)
 */
public class Customer {
    private int customerNumber;
    private String customerAddress;
    private String additionalWishes;
    
    public Customer(int customerNumber, String customerAddress, String additionalWishes) {
        this.customerNumber = customerNumber;
        this.customerAddress = customerAddress;
        this.additionalWishes = additionalWishes;
    }
    
    public Customer() {
        this.customerNumber = 0;
        this.customerAddress = "";
        this.additionalWishes = "";
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getAdditionalWishes() {
        return additionalWishes;
    }

    public void setAdditionalWishes(String additionalWishes) {
        this.additionalWishes = additionalWishes;
    }
    
    
}
