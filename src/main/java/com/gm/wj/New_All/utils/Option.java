package com.gm.wj.New_All.utils;

import java.util.List;

public class Option {

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    int value;
    String label;
    List<Option> children = null;

    public List<Option> getChildren() {
        return children;
    }

    public void setChildren(List<Option> children) {
        this.children = children;
    }
}
