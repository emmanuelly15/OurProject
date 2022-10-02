package com.example.paycoprototype;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmentProfile} factory method to
 * create an instance of this fragment.
 */

public class fragmentProfile extends Fragment {

    public fragmentProfile() {
        // Required empty public constructor
    }

    //Button btn_logout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView name = (TextView) view.findViewById(R.id.txtName); //This line as well as the other (**)
        try {
            name.setText(MainActivity.userProfile.getString("name"));//(**) are what are used when linking the Login details
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView phone = (TextView) view.findViewById(R.id.txtphone); //This line as well as the other (**)
        try {
            phone.setText(MainActivity.userProfile.getString("telephone"));//(**) are what are used when linking the Login details
        } catch (JSONException e) {
            e.printStackTrace();
        }
/*
        TextView email = (TextView) view.findViewById(R.id.txtemail); //This line as well as the other (**)
        try {
            name.setText(MainActivity.userProfile.getString("email"));//(**) are what are used when linking the Login details
        } catch (JSONException e) {
            e.printStackTrace();
        }
*/
        Button btn_logout = (Button) view.findViewById(R.id.button);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), MainActivity.class);
                startActivity(in);
            }
        });

        return view;
    }
}