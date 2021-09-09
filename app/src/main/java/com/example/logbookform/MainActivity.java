package com.example.logbookform;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText propertyType, bedRoom, monthlyRentPrice, furnitureType, notes, reporterName;
    Button submit;
    TextView addingDate;
    boolean validation = false;
    FormData formData ;
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        formData = intent.getSerializableExtra("data") == null ? new FormData() : (FormData) intent.getSerializableExtra("data");
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        formData.setAddingDate(sdf.format(new Date()));
        propertyType = findViewById(R.id.PropertyType);
        propertyType.setText(formData.getPropertyType());
        propertyType.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0) {
                    propertyType.setError("Please fill this field before Submit");
                }
                else {
                    formData.setPropertyType(propertyType.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
         bedRoom =  findViewById(R.id.BedRoom);
         bedRoom.setText(formData.getBedRoom());
        bedRoom.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0) {
                    bedRoom.setError("Please fill this field before Submit");
                }
                else {
                    formData.setBedRoom(bedRoom.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        addingDate = findViewById(R.id.AddingDate);
        addingDate.setText(formData.getAddingDate());
        Button selectDate = findViewById(R.id.selectDate);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
         selectDate.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, date, myCalendar
                         .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                         myCalendar.get(Calendar.DAY_OF_MONTH));
                 dialog.getDatePicker().setMaxDate(new Date().getTime());
                 dialog.show();
             }
         });

         monthlyRentPrice = findViewById(R.id.MonthlyRentPrice);
         monthlyRentPrice.setText(formData.getMonthlyRentPrice());
        monthlyRentPrice.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0) {
                    monthlyRentPrice.setError("Please fill this field before Submit");
                }
                else if (Integer.parseInt(charSequence.toString()) < 0) {
                    monthlyRentPrice.setError("Monthly rent price must be larger or equal 0");
                }
                else {
                    formData.setMonthlyRentPrice(monthlyRentPrice.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
         furnitureType =  findViewById(R.id.FurnitureType);
         furnitureType.setText(formData.getFurnitureType());
        furnitureType.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                formData.setFurnitureType(furnitureType.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
         notes =  findViewById(R.id.Notes);
         notes.setText(formData.getNotes());
        notes.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() > 100) {
                    notes.setError("The maximum length of notes are 100");
                }
                else {
                    formData.setNotes(notes.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
         reporterName = findViewById(R.id.ReporterName);
         reporterName.setText(formData.getReporterName());
         reporterName.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0) {
                    reporterName.setError("Please fill this field before Submit");
                }
                else {
                    formData.setReporterName(reporterName.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
         submit =  findViewById(R.id.submit);
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        addingDate.setText(sdf.format(myCalendar.getTime()));
        formData.setAddingDate(sdf.format(myCalendar.getTime()));
    }

    public void nextScreen(View view) {
        validation = checkValidation();
        if(validation) {
            Intent i = new Intent(MainActivity.this, MainActivity2.class);
            i.putExtra("data", formData);
            startActivity(i);
        }
    }

    private boolean checkValidation() {

        if(propertyType.length() <= 0) {
            propertyType.setError("Please fill this field before Submit");
            return false;
        }
        if(bedRoom.length() <= 0) {
            bedRoom.setError("Please fill this field before Submit");
            return false;
        }
        if(reporterName.length() <= 0) {
            reporterName.setError("Please fill this field before Submit");
            return false;
        }
        if(Integer.parseInt(monthlyRentPrice.getText().toString()) < 0) {
            monthlyRentPrice.setError("Price must be larger or equal 0");
            return false;
        }
        if(notes.length() > 100) {
            notes.setError("the maximum length of notes are 100");
            return false;
        }
        return true;
    }
}