package com.example.room.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Data {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String car;
    @ColumnInfo
    private String price;
}
