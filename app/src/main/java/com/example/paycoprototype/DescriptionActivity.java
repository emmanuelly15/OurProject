package com.example.paycoprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import com.example.paycoprototype.data.Document;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DescriptionActivity extends AppCompatActivity {

    //Adding parameters+

    //TimePickerDialog tpicker; // time picker dialog
   // EditText eText;// object for the edittext in xml
    //EditText titleText;
    //EditText typeText;
   // EditText locationText;
   // EditText commentText;
   // Button getButton;
    private Button openb;
    ImageView SentImg;


  //parameter for the list.

   // private Document item;
    //private List<Document> itemList = new ArrayList<Document>();

    //private String title= titleText.getText().toString();
    //private String type= typeText.getText().toString();
   // private String location= locationText.getText().toString();
    //private String comment= commentText.getText().toString();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        //Parameters declaration and assignment

        //This is for the rest.
        //Button  sendbt= (Button) findViewById(R.id.SendBtn) ; //Send button function
       // titleText=findViewById(R.id.TitleFill); //title
       // typeText=findViewById(R.id.typefill); //Document type
        //locationText=findViewById(R.id.locationfill); //Location
        //commentText=findViewById(R.id.commentfill); //comments section

        //This part is for the time picker only !!
       // eText= findViewById(R.id.timeEdit);
       // eText.setInputType(InputType.TYPE_NULL);
       // eText.setOnClickListener(new View.OnClickListener()
        //{

           // @Override
           // public void onClick(View view)
            //{
              //  final Calendar cldr= Calendar.getInstance();
               // int hour= cldr.get(Calendar.HOUR_OF_DAY);
                //int minutes= cldr.get(Calendar.MINUTE);

                //Dialog for the time picker

              //  tpicker= new TimePickerDialog(DescriptionActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    //@Override
                    //public void onTimeSet(TimePicker tp, int sHour, int sMinute)
                    //{
                       // eText.setText(sHour+":"+sMinute);
                  //  }
               // },hour,minutes,true

               // );

               // tpicker.show();

          //  }
       // });

        //This is the listener for the sentButton, I guess with the API controller !


        SentImg=(ImageView) findViewById(R.id.SentImage);
        openb = (Button) findViewById(R.id.AttachButton);
        openb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(DescriptionActivity.this, Scanner_Activity.class);
                 startActivity(intent);

                getThepic();



            }


        });

    }

    public void getThepic()
    {
        Bitmap bitreceiver= (Bitmap) this.getIntent().getParcelableExtra("thePic");
        SentImg.setImageBitmap(bitreceiver);

    }
}