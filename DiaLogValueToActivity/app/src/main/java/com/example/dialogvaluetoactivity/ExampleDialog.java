package com.example.dialogvaluetoactivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;
import java.util.Arrays;

public class ExampleDialog extends AppCompatDialogFragment {

    private CheckBox ck1,ck2,ck3,ck4;
    int count=0;
    ArrayList checkState = new ArrayList<>(Arrays.asList("null","null","null","null"));
    private ExampleDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_dialog, null);

        builder.setView(view)
                .setTitle("다이어로그 액티비티 값 전달 예제 최대 제한 2개")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String tmpStr="";
                        Log.v("선택된 값들:", String.valueOf(checkState));
                        for(int i=0;i<checkState.size();i++){
                            if(!checkState.get(i).equals("null")){
                                tmpStr += checkState.get(i)+",";
                            }
                        }
                        tmpStr = tmpStr.substring(0,tmpStr.length()-1);
                        Log.v("저장된 문자열:",tmpStr);

                        listener.applyTexts(tmpStr);
                    }
                });

        ck1 = view.findViewById(R.id.ch_test1);
        ck2 = view.findViewById(R.id.ch_test2);
        ck3 = view.findViewById(R.id.ch_test3);
        ck4 = view.findViewById(R.id.ch_test4);

        ck1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count<2) {
                    if (ck1.isChecked()) {
                        count++;
                        Log.v("ch1 체크상태:", "체크완료");
                        Log.v("count값:", String.valueOf(count));
                        checkState.set(0,ck1.getText());
                    } else {
                        count--;
                        Log.v("ch1 체크상태:", "체크해제");
                        Log.v("count값:", String.valueOf(count));
                        checkState.set(0,"null");
                    }
                }

                else if(count ==2 && !ck1.isChecked()){
                    count--;
                    checkState.set(0,"null");
                }

                else {
                    ck1.setChecked(false);
                }
            }
        });

        ck2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count<2){
                    if(ck2.isChecked()){
                        count++;
                        Log.v("ch2 체크상태:","체크완료");
                        Log.v("count값:", String.valueOf(count));
                        checkState.set(1,ck2.getText());
                    } else {
                        count--;
                        Log.v("ch2 체크상태:","체크해제");
                        Log.v("count값:", String.valueOf(count));
                        checkState.set(1,"null");
                    }
                }

                else if(count ==2 && !ck2.isChecked()){
                    checkState.set(1,"null");
                    count--;
                }

                else {
                    ck2.setChecked(false);
                }

            }
        });

        ck3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count<2){
                    if(ck3.isChecked()){
                        count++;
                        Log.v("ch3 체크상태:","체크완료");
                        Log.v("count값:", String.valueOf(count));
                        checkState.set(2,ck3.getText());
                    } else {
                        count--;
                        Log.v("ch3 체크상태:","체크해제");
                        Log.v("count값:", String.valueOf(count));
                        checkState.set(2,"null");
                    }
                }

                else if(count ==2 && !ck3.isChecked()){
                    checkState.set(2,"null");
                    count--;
                }

                else {
                    ck3.setChecked(false);
                    Log.v("count값:", String.valueOf(count));
                }


            }
        });

        ck4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count<2){
                    if(ck4.isChecked()){
                        count++;
                        Log.v("ch4 체크상태:","체크완료");
                        Log.v("count값:", String.valueOf(count));
                        checkState.set(3,ck4.getText());
                    } else {
                        count--;
                        Log.v("ch4 체크상태:","체크해제");
                        Log.v("count값:", String.valueOf(count));
                        checkState.set(3,"null");
                    }
                }

                else if(count ==2 && !ck4.isChecked()){
                    count--;
                    checkState.set(3,"null");
                }

                else {
                    ck4.setChecked(false);
                    Log.v("count값:", String.valueOf(count));
                }


            }
        });


        return builder.create();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface ExampleDialogListener{
        void applyTexts(String tmpStr);

    }
}
