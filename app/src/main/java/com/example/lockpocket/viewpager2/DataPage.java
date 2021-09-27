package com.example.lockpocket.viewpager2;

public class DataPage {
    int color;
    int image;
    String title;

    public DataPage(int image, String title){
        // this.color = color;
        this.image = image;
        this.title = title;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
