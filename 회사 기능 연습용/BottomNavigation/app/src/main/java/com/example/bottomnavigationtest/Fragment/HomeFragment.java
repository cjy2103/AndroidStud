package com.example.bottomnavigationtest.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bottomnavigationtest.Fragment.Dialog.CustomDialog;
import com.example.bottomnavigationtest.MainActivity;
import com.example.bottomnavigationtest.R;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView txtStr;
    Button btnChange, btnCustomDialog, btnMoveFragment;
    MainActivity mainActivity;

    private int txtCount = 0;


    
    // 이부분 없으면 런타임 오류 발생
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    /**********************************************************************************
     ************************************* 생명주기 ************************************
     **********************************************************************************/




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainActivity = (MainActivity) requireActivity();

        initBinding(view);

        changeText();

        openDialog();

        changeFragment();
    }


    /**********************************************************************************
     *********************************** 사용자 함수 ************************************
     **********************************************************************************/

    /**
     * @DESC: 초기 바인딩
     * @param view
     */
    private void initBinding(View view){
        txtStr    = view.findViewById(R.id.txt_str);
        btnChange = view.findViewById(R.id.btn_change);
        btnCustomDialog = view.findViewById(R.id.btn_dialog);
        btnMoveFragment = view.findViewById(R.id.btn_move_fragment);
    }


    /**********************************************************************************
     *********************************** 이벤트함수 ************************************
     **********************************************************************************/

    /**
     * @DESC: 텍스트 변경
     */
    private void changeText(){
        btnChange.setOnClickListener(v->{
            if(txtCount==0){
                txtStr.setText("텍스트 변경");
                txtCount++;

            } else {
                txtStr.setText("아래 버튼을 클릭해보세요");
                txtCount--;
            }
        });

    }

    /**
     * @DESC: 다이얼로그 열기
     */
    private void openDialog(){
        btnCustomDialog.setOnClickListener(v->{
            CustomDialog customDialog = new CustomDialog();
            customDialog.show(requireActivity().getSupportFragmentManager(), "CustomDialog");
        });

    }

    /**
     * @DESC: 프래그먼트 이동
     */
    private void changeFragment(){
        btnMoveFragment.setOnClickListener(v->{
                mainActivity.homeFrgmentMove();
        });
    }

}