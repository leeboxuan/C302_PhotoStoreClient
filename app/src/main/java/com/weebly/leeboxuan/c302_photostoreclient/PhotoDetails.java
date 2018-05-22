package com.weebly.leeboxuan.c302_photostoreclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class PhotoDetails extends AppCompatActivity {
     ListView lvDetails;
     CustomDetailsAdapter aaDetailsAdapter;
     ArrayList<Details> alDetails = new ArrayList();
     int cat_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_details);






    }

    protected void onResume(){
        super.onResume();
        lvDetails = (ListView) findViewById(R.id.lvDetails);
        aaDetailsAdapter = new CustomDetailsAdapter(this, R.layout.row_details, alDetails);
        lvDetails.setAdapter(aaDetailsAdapter);

        Intent receive = getIntent();
        cat_id = receive.getIntExtra("cat_id", 0);

        //Code for step 1 start
        HttpRequest request = new HttpRequest
                ("https://codemusically.000webhostapp.com/C302_P06_PhotoStoreWS/getPhotoStoreByCategory.php?category_id=" + cat_id);

        request.setOnHttpResponseListener(mHttpResponseListener);
        request.setMethod("GET");
        request.execute();
        //Code for step 1 end

    }

    // Code for step 2 start
    private HttpRequest.OnHttpResponseListener mHttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response){
                    // process response here
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i=0; i<jsonArray.length(); i++){
                            JSONObject jsonObj = jsonArray.getJSONObject(i);

                            int photoid = jsonObj.getInt("photo_id");
                            String categoryName = jsonObj.getString("title");
                            String description = jsonObj.getString("description");
                            String image = jsonObj.getString("image");

                            int categoryid = jsonObj.getInt("category_id");
                            String createdby = jsonObj.getString("created_by");





                            Details detail = new Details(photoid, categoryName, description, image, categoryid, createdby);
                            alDetails.add(detail);
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    aaDetailsAdapter.notifyDataSetChanged();

                }
            };
    // Code for step 2 end

}
