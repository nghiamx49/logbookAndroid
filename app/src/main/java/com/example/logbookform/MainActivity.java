package com.example.logbookform;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText monthlyRentPrice, notes, reporterName;
    Button submit;
    TextView addingDate;
    Spinner furnitureType, propertyType, bedRoom;
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
        final ArrayList<String> propertyList = new ArrayList<>();
        propertyList.add("Choose One");
        propertyList.add("Flat House");
        propertyList.add("Apartment");
        propertyList.add("Farm");
        propertyList.add("Building");
        ArrayAdapter<String> propertyAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item,propertyList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textview = (TextView) view;
                if (position == 0) {
                    textview.setTextColor(Color.GRAY);
                } else {
                    textview.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        propertyAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        propertyType.setAdapter(propertyAdapter);
        propertyType.setSelection(formData.getPropertyType().equals("") ? 0 : propertyList.indexOf(formData.getPropertyType()));
        propertyType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String valueSelected = propertyList.get(i);
                formData.setPropertyType(valueSelected);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
         bedRoom =  findViewById(R.id.BedRoom);
        final ArrayList<String> bedRoomList = new ArrayList<>();
        bedRoomList.add("Choose One");
        bedRoomList.add("Single Room");
        bedRoomList.add("Double Room");
        bedRoomList.add("Studio Room");
        ArrayAdapter<String> bedRoomAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item,bedRoomList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textview = (TextView) view;
                if (position == 0) {
                    textview.setTextColor(Color.GRAY);
                } else {
                    textview.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        propertyAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        bedRoom.setAdapter(bedRoomAdapter);
        bedRoom.setSelection(formData.getBedRoom().equals("") ? 0 : bedRoomList.indexOf(formData.getBedRoom()));
        bedRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String valueSelected = bedRoomList.get(i);
                formData.setBedRoom(valueSelected);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        addingDate = findViewById(R.id.AddingDate);
        addingDate.setText(formData.getAddingDate());
        Button selectDate = findViewById(R.id.selectDate);
        DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };
         selectDate.setOnClickListener(view -> {
             DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, date, myCalendar
                     .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                     myCalendar.get(Calendar.DAY_OF_MONTH));
             dialog.getDatePicker().setMaxDate(new Date().getTime());
             dialog.show();
         });

         monthlyRentPrice = findViewById(R.id.MonthlyRentPrice);
         monthlyRentPrice.setText(String.valueOf(formData.getMonthlyRentPrice()));
        monthlyRentPrice.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0) {
                    monthlyRentPrice.setError("Please fill this field before Submit");
                }
                else if (charSequence.toString().contains(".") && charSequence.toString().substring(charSequence.toString().indexOf(".")).length() > 3) {
                    monthlyRentPrice.setError("Only accept 2 digits after period.");
                }
                else if (Double.parseDouble(charSequence.toString()) < 0) {
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
         final ArrayList<String> strVal = new ArrayList<>();
         strVal.add("Choose One(Optional)");
         strVal.add("Furnished");
         strVal.add("Unfurnished");
         strVal.add("Part Furnished");
         ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item,strVal) {
             @Override
             public boolean isEnabled(int position) {
                 if (position == 0) {
                     return false;
                 } else {
                     return true;
                 }
             }
             @Override
             public View getDropDownView(int position, View convertView, ViewGroup parent) {
                 View view = super.getDropDownView(position, convertView, parent);
                 TextView textview = (TextView) view;
                 if (position == 0) {
                     textview.setTextColor(Color.GRAY);
                 } else {
                     textview.setTextColor(Color.BLACK);
                 }
                 return view;
             }
         };
         adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
         furnitureType.setAdapter(adapter);
         furnitureType.setSelection(formData.getPropertyType().equals("") ? 0 : strVal.indexOf(formData.getFurnitureType()));
         furnitureType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 String valueSelected = strVal.get(i);
                 if(valueSelected.equals("Choose One(Optional)")) {
                     formData.setFurnitureType("");
                 }
                 else {
                     formData.setFurnitureType(valueSelected);
                 }
             }

             @Override
             public void onNothingSelected(AdapterView<?> adapterView) {

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

        if(propertyType.getSelectedItem().toString().equals("Choose One")) {
           TextView error = (TextView) propertyType.getSelectedView();
           error.setError("Please select a valid property type before submit");
            return false;
        }
        if(bedRoom.getSelectedItem().toString().equals("Choose One")) {
            TextView error = (TextView) bedRoom.getSelectedView();
            error.setError("Please select a valid property type before submit");
            return false;
        }
        if(monthlyRentPrice.length() <= 0) {
            monthlyRentPrice.setError("Please fill this field before Submit");
            return false;
        }
        if(reporterName.length() <= 0) {
            reporterName.setError("Please fill this field before Submit");
            return false;
        }
        if(Double.parseDouble(monthlyRentPrice.getText().toString()) <= 0) {
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