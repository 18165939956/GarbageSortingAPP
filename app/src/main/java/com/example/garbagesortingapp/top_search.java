package com.example.garbagesortingapp;

import java.io.Serializable;

public class top_search implements Serializable {
    private String key;
    private String name;
    private int imageId;

    public top_search(String name,int imageId){
        this.name = name;
        this.imageId = imageId;
        this.key = key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId(){
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "top_search{" +
                "name='" + name + '\'' +
                ", imageId=" + imageId + '\'' +
                ", key=" + key +
                '}';
    }
}
