package com.mukeshkpdeveloper.mytask.models;

public class AllArtical {

    String image,title,discription;

    public AllArtical(String image, String title, String discription) {
        this.image = image;
        this.title = title;
        this.discription = discription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
