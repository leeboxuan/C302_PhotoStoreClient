package com.weebly.leeboxuan.c302_photostoreclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomDetailsAdapter extends ArrayAdapter {
    private ArrayList<Details> details;
    private Context context;
    private TextView tvName, tvDesc;

    public CustomDetailsAdapter(Context context, int resource, ArrayList<Details> objects) {
        super(context, resource, objects);
        details = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_details, parent, false);
        tvName = rowView.findViewById(R.id.tvTitle);
        tvDesc = rowView.findViewById(R.id.tvPhotoDescription);
        Details currDetail = details.get(position);
        tvName.setText(currDetail.getTitle());
        String desc = currDetail.getDescription() + "\nCreated By: " + currDetail.getCreated_by() + "\n" + currDetail.getImage();
        tvDesc.setText(desc);

        return rowView;
    }
}