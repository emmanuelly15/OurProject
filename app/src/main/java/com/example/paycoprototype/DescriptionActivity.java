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

import java.util.Calendar;

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
                final Calendar cldr= Calendar.getInstance();
                int hour= cldr.get(Calendar.HOUR_OF_DAY);
                int minutes= cldr.get(Calendar.MINUTE);

                //Dialog for the time picker

                tpicker= new TimePickerDialog(DescriptionActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int sHour, int sMinute)
                    {
                        eText.setText(sHour+":"+sMinute);
                    }
                },hour,minutes,true

                );

                tpicker.show();

            }
        });

;


                


    }
}