package com.example.paycoprototype;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.security.Permission;

public class Scanner_Activity extends AppCompatActivity {

    private static final int PERMISSION_CODE = 1234;
    private static final int CAPTURE_CODE = 1001;
    MaterialButton Button;
    ImageView imageview;
    Uri image_uri;
    private Object ContentValues;
    private android.content.ContentValues values;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        Button = findViewById(R.id.capture_picture_Id);
        imageview = findViewById(R.id.ImageView);

        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.CAMERA) ==
                            PackageManager.PERMISSION_DENIED ||
                            checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                                    PackageManager.PERMISSION_DENIED) {

                        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission, PERMISSION_CODE);
                    } else{
                        openCamera();
                    }
                }else {
                    openCamera();
                }
            }
        });



    }

    public void openCamera() {

        ContentValues = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "new image");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");
        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent camintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        camintent.putExtra(MediaStore.EXTRA_OUTPUT,image_uri);
        startActivityForResult(camintent, CAPTURE_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case PERMISSION_CODE:
                if (grantResults.length>0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){
                    openCamera();
                }else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK){
            imageview.setImageURI(image_uri);
        }
    }
}
