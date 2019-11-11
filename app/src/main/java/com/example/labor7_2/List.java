package com.example.labor7_2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.labor7_2.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link List.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link List#newInstance} factory method to
 * create an instance of this fragment.
 */
public class List extends Fragment {

RecyclerView myrec;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_list, container, false);
        myrec = (RecyclerView)v.findViewById(R.id.recyclerview);

        return v;
    }


}
