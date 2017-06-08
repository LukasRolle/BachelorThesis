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
public class Article {
    private int articleNumber;
    private String articleName;

    public Article(int articleNumber, String articleName) {
        this.articleNumber = articleNumber;
        this.articleName = articleName;
    }

    public int getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(int articleNumber) {
        this.articleNumber = articleNumber;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }
    
    
}
