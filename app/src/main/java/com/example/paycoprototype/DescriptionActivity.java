package com.example.paycoprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

public class DescriptionActivity extends AppCompatActivity {
    
    //Adding parameters
    
    TimePickerDialog tpicker; // time picker dialog
    EditText eText;// object for the edittext in xml
    Button getButton;
    
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        //Now assigning
        
        eText=(EditText)  findViewById(R.id.timeEdit);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {


            }
        });
                

        
    }
}