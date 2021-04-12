package com.example.alertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });

    }
    /* @Dsec
    *  함수설명: 알림창 띄우는 함수
     */
//    void show()
//    {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("AlertDialog Title");
//        builder.setMessage("AlertDialog Content");
//        builder.setPositiveButton("예",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(getApplicationContext(),"예를 선택했습니다.",Toast.LENGTH_LONG).show(); // Toast.LENGTH_LONG = 토스트를 길게 보여주고 싶을때
//                    }
//                });
//        builder.setNegativeButton("아니오",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(getApplicationContext(),"아니오를 선택했습니다.",Toast.LENGTH_LONG).show();
//                    }
//                });
//        builder.show();
//    }

    /* @Dsec
     *  함수설명: 리스트로 알림창 띄우는 함수
     */
//    void show()
//    {
//        final List<String> ListItems = new ArrayList<>();
//        ListItems.add("사과");
//        ListItems.add("배");
//        ListItems.add("귤");
//        ListItems.add("바나나");
//        final CharSequence[] items = ListItems.toArray(new String[ListItems.size()]);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("AlertDialog Title");
//        builder.setItems(items, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int pos) {
//                String selectdText = items[pos].toString();
//                Toast.makeText(MainActivity.this, selectdText, Toast.LENGTH_SHORT).show(); //Toast.LENGTH_SHORT = 토스트를 짧게 보여주고 싶을때
//            }
//        });
//
//        builder.show();
//
//    }

    /* @Dsec
     *  함수설명: 입력창
     */
//    void show()
//    {
//        final EditText editText = new EditText(this);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("AlertDialog Title");
//        builder.setMessage("AlertDialog Content");
//        builder.setView(editText);
//        builder.setPositiveButton("입력",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(getApplicationContext(), editText.getText(), Toast.LENGTH_LONG).show();
//                    }
//                });
//
//        builder.setNegativeButton("취소",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//
//        builder.show();
//
//    }

    /* @Dsec
     *  함수설명: 입력창
     */
    void show()
    {
        final List<String> ListItems = new ArrayList<>();
        ListItems.add("사과");
        ListItems.add("배");
        ListItems.add("귤");
        ListItems.add("바나나");
        final CharSequence[] items = ListItems.toArray(new String[ListItems.size()]);

        final List SelectedItems = new ArrayList();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("AlertDialog Title");
        builder.setMultiChoiceItems(items, null,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked){
                            // 사용자가 체크한 경우 리스트에 추가
                            SelectedItems.add(which);
                        }
                        else if(SelectedItems.contains(which)){
                            //이미 리스트에 들어있던 아이템이면 제거
                            SelectedItems.remove(Integer.valueOf(which));
                        }
                    }
                });

        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String msg = "";
                        for(int i = 0; i< SelectedItems.size();i++){
                            int index = (int) SelectedItems.get(i);

                            msg = msg+"\n" + (i+1) + " : " + ListItems.get(index);
                        }
                        Toast.makeText(getApplicationContext(),
                                "Total " + SelectedItems.size() + "Items Selected.\n" + msg ,
                                Toast.LENGTH_LONG).show();
                    }
                });

        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        builder.show();

    }


}