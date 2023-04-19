package com.example.navigationgraph.data;

public class ItemIconRes {
    private int itemId;
    private int iconResId;

    public ItemIconRes(int itemId, int iconResId) {
        this.itemId    = itemId;
        this.iconResId = iconResId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }
}
