package com.example.logbookform;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class TableControllerDetail extends DatabaseHandler {
    public TableControllerDetail(Context context) {
        super(context);
    }
    public boolean create(FormData formData) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("property_type", formData.getPropertyType());
        contentValues.put("bed_room", formData.getBedRoom());
        contentValues.put("adding_date", formData.getAddingDate());
        contentValues.put("monthly_rent_price", formData.getMonthlyRentPrice());
        contentValues.put("furniture_type", formData.getFurnitureType());
        contentValues.put("notes", formData.getNotes());
        contentValues.put("reporter_name", formData.getReporterName());
        SQLiteDatabase db = this.getWritableDatabase();
        boolean success = db.insert("details", null, contentValues) > 0;
        db.close();
        return success;
    }
}
