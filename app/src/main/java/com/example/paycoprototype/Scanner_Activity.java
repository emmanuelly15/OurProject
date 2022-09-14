package com.example.paycoprototype;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Scanner_Activity extends AppCompatActivity {

    ImageView dispImg;
    Button presBtn;
    Bitmap bitmap;
    OutputStream outputStream;
    Button save;
    private static int REQUEST_CODE=100;
    String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        dispImg = findViewById(R.id.showImage);
        presBtn = findViewById(R.id.pressMe);

        //REQ for runtime CAMERA_PERMISSIONS
        if(ContextCompat.checkSelfPermission(Scanner_Activity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Scanner_Activity.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }

        presBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            bitmap = (Bitmap) data.getExtras().get("data");
            dispImg.setImageBitmap(bitmap);



            save= findViewById(R.id.SaveIMG);  //Trying to save image


            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    if(ContextCompat.checkSelfPermission(Scanner_Activity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED)
                    {
                        uploadImg();
                        returnToDescription();


                    }else{
                        askPermission();
                    }

                }
            });


            //  uploadImg();
        }
    }


// Change made here

    private void askPermission()
    {
        ActivityCompat.requestPermissions(Scanner_Activity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE);
        ActivityCompat.requestPermissions(Scanner_Activity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode==REQUEST_CODE)
        {
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
            {
                uploadImg();
            }else
            {
              //  Toast.makeText(Scanner_Activity.this, "Please provide the required permission", Toast.LENGTH_SHORT).show();
                askPermission();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void uploadImg()
    {
        BitmapDrawable drawable= (BitmapDrawable) dispImg.getDrawable();
        bitmap= drawable.getBitmap(); //Here you tried with Nem's bitmap

        File file= Environment.getExternalStorageDirectory();
        File dir= new File(file.getAbsolutePath()+"/PaycoFiles");
        if(!dir.exists())
        {
            dir.mkdir();
        }

        String filename= String.format("%d.png",System.currentTimeMillis());
        File outfile= new File(dir,filename);
        try
        {
            outputStream= new FileOutputStream(outfile);
        }catch (Exception e){e.printStackTrace();}

        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        try
        {
            outputStream.flush();
            outputStream.close();

        }catch(Exception v){v.printStackTrace();}

        //createImageFile();



    }

    private  void returnToDescription()

    {


        Intent intent;
        intent = new Intent(Scanner_Activity.this, DescriptionActivity.class);
        intent.putExtra("thePic", bitmap);
        startActivity(intent);


    }

} //End of the class don't delete

