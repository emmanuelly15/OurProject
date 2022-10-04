package com.example.paycoprototype;

import static com.example.paycoprototype.Scanner_Activity.REQUEST_CODE;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.github.dhaval2404.imagepicker.ImagePicker;


public class DescriptionActivity extends AppCompatActivity {

    private Button openb;
    private Button sendBtn;
    //private String base_url ="http://api.payco.gngengineering.co.za/api/ImageUpload/UploadImages";
    private String base_url ="https://6784-41-113-72-217.eu.ngrok.io/api/ImageUpload/UploadImages";
    // RequestQueue rq;

    private String imageFile;




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            imageFile = uri.getPath();
        }
    }


    public void openSomeActivityForResult() {

        ImagePicker.with(this)
                .crop()                 //Crop image(Optional), Check Customization for more option
                .compress(1024)          //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)
                .start();//Fi

        //Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //intent.setType("image/*");
        //intent.setAction(Intent.ACTION_GET_CONTENT);
        //Intent intent = new Intent(this, SomeActivity.class);
        //someActivityResultLauncher.launch(intent);
    }

    final ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        Uri uri = data.getData();
                        imageFile = uri.getPath();
                        //getFile
                        //imageFile = getFileName(uri);
                        //doSomeOperations();
                    }
                }
            });

    @SuppressLint("Range")
    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        //rq= Volley.newRequestQueue(this);
        setContentView(R.layout.activity_description);

        ActivityCompat.requestPermissions(DescriptionActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE);



        openb = (Button) findViewById(R.id.AttachButton);
        openb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                openSomeActivityForResult();
                //someActivityResultLauncher(intent);
                //startActivityForResult(intent, PICKFILE_REQUEST_CODE);

                /*Intent intent;
                intent = new Intent(docTest.this, MainActivity.class);
                startActivity(intent);*/
            }
        });


        //Listener for the sendButton

        sendBtn=(Button) findViewById(R.id.SendBtn);
        sendBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                EditText comment = (EditText) findViewById(R.id.commentfill); //Comment

                EditText email = (EditText) findViewById(R.id.emaildetails); //email
                EditText title = (EditText) findViewById(R.id.TitleFill); //Title
                EditText document = (EditText) findViewById(R.id.typefill); //Document Type
                EditText location = (EditText) findViewById(R.id.locationfill); //location
                EditText amount = (EditText) findViewById(R.id.AmountFill); //Amount




                final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("comment", comment.getText().toString())



                        .addFormDataPart("email", email.getText().toString())
                        .addFormDataPart("title", title.getText().toString())
                        .addFormDataPart("fileformat", document.getText().toString())
                        .addFormDataPart("location", location.getText().toString())
                        .addFormDataPart("amount", amount.getText().toString())
                        .addFormDataPart("title", "Square Logo")
                        .addFormDataPart("image", "logo-square.png",
                                RequestBody.create(MEDIA_TYPE_PNG, new File(imageFile)))
                        .build();

                Request request = new Request.Builder()
                        //.header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
                        .url(base_url)
                        .post(requestBody)
                        .build();

                final OkHttpClient client = new OkHttpClient();
                try (Response response = client.newCall(request).execute()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    System.out.println(response.body().string());
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        });

    }
}