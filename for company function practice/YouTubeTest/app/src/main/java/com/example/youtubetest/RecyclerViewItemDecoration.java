package com.example.youtubetest;

import android.content.Context;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//레퍼런스 블로그
//https://black-jin0427.tistory.com/102

public class RecyclerViewItemDecoration extends RecyclerView.ItemDecoration{

    private int size2;
    //private int size9;

    private int size1;
    //private int size8;

    private int halfWidth;

//    public RecyclerViewItemDecoration(Context context) {
//        size2 = dpToPx(context, 2);
//        //size9 = dpToPx(context, 9);
//        size1 = dpToPx(context, 1);
//        //size8 = dpToPx(context, 8);
//    }

    public RecyclerViewItemDecoration(int halfWidth) {
        this.halfWidth = halfWidth;
    }

    //dp를 -> px로 변경.
//    private int dpToPx(Context context, int dp){
//
//        return (int) TypedValue.
//                applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
//    }

    // 여러 삽질결과, 리사이클러뷰 패딩에 대해서는 작성하지 말고 -> 패딩은 xml에서,
    // 아이템1개에 대해서 생각을 해서 넣으니까 원하는게 나왔음(?);;
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildAdapterPosition(view); //각 아이템의 포지션
        int itemCount = state.getItemCount(); //전체 아이템 갯수

        if(position == 0) {
            outRect.left = halfWidth;
        }


        //아이템간 거리 2dp

        //상하 설정
        /*if(position == 0 || position == 1){
            //첫번재 줄 아이템
        }else{
        }*/

        //첫째행 탑
        /*if(position / 8 < 1){
            outRect.top = size9;
        }*/

        //모든 행 바텀
//        outRect.bottom = size2; //모든 아이템 바텀에 2dp
//
//        GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();
//        int spanIndex = lp.getSpanIndex();
//
//        outRect.right = size1;
//        outRect.left = size1;

        /*if(spanIndex == 0){
            //좌측 패딩
            outRect.left = size1 + size8;
        }else{
            outRect.left = size1;
        }

        if(spanIndex != 7){
            //아이템간 우측
            outRect.right = size1;
        }else{
            //끝열 우측 패딩
            outRect.right = size1 + size8;
        }*/
    }
}
