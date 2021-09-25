package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FollowersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FollowersFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "firstName";
    private static final String ARG_PARAM2 = "lastName";
    private static final String ARG_PARAM3 = "img";

    // TODO: Rename and change types of parameters
    private String mFirstName;
    private String mLastName;
    private String mImg;

    public FollowersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param mFirstName Parameter 1.
     * @param mLastName Parameter 2.
     * @param mImg Parameter 2.
     * @return A new instance of fragment FollowersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FollowersFragment newInstance(String mFirstName, String mLastName,String mImg) {
        FollowersFragment fragment = new FollowersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, mFirstName);
        args.putString(ARG_PARAM2, mLastName);
        args.putString(ARG_PARAM3, mImg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFirstName = getArguments().getString(ARG_PARAM1);
            mLastName = getArguments().getString(ARG_PARAM2);
            mImg = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_followers, container, false);
    }
}