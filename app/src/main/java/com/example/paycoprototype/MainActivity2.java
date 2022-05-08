package com.example.paycoprototype;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity2 extends AppCompatActivity {

    private BottomNavigationView bn; //way to the Navigation bar in activity2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bn= findViewById(R.id.bottomnav);
        bn.setOnNavigationItemSelectedListener(bottomnavMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
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

                case R.id.home:
                    frangment1= new HomeFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container,frangment1).commit();
            return false;
        }
    };
}