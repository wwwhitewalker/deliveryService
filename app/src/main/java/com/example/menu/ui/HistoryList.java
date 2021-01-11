package com.example.menu.ui;

public class HistoryList {

    private String name ;
    //    private String Description;
    private int order_id;
    private int total;
    private long telephone;
    private String email ;
    private String address ;
    private String companyName;
    private String status;
    private String ddate ;
//    private String image_url;

    public HistoryList() {
    }

    public HistoryList(String name, String email,int total, int order_id, long telephone,String address,String companyName,String ddate) {
        this.name = name;
//        Description = description;
        this.order_id = order_id;
        this.total = total;
        this.telephone = telephone;
        this.email = email;
        this.address = address;
        this.status = address;
        this.companyName = companyName;
        this.ddate = ddate;
//        this.image_url = image_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDdate() {
        return ddate;
    }

    public void setDdate(String ddate) {
        this.ddate = ddate;
    }

    public String getName() {
        return name;
    }

//    public String getDescription() {
//        return Description;
//    }

    public int getOrderId() {
        return order_id;
    }

    public int getTotal() {
        return total;
    }

    public long getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

//    public String getImage_url() {
//        return image_url;
//    }


    public void setName(String name) {
        this.name = name;
    }

//    public void setDescription(String description) {
//        Description = description;
//    }

    public void setOrderId(int order_id) {
        this.order_id = order_id;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTelephone(long telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public void setImage_url(String image_url) {
//        this.image_url = image_url;
//    }
}
