package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PinHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PinHomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "pinImg";
    private static final String ARG_PARAM2 = "lat";
    private static final String ARG_PARAM3 = "lon";
    private static final String ARG_PARAM4 = "locatName";
    private static final String ARG_PARAM5 = "title";
    private static final String ARG_PARAM6 = "body";
    private static final String ARG_PARAM7 = "userID";

    // TODO: Rename and change types of parameters
    private String mPinImg;
    private String mLat;
    private String mLon;
    private String mLocateName;
    private String mTitle;
    private String mBody;
    private String mUserId;

    public PinHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param mPinImg Parameter 1.
     * @param mLat Parameter 2.
     * @param mLon Parameter 3.
     * @param mLocateName Parameter 4.
     * @param mTitle Parameter 5.
     * @param mBody Parameter 6.
     * @param mUserId Parameter 7.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PinHomeFragment newInstance(String mPinImg, String mLat,String mLon,String mLocateName,String mTitle,String mBody,String mUserId) {
        PinHomeFragment fragment = new PinHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, mPinImg);
        args.putString(ARG_PARAM2, mLat);
        args.putString(ARG_PARAM3, mLon);
        args.putString(ARG_PARAM4, mLocateName);
        args.putString(ARG_PARAM5, mTitle);
        args.putString(ARG_PARAM6, mBody);
        args.putString(ARG_PARAM7, mUserId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPinImg = getArguments().getString(ARG_PARAM1);
            mLat = getArguments().getString(ARG_PARAM2);
            mLon = getArguments().getString(ARG_PARAM3);
            mLocateName = getArguments().getString(ARG_PARAM4);
            mTitle = getArguments().getString(ARG_PARAM5);
            mBody = getArguments().getString(ARG_PARAM6);
            mUserId = getArguments().getString(ARG_PARAM7);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }
}