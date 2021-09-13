package com.example.logbookform;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity2 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent i = getIntent();
        FormData formData = (FormData) i.getSerializableExtra("data");
        TextView detail = findViewById(R.id.Detail);
        detail.setTextSize(16);
        detail.setText(Html.fromHtml("Property Name: " + "<b>" + formData.getPropertyType() + "</b>" + "<br>" +
                "Bed Room: "+ "<b>" + formData.getBedRoom() + "</b>" + "<br>" +
                "Adding Date: " + "<b>" + formData.getAddingDate() + "</b>" + "<br>" +
                "Monthly Rent Price: "+ "<b>" + formData.getMonthlyRentPrice() + "</b>" + "<br>" +
                "Furniture Type: " + "<b>" + formData.getFurnitureType() + "</b>" + "<br>" +
                "Notes: " + "<b>" + formData.getNotes() + "</b>" + "<br>" +
                "Reporter Name: " + "<b>" + formData.getPropertyType() + "</b>"));
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(view -> {
            Context context = view.getRootView().getContext();
//            boolean createSuccessful = new TableControllerDetail(context).create(formData);
            String url = "http://10.0.2.2:3000/create";
            JSONObject requestBody = new JSONObject();
            try {
                requestBody.put("propertyType", formData.getPropertyType());
                requestBody.put("bedRoom", formData.getBedRoom());
                requestBody.put("addingDate", formData.getAddingDate());
                requestBody.put("monthlyRentPrice", formData.getMonthlyRentPrice());
                requestBody.put("furnitureType", formData.getFurnitureType());
                requestBody.put("notes", formData.getNotes());
                requestBody.put("reporterName", formData.getReporterName());

            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,url, requestBody, response -> {
                try {
                    Log.d("status", String.valueOf(response.get("status")));
                    if(response.get("status").equals(201)) {
                        new AlertDialog.Builder(context)
                        .setTitle("Save Data Success")
                        .setMessage("Press button to back to form")
                        .setPositiveButton("Fill new One", (dialog, which) -> {
                            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                            startActivity(intent);
                        }).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, error -> error.printStackTrace()) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json");
                    return headers;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(jsonObjectRequest);
//            }
        });
        Button back = findViewById(R.id.back);
        back.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
            intent.putExtra("data", formData);
            startActivity(intent);
        });
    }

}