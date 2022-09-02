package com.example.paycoprototype;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LoginActivity extends AppCompatActivity {

    private BottomNavigationView bn; //way to the Navigation bar in activity2


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  setContentView(T.layout.activity2_main);

        //creating objects
        TextView username=(TextView) findViewById(R.id.username);
        TextView password=(TextView) findViewById(R.id.password);
        Button signinbtn=(Button)  findViewById(R.id.submitbtn);
        CheckBox staybnt= (CheckBox) findViewById(R.id.staybtn);
        bn= findViewById(R.id.bottomnav);
        bn.setOnNavigationItemSelectedListener(bottomnavMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
        //Now the action listeners

        //Sign-in button
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("emma") && password.getText().toString().equals("123"))
                {
                    Toast.makeText(LoginActivity.this, "Login successful",Toast.LENGTH_SHORT).show();

                }else
                {
                    Toast.makeText(LoginActivity.this, "Login failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Stay_in button listener
        staybnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(staybnt.isChecked())
                {
                    Toast.makeText(LoginActivity.this, "Login you will stay connected",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //method for the navigation listener

    private BottomNavigationView.OnNavigationItemSelectedListener bottomnavMethod= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment frangment1=null;//how to call new fragment

            //switch to select the item selected here
            switch(menuItem.getItemId())
            {
                case R.id.scan:
                    frangment1= new Scanner();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container,frangment1).commit();
            return false;
        }
    };
}