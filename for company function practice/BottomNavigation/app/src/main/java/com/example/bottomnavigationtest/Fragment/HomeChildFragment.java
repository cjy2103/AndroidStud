package com.example.bottomnavigationtest.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bottomnavigationtest.MainActivity;
import com.example.bottomnavigationtest.R;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeChildFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeChildFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView tvTest;
    Button btnHomeMove, btnTvChange;
    MainActivity mainActivity;
    boolean change = false;

    public HomeChildFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeChildFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeChildFragment newInstance(String param1, String param2) {
        HomeChildFragment fragment = new HomeChildFragment();
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
        return inflater.inflate(R.layout.fragment_home_child, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainActivity = (MainActivity) requireActivity();

        initBinding(view);

        clickTvChange();

        clickHomeMove();
    }

    /**********************************************************************************
     *********************************** 사용자 함수 ************************************
     **********************************************************************************/

    private void initBinding(View view){
        tvTest      = view.findViewById(R.id.tv_test);
        btnHomeMove = view.findViewById(R.id.btn_close);
        btnTvChange = view.findViewById(R.id.btn_tv_change);
    }

    /**********************************************************************************
     *********************************** 이벤트함수 ************************************
     **********************************************************************************/

    private void clickTvChange(){
        btnTvChange.setOnClickListener(v->{
            change = !change;
            if(change) {
                tvTest.setText("변경된 텍스트");
            } else {
                tvTest.setText("Home의 하위 프래그먼트");
            }
        });
    }

    private void clickHomeMove(){
        btnHomeMove.setOnClickListener(v->{
            mainActivity.backHomeFragment();
        });
    }

}