package com.example.roomtest.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class USER {
    @PrimaryKey
    private int uid;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String age;
    @ColumnInfo
    private String sex;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String toString(){
        return "InputMsg{" +
                "uid=" + uid +
                ", name='" + name +
                ", age='" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
