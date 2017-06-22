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
public class OrderLine {
    private int orderLineNumber;
    private int quantity;
    private Pallet pallet;
    private boolean acknowledgement;

    public OrderLine(int orderLineNumber, int quantity, Pallet pallet, boolean acknowledgement) {
        this.orderLineNumber = orderLineNumber;
        this.quantity = quantity;
        this.pallet = pallet;
        this.acknowledgement = acknowledgement;
    }
    
    public OrderLine() {
        
    }
    
    public int getOrderLineNumber() {
        return orderLineNumber;
    }

    public void setOrderLineNumber(int orderLineNumber) {
        this.orderLineNumber = orderLineNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Pallet getPallet() {
        return pallet;
    }

    public void setPallet(Pallet pallet) {
        this.pallet = pallet;
    }

    public boolean isAcknowledgement() {
        return acknowledgement;
    }

    public void setAcknowledgement(boolean acknowledgement) {
        this.acknowledgement = acknowledgement;
    }
    
}
