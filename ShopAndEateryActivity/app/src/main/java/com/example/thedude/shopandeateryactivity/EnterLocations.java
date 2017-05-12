package com.example.thedude.shopandeateryactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterLocations extends AppCompatActivity {

    public EditText editLocationText;
    public EditText editDistanceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_locations);

        editLocationText = (EditText) findViewById(R.id.txtbxlocation);
        editDistanceText = (EditText) findViewById(R.id.txtbxdistance);


        editLocationText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editLocationText.setText("");
            }
        });

        editDistanceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDistanceText.setText("");
            }
        });


        Button show = (Button) findViewById(R.id.submit);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MapsActivity.class);

                String loc =  editLocationText.getText().toString();
                String dis =  editDistanceText.getText().toString();


                i.putExtra("location",loc);
                i.putExtra("distance",dis);

                startActivity(i);
            }
        });

    }
}
