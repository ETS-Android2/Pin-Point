package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PinImgFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PinImgFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "pinImg";

    // TODO: Rename and change types of parameters
    private String mPinImg;

    public PinImgFragment() {
        // Required empty public constructor
    }



    public static PinImgFragment newInstance(String pinImg) {
        PinImgFragment fragment = new PinImgFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, pinImg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPinImg = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pin_img, container, false);
    }
}