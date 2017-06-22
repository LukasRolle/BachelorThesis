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
    private int customerNumber;
    private boolean orderPacked;

    public Order(Customer customer, List<OrderLine> orderLines, List<Worker> workers, int orderNumber, int customerNumber, boolean orderPacked) {
        this.customer = customer;
        this.orderLines = orderLines;
        this.workers = workers;
        this.orderNumber = orderNumber;
        this.customerNumber = customerNumber;
        this.orderPacked = orderPacked;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
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

    static class OrderLine {

        private int orderLineNumber;
        private int quantity;
        private Pallet pallet;
        private boolean acknowledgement;
        private int orderNumber;
        private int palletNumber;

        public OrderLine(int orderLineNumber, int quantity, Pallet pallet, boolean acknowledgement, int orderNumber, int palletNumber) {
            this.orderLineNumber = orderLineNumber;
            this.quantity = quantity;
            this.pallet = pallet;
            this.acknowledgement = acknowledgement;
            this.orderNumber = orderNumber;
            this.palletNumber = palletNumber;
        }


        public OrderLine() {

        }

        public int getPalletNumber() {
            return palletNumber;
        }

        public void setPalletNumber(int palletNumber) {
            this.palletNumber = palletNumber;
        }

        public int getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(int orderNumber) {
            this.orderNumber = orderNumber;
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

        static class Pallet {

            private String storageLocation;
            private int articleNumber;
            private Article article;
            private int quantity;
            private int palletNumber;

            
            public Pallet(String storageLocation, int articleNumber, Article article, int quantity, int palletNumber) {
                this.storageLocation = storageLocation;
                this.articleNumber = articleNumber;
                this.article = article;
                this.quantity = quantity;
                this.palletNumber = palletNumber;
            }
            
            public Pallet() {

            }

            public int getArticleNumber() {
                return articleNumber;
            }

            public void setArticleNumber(int articleNumber) {
                this.articleNumber = articleNumber;
            }

            public String getStorageLocation() {
                return storageLocation;
            }

            public void setStorageLocation(String storageLocation) {
                this.storageLocation = storageLocation;
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

            static class Article {

                private int articleNumber;
                private String articleName;

                public Article(int articleNumber, String articleName) {
                    this.articleNumber = articleNumber;
                    this.articleName = articleName;
                }

                public Article() {

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

        }
    }

    static class Worker {

        int workerNumber;
        List<Order> orders;

        public Worker(int workerNumber) {
            this.workerNumber = workerNumber;
        }

        public Worker() {

        }

        public int getWorkerNumber() {
            return workerNumber;
        }

        public void setWorkerNumber(int workerNumber) {
            this.workerNumber = workerNumber;
        }
    }
}
