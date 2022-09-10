package com.example.paycoprototype;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Scanner_Activity extends AppCompatActivity {

    ImageView dispImg;
    Button presBtn;
    Bitmap bitmap;

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

            uploadImg();
        }
    }
    private void uploadImg()
    {
    }

}
