package com.example.logbookform;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class MainActivity2 extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent i = getIntent();
        FormData formData = (FormData) i.getSerializableExtra("data");
        TextView detail = (TextView) findViewById(R.id.Detail);
        detail.setTextSize(16);
        detail.setText(Html.fromHtml("Property Name: " + "<b>" + formData.getPropertyType() + "</b>" + "<br>" +
                "Bed Room: "+ "<b>" + formData.getBedRoom() + "</b>" + "<br>" +
                "Adding Date: " + "<b>" + formData.getAddingDate() + "</b>" + "<br>" +
                "Monthly Rent Price: "+ "<b>" + formData.getMonthlyRentPrice() + "</b>" + "<br>" +
                "Furniture Type: " + "<b>" + formData.getFurnitureType() + "</b>" + "<br>" +
                "Notes: " + "<b>" + formData.getNotes() + "</b>" + "<br>" +
                "Reporter Name: " + "<b>" + formData.getPropertyType() + "</b>"));
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getRootView().getContext();
                boolean createSuccessful = new TableControllerDetail(context).create(formData);
                Log.d("state", String.valueOf(createSuccessful));
                if(createSuccessful) {
                    new AlertDialog.Builder(context)
                            .setTitle("Save Data Success")
                            .setMessage("Press button to back to form")
                            .setPositiveButton("Fill new One", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            }).show();
                } else {
                    new AlertDialog.Builder(context)
                            .setTitle("Save Data Failed")
                            .setMessage("Press button to back to form and check again")
                            .setPositiveButton("Back To Check", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                                    intent.putExtra("data", formData);
                                    startActivity(intent);
                                }
                            }).show();
                }
            }
        });
        Button back = findViewById(R.id.back);
        back.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
            intent.putExtra("data", formData);
            startActivity(intent);
        });
    }

}