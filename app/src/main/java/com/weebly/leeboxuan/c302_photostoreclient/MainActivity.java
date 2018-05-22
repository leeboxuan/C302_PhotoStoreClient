package com.weebly.leeboxuan.c302_photostoreclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ListView lvCategories;
    ArrayList<Category> alCategories;
    CustomAdapter aaCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    protected void onResume() {
        super.onResume();
        alCategories = new ArrayList<Category>();
        lvCategories = (ListView) findViewById(R.id.listViewCategories);
        aaCategories = new CustomAdapter(this, R.layout.row, alCategories);
        lvCategories.setAdapter(aaCategories);


        //Code for step 1 start
        HttpRequest request = new HttpRequest
                ("https://codemusically.000webhostapp.com/C302_P06_PhotoStoreWS/getCategories.php");

        request.setOnHttpResponseListener(mHttpResponseListener);
        request.setMethod("GET");
        request.execute();
        //Code for step 1 end

        lvCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int cat_id = alCategories.get(position).getCat_id();
                Intent i = new Intent(MainActivity.this, PhotoDetails.class);
                i.putExtra("cat_id", cat_id);
                startActivity(i);

            }
        });

    }

    // Code for step 2 start
    private HttpRequest.OnHttpResponseListener mHttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response) {

                    // process response here
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObj = jsonArray.getJSONObject(i);

                            int categoryId = jsonObj.getInt("category_id");
                            String categoryName = jsonObj.getString("name");
                            String description = jsonObj.getString("description");


                            Category newcat = new Category(categoryId, categoryName, description);
                            alCategories.add(newcat);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    aaCategories.notifyDataSetChanged();
                }
            };
    // Code for step 2 end


}
