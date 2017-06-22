/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fontys.logwear.demofrontend.Model;

import java.util.List;

/**
 *
 * @author Lukas Rolle(LukasRolleSE@gmail.com)
 */
public class Order {
    private List<OrderLine> orderLines;
    private List<Worker> workers;
    private boolean orderPacked;
    private int orderNumber;
    private Customer customer;

    public Order(List<OrderLine> orderLines, List<Worker> workers, boolean orderPacked, int orderNumber, Customer customer) {
        this.customer = customer;
        this.orderLines = orderLines;
        this.workers = workers;
        this.orderNumber = orderNumber;
        this.orderPacked = orderPacked;
    }

    public Order() {
        
    }
    
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public boolean isOrderPacked() {
        return orderPacked;
    }

    public void setOrderPacked(boolean orderPacked) {
        this.orderPacked = orderPacked;
    }
    
    static class Customer {
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

}
