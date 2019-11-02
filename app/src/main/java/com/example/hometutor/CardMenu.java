package com.example.hometutor;

public class CardMenu {
    private int thumbnail;
    public CardMenu(){}
    public CardMenu(int thumbnail){
        this.thumbnail = thumbnail;
    }
    public int getThumbnail()
    {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
