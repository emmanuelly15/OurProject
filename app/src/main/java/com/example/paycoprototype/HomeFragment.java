package com.example.paycoprototype;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.ColorInt;
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
import android.widget.TableRow;
import android.widget.TextView;
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

import de.codecrafters.tableview.TableDataAdapter;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.providers.TableDataRowBackgroundProvider;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.TableDataRowBackgroundProviders;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class HomeFragment extends Fragment{

    @Nullable
    TableView<DocumentData> table;
    final List<DocumentData> documentData = new ArrayList<DocumentData>();;

    Button AddBtn;
    //private String base_url ="https://2ad1-41-113-95-53.eu.ngrok.io/document";
    private String base_url ="https://6969-41-113-54-140.eu.ngrok.io/document";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Request request = new Request.Builder()
                .url(base_url)
                .build();

        final OkHttpClient client = new OkHttpClient();
        //LayoutInflater lyf = LayoutInflater.from(this.getContext());
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            JSONArray jsa = new JSONArray(response.body().string());


            for (int i = 0; i < jsa.length(); i++) {
                DocumentData dd = new DocumentData();
                JSONObject js = jsa.getJSONObject(i);
                dd.email = js.getString("email");
                if (dd.email.matches(MainActivity.userProfile.getString("email"))) {
                    dd.number = js.getString("id");
                    dd.file = js.getString("imagePath");
                    dd.status = js.getString("status");

                    documentData.add(dd);
                }

            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        Button btn = (Button) v.findViewById(R.id.AddDocbtn);

        table = v.findViewById(R.id.tableView);
        table.setHeaderAdapter(new SimpleTableHeaderAdapter(getContext(), "Number", "File", "Status"));
        table.setDataAdapter(new DocumentDataTableDataAdapter(getContext(), documentData));
        //table.setDataRowBackgroundProvider(new DocumentDataTableDataAdapter(getContext(), documentData));
        int colorEvenRows = getResources().getColor(R.color.white);
        int colorOddRows = getResources().getColor(R.color.grey);
        table.setDataRowBackgroundProvider(TableDataRowBackgroundProviders.alternatingRowColors(colorEvenRows, colorOddRows));


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

class DocumentData {
    public String number;
    public String file;
    public String status;
    public String email;

}

class DocumentDataTableDataAdapter extends TableDataAdapter<DocumentData> implements TableDataRowBackgroundProvider<DocumentData> {

    public DocumentDataTableDataAdapter(Context context, List<DocumentData> data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        DocumentData data = getRowData(rowIndex);
        View v = null;

        switch (columnIndex) {
            case 0: //Number Columb//
                final TextView t = new TextView(getContext());
                t.setText(data.number);
                return t;
            case 1: //File Columb//
                final TextView file = new TextView(getContext());
                file.setText(data.file);
                return file;
            case 2://Status Columb//
                final TextView status = new TextView(getContext());
                status.setText(data.status);
                if(data.status.equalsIgnoreCase("Accepted")) {
                    status.setTextColor(Color.BLUE);
                    //status.set
                } else if(data.status.equalsIgnoreCase("Rejected")) {
                    status.setTextColor(Color.RED);
                }
                return status;
            case 3:
        }

        return v;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public Drawable getRowBackground(final int rowIndex, final DocumentData data) {
        int rowColor = getResources().getColor(R.color.white);
        if(data.status.equalsIgnoreCase("Accepted") ) {
            rowColor = R.color.select_blue;
        } else if(data.status.equalsIgnoreCase("Rejected")) {
            rowColor = R.color.purple_700;
        } else {
            rowColor = R.color.black;
        }

        return new ColorDrawable(rowColor);
    }

}

