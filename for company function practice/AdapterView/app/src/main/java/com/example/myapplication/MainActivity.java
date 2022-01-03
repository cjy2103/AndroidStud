package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.lv_comment_view);
        MyAdapter adapter = new MyAdapter();
        adapter.addItem(new MyItem("a", "123"));
        adapter.addItem(new MyItem("bb", "010-123"));
        adapter.addItem(new MyItem("ccc", "010-123-234"));
        listView.setAdapter(adapter);
    }

    class MyAdapter extends BaseAdapter {
        private ArrayList<MyItem> items = new ArrayList<>();

        public void addItem(MyItem item) {
            items.add(item);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public MyItem getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, final View convertView, ViewGroup parent) {
            MyItemView view = new MyItemView(getApplicationContext());
            MyItem item = items.get(position);
            view.setId(item.getId());
            view.setPhone(item.getPhone());
            return view;
        }

    }
}