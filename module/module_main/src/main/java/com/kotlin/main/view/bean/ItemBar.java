package com.kotlin.main.view.bean;

public class ItemBar {
    String name ;
    int imgIdSelect;
    int imgIdUnSelect;
    Class fragmentClass;

    public ItemBar(String name, int imgIdSelect, int imgIdUnSelect, Class fragmentClass) {
        this.name = name;
        this.imgIdSelect = imgIdSelect;
        this.imgIdUnSelect = imgIdUnSelect;
        this.fragmentClass = fragmentClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgIdSelect() {
        return imgIdSelect;
    }

    public void setImgIdSelect(int imgIdSelect) {
        this.imgIdSelect = imgIdSelect;
    }

    public int getImgIdUnSelect() {
        return imgIdUnSelect;
    }

    public void setImgIdUnSelect(int imgIdUnSelect) {
        this.imgIdUnSelect = imgIdUnSelect;
    }

    public Class getFragmentClass() {
        return fragmentClass;
    }

    public void setFragmentClass(Class fragmentClass) {
        this.fragmentClass = fragmentClass;
    }
}
