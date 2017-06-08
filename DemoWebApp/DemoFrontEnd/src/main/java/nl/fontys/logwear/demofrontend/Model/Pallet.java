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
public class Pallet {
    private String location;
    private Article article;
    private int quantity;
    private int palletNumber;

    public Pallet(String location, Article article, int quantity, int palletNumber) {
        this.location = location;
        this.article = article;
        this.quantity = quantity;
        this.palletNumber = palletNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPalletNumber() {
        return palletNumber;
    }

    public void setPalletNumber(int palletNumber) {
        this.palletNumber = palletNumber;
    }
    
}
