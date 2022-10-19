package com.example.paycoprototype;

import static com.example.paycoprototype.Scanner_Activity.REQUEST_CODE;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    private String base_url = "https://6969-41-113-54-140.eu.ngrok.io/api/ImageUpload/UploadImages";
    // RequestQueue rq;

    private String imageFile;

    Spinner spinner;
    String[] options = {"Invoice", "Authorization", "Bill", "Tickets"};
    String value;

    EditText TitleFill, typefill, locationfill, emaildetails, commentfill, AmountFill;

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

        ActivityCompat.requestPermissions(DescriptionActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);

        Spinner Typedropdown=(Spinner) findViewById(R.id.dropdownList);//spinner is for dropdown
        ArrayAdapter<String> typeadapter= new ArrayAdapter<String>(DescriptionActivity.this,
                android.R.layout.simple_spinner_item, options);//adapter is for the change
        typeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Typedropdown.setAdapter(typeadapter);//this will get what is on the adapter
        String elSelected= Typedropdown.getSelectedItem().toString();
        /*spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                value = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

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

        sendBtn = (Button) findViewById(R.id.SendBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText comment = (EditText) findViewById(R.id.commentfill); //Comment
                if (comment.getText().toString().length() == 0 || comment.getText().toString().length() >= 200){
                    comment.requestFocus();
                    comment.setError("Field must contain at most 200 Characters");
                }
                EditText email = (EditText) findViewById(R.id.emaildetails); //email
                if (email.getText().toString().length() == 0 || !email.getText().toString().contains("@")){
                    email.requestFocus();
                    email.setError("Field cannot be empty and please enter a standard email address");
                }
                EditText title = (EditText) findViewById(R.id.TitleFill); //Title
                if (title.getText().toString().length() == 0){
                    title.requestFocus();
                    title.setError("Field Cannot Be Empty");
                }
                /*EditText document = (EditText) findViewById(R.id.typefill); //Document Type
                if (document.getText().toString().length()==0){
                    document.requestFocus();
                    document.setError("Field Cannot Be Empty");
                }*/
                EditText location = (EditText) findViewById(R.id.locationfill); //location
                if (location.getText().toString().length()==0){
                    location.requestFocus();
                    location.setError("Field Cannot Be Empty");
                }
                EditText amount = (EditText) findViewById(R.id.AmountFill); //Amount
                if (amount.getText().toString().length() == 0) {
                    amount.requestFocus();
                    amount.setError("Field Cannot Be Empty");
                }
                else if (!amount.getText().toString().matches( "[0-9]")){
                    amount.requestFocus();
                    amount.setError(("Please Enter in Numeral Values, No Currency or Letter"));
                }

                /*boolean check = validateinfo(title.getText().toString(), document.getText().toString(),
                        email.getText().toString(), location.getText().toString(),
                        amount.getText().toString());
                if (check == true) {
                    Toast.makeText(getApplicationContext(), "Data is Valid", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Sorry check Information again", Toast.LENGTH_SHORT).show();
                }*/


                final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("comment", comment.getText().toString())
                        .addFormDataPart("email", email.getText().toString())
                        .addFormDataPart("title", title.getText().toString())
                        .addFormDataPart("fileformat", elSelected)
                        .addFormDataPart("location", location.getText().toString())
                        .addFormDataPart("amount", amount.getText().toString())
                        .addFormDataPart("title", "Square Logo")
                        .addFormDataPart("image", "logo-square.png",
                                RequestBody.create(MEDIA_TYPE_PNG, new File(imageFile)))
                        .build();

/*
                AlertDialog.Builder albuilder = new AlertDialog.Builder(DescriptionActivity.this);
                albuilder.setCancelable(true);
                albuilder.setTitle("Payco");
                albuilder.setMessage("Your document was uploaded!");
                albuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                albuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Intent intent= new Intent (DescriptionActivity.this, HomeFragment.class);//this one will be the same still
                        //startActivity(intent);

                        Fragment hfrag = new HomeFragment();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container, hfrag).commit();

                    }
                });
                albuilder.show();
*/

                Request request = new Request.Builder()
                        //.header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
                        .url(base_url)
                        .post(requestBody)
                        .build();

                final OkHttpClient client = new OkHttpClient();
                try (Response response = client.newCall(request).execute()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    System.out.println(response.body().string());
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        });


    }
}
/*
    private Boolean validateinfo(String title, String document, String email,
                                 String location, String amount) {
        if (title.length() == 0){
            TitleFill.requestFocus();
            TitleFill.setError("Field Cannot Be Empty");
            return false;
        }
        else if (!title.matches("[a-zA-Z]+")){
            TitleFill.requestFocus();
            TitleFill.setError("Enter Only Alphabetical Characters");
            return false;
        }
        else if (document.length()==0){
            typefill.requestFocus();
            typefill.setError("Field Cannot Be Empty");
            return false;
        }
        else if (!document.matches("[a-zA-Z]+")){
            typefill.requestFocus();
            typefill.setError("Enter Only Alphabetical Characters");
            return false;
        }
        else if (email.length() == 0){
            emaildetails.requestFocus();
            emaildetails.setError("Field Cannot Be Empty");
            return false;
        }
        else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            emaildetails.requestFocus();
            emaildetails.setError("Enter Valid Email Address");
            return false;
        }
        else if (location.length() == 0) {
            locationfill.requestFocus();
            locationfill.setError("Field Cannot Be Empty");
            return false;
        }
        else if (!location.matches("[a-zA-Z]+")) {
            locationfill.requestFocus();
            locationfill.setError("Enter Only Alphabetical Characters");
            return false;
        }
        else if (amount.length() == 0) {
            AmountFill.requestFocus();
            AmountFill.setError("Field Cannot Be Empty");
            return false;
        }
        else if (!amount.matches("^[+][0-9]{10,13}")){
            AmountFill.requestFocus();
            AmountFill.setError(("Please Enter in Numeral Values, no Currency"));
            return false;
        }
        else{
            return true;
        }
    }
}
*/
/*
Spinner Typedropdown=(Spinner) findViewById(R.id.dropdownList);//spinner is for dropdown
ArrayAdapter<String> typeadapter= new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.TypeOption));//adapter is for the change
typeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
Typedropdown.setAdapter(typeadapter);//this will get what is on the adapter
String elSelected= Typedropdown.getSelectedItem().toString();//This is where the string for the selected element
Toast.makeText(getApplicationContext(),elSelected,Toast.LENGTH_SHORT).show(); //just to show








<Spinner
android:id="@+id/dropdownList"
android:layout_width="79dp"
android:layout_height="wrap_content"
android:layout_below="@id/label"
android:layout_marginTop="10dp"
android:layout_marginEnd="10dp" />
 */