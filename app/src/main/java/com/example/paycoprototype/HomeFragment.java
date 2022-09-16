package com.example.paycoprototype;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.paycoprototype.data.Document;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class HomeFragment extends Fragment{
    @Nullable
    TableLayout tl;

            Button AddBtn;
    private String base_url ="https://2ad1-41-113-95-53.eu.ngrok.io/document";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


    }
    public HomeFragment() {




        // Required empty public constructor



        //RequestBody requestBody = new MultipartBody.Builder()
                //.setType(MultipartBody.FORM)
                //.addFormDataPart("comment", comment.getText().toString())
                //.addFormDataPart("email", "alberto@gmail.com")
                //.addFormDataPart("title", "title demo ")
                //.addFormDataPart("fileformat", "fileformat demo ")
                //.addFormDataPart("location", "location demo ")
                //.addFormDataPart("amount", "1000.00 ")
                //.addFormDataPart("title", "Square Logo")
                //.addFormDataPart("image", "logo-square.png",
                        //RequestBody.create(MEDIA_TYPE_PNG, new File(imageFile)))
                //.build();

        Request request = new Request.Builder()
                //.header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
                .url(base_url)
                //.post(requestBody)
                .build();

        final OkHttpClient client = new OkHttpClient();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            //JSONArray jsa = new JSONArray(new Gson().toJson(response.body().string()));
            JSONArray jsa = new JSONArray(response.body().string());

            List<docData> data = new ArrayList<docData>();



            for (int i = 0; i < jsa.length() - 1; i++) {
                JSONObject js = jsa.getJSONObject(i);
                System.out.println(js.toString());

                docData dd = new docData();
                dd.number = js.getString("id");
                dd.file = js.getString("imagePath");
                dd.status = js.getString("status");
                data.add(dd);
            }

            //TableView<docData> table =


            System.out.println(response.body().string());
        } catch (Exception e) {
            System.out.println(e.toString());
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        Button btn = (Button) v.findViewById(R.id.AddDocbtn);

        tl = (TableLayout) v.findViewById(R.id.NotifTable);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), DescriptionActivity.class);

                getActivity().startActivity(in);
            }
        });
        return v;


    }


    }

class docData {
    public String number;
    public String file;
    public String status;
}