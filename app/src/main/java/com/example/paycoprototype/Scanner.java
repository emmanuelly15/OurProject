package com.example.paycoprototype;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Scanner} factory method to
 * create an instance of this fragment.
 *
 */
public class Scanner extends Fragment {

    public Scanner()
    {
        // Required empty public constructor
    }

    //Parameters




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scanner, container, false);
    }
}