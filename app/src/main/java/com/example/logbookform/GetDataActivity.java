package com.example.logbookform;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetDataActivity extends AppCompatActivity {
    RecyclerView dataView;
    TextView title;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_activity);
        Button back = findViewById(R.id.back);
        back.setOnClickListener(view -> {
            Intent i = new Intent(GetDataActivity.this, MainActivity.class);
            startActivity(i);
        });
        getData();
    }

    public void getData() {
        List<FormData> data = new ArrayList<>();
        String url = "http://10.0.2.2:3000/";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray array = jsonObject.getJSONArray("data");
                for (int i = 0; i < array.length();i++) {
                    JSONObject obj = array.getJSONObject(i);
                    FormData formData = new FormData();
                    formData.setPropertyType(obj.getString("propertyType"));
                    formData.setBedRoom(obj.getString("bedRoom"));

                    formData.setAddingDate(obj.getString("addingDate"));
                    formData.setMonthlyRentPrice(String.valueOf(obj.getDouble("monthlyRentPrice")));
                    formData.setFurnitureType(obj.getString("furnitureType"));
                    formData.setNotes(obj.getString("notes"));
                    formData.setReporterName(obj.getString("reporterName"));
                    data.add(formData);
                }
                dataView = findViewById(R.id.list_data);
                ItemAdapter itemAdapter = new ItemAdapter(data);
                dataView.setAdapter(itemAdapter);
                dataView.setLayoutManager(new LinearLayoutManager(this));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> error.printStackTrace());
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
