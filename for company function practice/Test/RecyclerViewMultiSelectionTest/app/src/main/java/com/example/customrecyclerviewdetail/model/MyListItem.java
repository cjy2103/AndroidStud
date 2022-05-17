package com.example.customrecyclerviewdetail.model;

import java.io.Serializable;
import java.util.ArrayList;

public class MyListItem implements Serializable{
    ArrayList<ListItemModel> list;

    public ArrayList<ListItemModel> getList() {
        return list;
    }

    public void setList(ArrayList<ListItemModel> list) {
        this.list = list;
    }
}
