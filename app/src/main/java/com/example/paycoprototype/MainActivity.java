package com.example.paycoprototype;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //creating objects
        TextView username=(TextView) findViewById(R.id.username);
        TextView password=(TextView) findViewById(R.id.password);
        Button signinbtn=(Button)  findViewById(R.id.submitbtn);
        CheckBox staybnt= (CheckBox) findViewById(R.id.staybtn);


        //Now the action listeners

        //Sign-in button
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("emma") && password.getText().toString().equals("123"))
                {
                    Toast.makeText(MainActivity.this, "Login successfull",Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent (MainActivity.this,MainActivity2.class);
                    startActivity(intent);


                }else
                {
                    Toast.makeText(MainActivity.this, "Login failed",Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "Incorrect data entered",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Stay_in button listener
        staybnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(staybnt.isChecked())
                {
                    Toast.makeText(MainActivity.this, "Login you will stay connected",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}