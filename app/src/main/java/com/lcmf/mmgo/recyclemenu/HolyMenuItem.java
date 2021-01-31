package com.lcmf.mmgo.recyclemenu;

public class HolyMenuItem {

    private String name;
    private int imageId;

    public HolyMenuItem(String name, int imageId){
        this.name = name;
        this.imageId = imageId;

    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}