package com.example.youtubetest.noUse;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class MyListDecoration extends RecyclerView.ItemDecoration {

    private static final String TAG = MyListDecoration.class.getSimpleName();

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() -1 ){
            //outRect.right = 30;
            Log.v(TAG,"이거 실행됨");
        }
    }
}
