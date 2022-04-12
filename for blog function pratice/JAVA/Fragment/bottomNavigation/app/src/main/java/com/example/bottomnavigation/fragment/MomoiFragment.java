package com.example.bottomnavigation.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bottomnavigation.R;
import com.example.bottomnavigation.databinding.FragmentMidoriBinding;
import com.example.bottomnavigation.databinding.FragmentMomoiBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MomoiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MomoiFragment extends Fragment {

    private FragmentMomoiBinding binding;
    private boolean changeImage = true;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MomoiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoMoiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MomoiFragment newInstance(String param1, String param2) {
        MomoiFragment fragment = new MomoiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

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
        return viewBinding(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        clickBtnChange();
    }


    /*********************************************************************************************************
     ****************************************** 이벤트 함수 ***************************************************
     ********************************************************************************************************/

    /**
     * @DESC: 이미지 변경 버튼클릭
     */
    private void clickBtnChange(){
        binding.btnChange.setOnClickListener(v->{
            if(changeImage){
                binding.imageView.setImageResource(R.drawable.iv_momoi_stand);
            } else {
                binding.imageView.setImageResource(R.drawable.iv_momoi);
            }
            changeImage = !changeImage;
        });
    }


    /*********************************************************************************************************
     ****************************************** 사용자 함수 ***************************************************
     ********************************************************************************************************/

    /**
     * @DESC: 뷰바인딩
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    private View viewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = FragmentMomoiBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}