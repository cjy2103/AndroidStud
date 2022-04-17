package com.example.textview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import com.example.textview.customtype.CustomTypefaceSpan;
import com.example.textview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewBinding();

        textViewColorSetting();

        textViewSizeStyleSetting();

        customStyleSetting();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * @DESC: 텍스트 색 변경
     */
    private void textViewColorSetting(){
        String txtColor = binding.tvColor.getText().toString();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(txtColor);

        String word = "색을";
        int start = txtColor.indexOf(word);
        int end = start + word.length();

        spannableStringBuilder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this,R.color.red)), start
                , end , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        binding.tvColor.setText(spannableStringBuilder);
    }

    /**
     * @DESC: 폰트 스타일, 크기 변경
     */
    private void textViewSizeStyleSetting(){
        String txtSizeStyle = binding.tvStyle.getText().toString();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(txtSizeStyle);

        String word = "속성을";
        int start = txtSizeStyle.indexOf(word);
        int end = start + word.length();

        spannableStringBuilder.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.setSpan(new RelativeSizeSpan(2.0f), start, end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);

        binding.tvStyle.setText(spannableStringBuilder);
    }

    /**
     * @DESC: 커스텀 폰트 설정
     */
    private void customStyleSetting(){
        Typeface tfMapleBold = Typeface.createFromAsset(this.getAssets(), "Maplestory Bold.ttf");
        String txtCustom = binding.tvMaple.getText().toString();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(txtCustom);

        String word = "메이플스토리 볼드체.";
        int start = txtCustom.indexOf(word);
        int end = start + word.length();

        spannableStringBuilder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this,R.color.red))
                , start, end , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.setSpan(new CustomTypefaceSpan(tfMapleBold)
                , start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.setSpan(new RelativeSizeSpan(1.5f)
                , start, end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);

        binding.tvMaple.setText(spannableStringBuilder);
    }

}