package com.example.paycoprototype;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.paycoprototype.data.Document;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;



public class HomeFragment extends Fragment{

Button AddBtn;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview=inflater.inflate(R.layout.fragment_home, container, false);
        //Test to call activity from fragment here:
        AddBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

            }

        });


        return myview;
    }


}