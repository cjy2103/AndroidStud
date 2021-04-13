package com.example.alertdialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements DialogFragmentExample.OnCompleteListener{

    @Override
    public void onInputedData(String id, String pass) {
        Toast.makeText(this, id+"/"+pass, Toast.LENGTH_LONG).show();
    }

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
     *  함수설명: 다중선택
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
//        final List SelectedItems = new ArrayList();
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("AlertDialog Title");
//        builder.setMultiChoiceItems(items, null,
//                new DialogInterface.OnMultiChoiceClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                        if(isChecked){
//                            // 사용자가 체크한 경우 리스트에 추가
//                            SelectedItems.add(which);
//                        }
//                        else if(SelectedItems.contains(which)){
//                            //이미 리스트에 들어있던 아이템이면 제거
//                            SelectedItems.remove(Integer.valueOf(which));
//                        }
//                    }
//                });
//
//        builder.setPositiveButton("OK",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String msg = "";
//                        for(int i = 0; i< SelectedItems.size();i++){
//                            int index = (int) SelectedItems.get(i);
//
//                            msg = msg+"\n" + (i+1) + " : " + ListItems.get(index);
//                        }
//                        Toast.makeText(getApplicationContext(),
//                                "Total " + SelectedItems.size() + "Items Selected.\n" + msg ,
//                                Toast.LENGTH_LONG).show();
//                    }
//                });
//
//        builder.setNegativeButton("Cancel",
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
     *  함수설명: 단일선택
     */
//    void show()
//    {
//        final List<String> list_items = new ArrayList<>();
//        list_items.add("사과");
//        list_items.add("배");
//        list_items.add("귤");
//        list_items.add("바나나");
//        final CharSequence[] items = list_items.toArray(new String[list_items.size()]);
//
//        final List selected_items = new ArrayList();
//        int defaultItem = 0;
//        selected_items.add(defaultItem);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("AlertDialog Title");
//        builder.setSingleChoiceItems(items, defaultItem,
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        selected_items.clear();
//                        selected_items.add(which);
//                    }
//                });
//
//        builder.setPositiveButton("OK",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String msg = "";
//
//                        if(!selected_items.isEmpty()){
//                            int index = (int) selected_items.get(0);
//                            msg = list_items.get(index);
//                        }
//                        Toast.makeText(getApplicationContext(),
//                                "Items selected.\n" + msg , Toast.LENGTH_LONG).show();
//                    }
//                });
//
//        builder.setNegativeButton("Cancel",
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
     *  함수설명: 로그인창
     */
//    void show()
//    {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = getLayoutInflater();
//        View view = inflater.inflate(R.layout.dialog_login, null);
//        builder.setView(view);
//        final Button submit = (Button) view.findViewById(R.id.buttonSubmit);
//        final EditText email = (EditText) view.findViewById(R.id.edittextEmailAddress);
//        final EditText password = (EditText) view.findViewById(R.id.edittextPassword);
//
//        final AlertDialog dialog = builder.create();
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String strEmail = email.getText().toString();
//                String strPassword = password.getText().toString();
//                Toast.makeText(getApplicationContext(), strEmail + "/" + strPassword, Toast.LENGTH_LONG).show();
//                dialog.dismiss();
//            }
//        });
//
//        builder.show();
//
//    }

    /* @Dsec
     *  함수설명: 로그인창 Fragment
     */
    void show()
    {
        FragmentManager fm = getSupportFragmentManager();

        DialogFragment newFragment = new DialogFragmentExample();
        newFragment.show(fm, "dialog"); //"dialog"라는 태그를 갖는 프래그먼트를 보여준다.
    }


}