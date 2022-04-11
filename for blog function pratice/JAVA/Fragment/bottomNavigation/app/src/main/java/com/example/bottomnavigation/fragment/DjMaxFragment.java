package com.example.bottomnavigation.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bottomnavigation.R;
import com.example.bottomnavigation.databinding.FragmentDjMaxBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DjMaxFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DjMaxFragment extends Fragment {

    private FragmentDjMaxBinding binding;
    private boolean changeImage = true;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DjMaxFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DjMaxFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DjMaxFragment newInstance(String param1, String param2) {
        DjMaxFragment fragment = new DjMaxFragment();
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
    private void clickBtnChange(){
        binding.btnChange.setOnClickListener(v->{
            if(changeImage){
                binding.imageView.setImageResource(R.drawable.iv_djmax_alice);
            } else {
                binding.imageView.setImageResource(R.drawable.iv_djmax_x_mas);
            }
            changeImage = !changeImage;
        });
    }


    /*********************************************************************************************************
     ****************************************** 사용자 함수 ***************************************************
     ********************************************************************************************************/

    private View viewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = FragmentDjMaxBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}