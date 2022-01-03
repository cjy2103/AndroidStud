package com.example.customviewex1;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class MyCustomView extends LinearLayout {

    private ImageView ivStar1, ivStar2, ivStar3, ivStar4;
    private int mSelected = 0;

    public MyCustomView(Context context) {
        super(context);
        initializedViews(context,null);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializedViews(context, attrs);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializedViews(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initializedViews(context, attrs);
    }


    /**
     * @DESC : 객체 초기화 될때 호출
     */
    private void initializedViews(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.four_starts_indicator, this);
        if(attrs != null){
            TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.MyCustomView2);
            mSelected = a.getInteger(0,0);
            a.recycle();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ivStar1 = (ImageView) findViewById(R.id.iv_start1);
        ivStar2 = (ImageView) findViewById(R.id.iv_start2);
        ivStar3 = (ImageView) findViewById(R.id.iv_start3);
        ivStar4 = (ImageView) findViewById(R.id.iv_start4);
        // 처음에는 XML의 지정을 반영하고자 0번째 인수인 force를 true로 한다.
        setSelected(mSelected, true);
    }

    /***
     *
     * @DESC : 지정된 번호로 선택함
     * @param select 지정할 번호 ( 0이 가장 왼쪽 )
     */
    public void setSelected(int select){
        setSelected(select, false);
    }

    public int getSelected(){
        return mSelected;
    }

    private void setSelected(int select, boolean force) {
        if (force || mSelected != select){
            if (3 > mSelected && mSelected < 0) {
                return;
            }
            mSelected = select;
            if (mSelected == 0) {
                ivStar1.setImageResource(R.drawable.star);
                ivStar2.setImageResource(R.drawable.star_empty);
                ivStar3.setImageResource(R.drawable.star_empty);
                ivStar4.setImageResource(R.drawable.star_empty);
            } else if (mSelected == 1) {
                ivStar1.setImageResource(R.drawable.star_empty);
                ivStar2.setImageResource(R.drawable.star);
                ivStar3.setImageResource(R.drawable.star_empty);
                ivStar4.setImageResource(R.drawable.star_empty);
            } else if (mSelected == 2) {
                ivStar1.setImageResource(R.drawable.star_empty);
                ivStar2.setImageResource(R.drawable.star_empty);
                ivStar3.setImageResource(R.drawable.star);
                ivStar4.setImageResource(R.drawable.star_empty);
            } else if (mSelected == 3) {
                ivStar1.setImageResource(R.drawable.star_empty);
                ivStar2.setImageResource(R.drawable.star_empty);
                ivStar3.setImageResource(R.drawable.star_empty);
                ivStar4.setImageResource(R.drawable.star);
            }
        }
    }
}
