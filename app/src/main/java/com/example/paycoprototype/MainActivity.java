package com.example.paycoprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    //public static String base_url ="https://c726-41-113-34-37.eu.ngrok.io";
    public static String base_url ="https://3b9e-41-113-122-143.eu.ngrok.io";
    public static JSONObject userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        //creating objects
        TextView username=(TextView) findViewById(R.id.username);
        TextView password=(TextView) findViewById(R.id.password);
        Button signinbtn=(Button)  findViewById(R.id.submitbtn);
        CheckBox staybnt= (CheckBox) findViewById(R.id.staybtn);


        //Now the action listeners

        //Sign-in button
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject js = new JSONObject();
                try {
                    js.put("email",username.getText());
                    js.put("password", password.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RequestBody requestBody = RequestBody.create(MediaType.parse("application/JSON"), js.toString());
                Request request = new Request.Builder()
                        .url(base_url + "/login")
                        .post(requestBody)
                        .build();

                final OkHttpClient client = new OkHttpClient().newBuilder()
                        .readTimeout(1, TimeUnit.MINUTES)
                        .connectTimeout(1, TimeUnit.MINUTES)
                        .build();
                try (Response response = client.newCall(request).execute()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                    userProfile = new JSONObject(response.body().string());
                    if (!userProfile.getString("errorMessage").equals("")){
                        //Toast.makeText(MainActivity.this, "Login failed",Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), userProfile.getString("errorMessage"),Toast.LENGTH_LONG).show();

                        return;

                    }
                    Toast.makeText(MainActivity.this, "Login successfull",Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent (MainActivity.this,MainActivity2.class);
                    startActivity(intent);

                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            }
        });
        //Stay_in button listener
        staybnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(staybnt.isChecked())
                {
                    Toast.makeText(MainActivity.this, "Login you will stay connected",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}