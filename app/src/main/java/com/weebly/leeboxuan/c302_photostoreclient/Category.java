package com.weebly.leeboxuan.c302_photostoreclient;

public class Category {
    private int cat_id;
    private String cat_name;
    private String cat_description;

    public Category(int cat_id, String cat_name, String cat_description){
        this.cat_id = cat_id;
        this.cat_name = cat_name;
        this.cat_description = cat_description;
    }

    public int getCat_id() {
        return cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public String getCat_description() {
        return cat_description;
    }
}

