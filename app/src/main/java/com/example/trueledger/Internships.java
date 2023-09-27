package com.example.trueledger;

public class Internships {
    public String image;
    public String subtitle;
    public String title;
    public String priceinfo;
    public String id;



    public Internships() {
    }

    public Internships(String image, String subtitle, String title, String priceinfo,String id) {
        this.image = image;
        this.subtitle = subtitle;
        this.title = title;
        this.priceinfo = priceinfo;
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriceinfo() {
        return priceinfo;
    }

    public void setPriceinfo(String priceinfo) {
        this.priceinfo = priceinfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
