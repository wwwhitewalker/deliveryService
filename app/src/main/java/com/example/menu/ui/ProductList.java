package com.example.menu.ui;

public class ProductList {
    private int order_product_id;
    private int order_id;
    private int product_id;
    private String productName;
    private int quantity;
    private int price;
    private double total;

    public ProductList(){

    }
    public ProductList(String productName, int order_product_id,int order_id,int total, int product_id, int quantity,int price) {
        this.productName = productName;
        this.order_id = order_id;
        this.total = total;
        this.order_product_id = order_product_id;
        this.product_id = product_id;
        this.price = price;
        this.quantity = quantity;
    }

    public int getOrder_product_id() {
        return order_product_id;
    }

    public void setOrder_product_id(int order_product_id) {
        this.order_product_id = order_product_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
