package com.example.multiselectspinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MultiSelectionSpinner multiSelectionSpinner;
    List<String> lstFacilities;
    Button btnFacilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        multiSelectionSpinner = findViewById(R.id.multiSelectionSpinner);
        lstFacilities = new ArrayList<String>();
        getFacilities();

        btnFacilities = findViewById(R.id.btnFacilities);

        //Convert the selected spinner options to Array of String
        btnFacilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selected = multiSelectionSpinner.getSelectedItemsAsString();
                List<String> lst = Arrays.asList(selected.trim().split("\\s*,\\s*")); //To remove space from start and end of comma
                System.out.println(lst);
            }
        });
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