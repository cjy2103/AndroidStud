package com.example.customlistviewmovedetail.listview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.example.customlistviewmovedetail.R;
import com.example.customlistviewmovedetail.databinding.CustomListviewBinding;


import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> listViewItems = new ArrayList<ListViewItem>();
    private Context mContext;

    /**
     * @DESC: Adapter에 사용되는 데이터의 개수
     * @return
     */
    @Override
    public int getCount() {
        return listViewItems.size();
    }
    /**
     * @DESC: 지정한 위치(position)에 있는 데이터
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return listViewItems.get(position);
    }

    /**
     * @DESC: 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * @DESC:  position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴
     * @param position
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        mContext = viewGroup.getContext();
        CustomListviewBinding binding;
        if(view == null){
            binding = CustomListviewBinding.inflate(LayoutInflater.from(mContext));
            view = binding.getRoot();
            view.setTag(R.id.list_view, binding);
        } else {
            binding = (CustomListviewBinding)view.getTag(R.id.list_view);
        }

        ListViewItem listViewItem = listViewItems.get(position);

        binding.imageView.setImageDrawable(listViewItem.getImage());
        binding.tvTitle.setText(listViewItem.getTitle());
        binding.tvDescribe.setText(listViewItem.getDescribe());

        return view;
    }

    /**
     * @DESC: 아이템 데이터 추가
     * @param image
     * @param title
     * @param describe
     */
    public void addItem(Drawable image, String title, String describe, String youtubeLink){
        ListViewItem item = new ListViewItem();

        item.setImage(image);
        item.setTitle(title);
        item.setDescribe(describe);
        item.setYoutubeLink(youtubeLink);

        listViewItems.add(item);
    }

    /**
     * @DESC: 아이템 초기화
     */
    public void clearItem(){
        listViewItems.clear();
    }


}
