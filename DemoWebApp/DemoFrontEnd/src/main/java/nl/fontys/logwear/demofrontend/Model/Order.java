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
    private Customer customer;
    private List<OrderLine> orderLines;
    private List<Worker> workers;
    private int orderNumber;
    private boolean orderPacked;

    public Order(Customer customer, List<OrderLine> orderLines, List<Worker> workers, int orderNumber, boolean orderPacked) {
        this.customer = customer;
        this.orderLines = orderLines;
        this.workers = workers;
        this.orderNumber = orderNumber;
        this.orderPacked = orderPacked;
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
    
    
}
