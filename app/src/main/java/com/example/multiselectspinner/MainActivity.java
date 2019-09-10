package com.example.multiselectspinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MultiSelectionSpinner multiSelectionSpinner;
    List<String> lstFacilities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        multiSelectionSpinner = findViewById(R.id.multiSelectionSpinner);
        lstFacilities = new ArrayList<String>();
        getFacilities();
    }

    private void getFacilities() {
        try{
            JSONObject fac = new JSONObject(OpenJSON.readJSONFromAsset(MainActivity.this, "facilities.json"));
            JSONArray facilities = fac.getJSONArray("facilities");
            for (int i=0; i < facilities.length(); i++){
                String facility = facilities.getJSONObject(i).getString("f_name");
                lstFacilities.add(facility);
            }

            multiSelectionSpinner.setItems(lstFacilities);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
