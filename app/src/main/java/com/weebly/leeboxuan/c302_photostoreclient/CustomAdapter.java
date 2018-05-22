package com.weebly.leeboxuan.c302_photostoreclient;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Category> {
    Context context;
    ArrayList<Category> categories;
    int resource;
   public CustomAdapter(Context context, int resource, ArrayList<Category> categories){
       super(context, resource, categories);
       this.context = context;
       this.categories = categories;
       this.resource = resource;

   }

   @Override
    public View getView(int position, View convertView, ViewGroup parent){
       LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
       View view = inflater.inflate(R.layout.row, null);

       TextView tvCategory = (TextView) view.findViewById(R.id.tvCategory);
       TextView tvDescription = (TextView) view.findViewById(R.id.tvDescription);

       Category cat = categories.get(position);

       tvCategory.setText(cat.getCat_name());
       tvDescription.setText(cat.getCat_description());


       

       return view;

   }
}
