package com.example.logbookform;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

    private List<FormData> formDataList;

    public ItemAdapter(List<FormData> formDataList) {
        this.formDataList = formDataList;
    }

    @Override
    public ItemHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView =inflater.inflate(R.layout.items, parent, false);
        ItemHolder itemHolder = new ItemHolder(itemView);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        FormData item = formDataList.get(position);

        TextView textView = holder.itemRender;
        textView.setTextColor(Color.BLACK);
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        SimpleDateFormat converter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = converter.parse(item.getAddingDate().substring(0, 10));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        textView.setText(Html.fromHtml("Property Name: " + "<b>" + item.getPropertyType() + "</b>" + "<br>" +
                "Bed Room: "+ "<b>" + item.getBedRoom() + "</b>" + "<br>" +
                "Adding Date: " + "<b>" + sdf.format(date.getTime()) + "</b>" + "<br>" +
                "Monthly Rent Price: "+ "<b>" + item.getMonthlyRentPrice() + "</b>" + "<br>" +
                "Furniture Type: " + "<b>" + item.getFurnitureType() + "</b>" + "<br>" +
                "Notes: " + "<b>" + item.getNotes() + "</b>" + "<br>" +
                "Reporter Name: " + "<b>" + item.getPropertyType() + "</b>"));
    }

    @Override
    public int getItemCount() {
        return formDataList.size();
    }
}
