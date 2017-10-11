package aayush.randompatrolling;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class addPlace extends AppCompatActivity {

    double latitude;
    double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);


        final EditText address = (EditText) findViewById(R.id.address);
        final EditText minTime = (EditText) findViewById(R.id.minTimeStay);
        final EditText maxTime = (EditText) findViewById(R.id.maxTimeStay);
        final EditText priority = (EditText) findViewById(R.id.placePriority);
        final EditText checkBackTime = (EditText) findViewById(R.id.checkBackDuration);
        Button addPlace = (Button) findViewById(R.id.addPlaceButton);

        Intent getPlaces = getIntent();
        if (getPlaces.getStringExtra("addressName") != null) {
            String addressname = getPlaces.getStringExtra("addressName");
            Log.d("addressName", addressname);
            address.setText(addressname);
        }

        if (getPlaces.getDoubleExtra("latitude", 0) != 0) {
            latitude = getPlaces.getDoubleExtra("latitude", 0);
            Log.d("latitude", String.valueOf(latitude));
        }
        if (getPlaces.getDoubleExtra("longitude", 0) != 0) {
            longitude = getPlaces.getDoubleExtra("longitude", 0);
            Log.d("longitude", String.valueOf(longitude));
        }

        addPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addressName = address.getText().toString();
                String minTimeStay = minTime.getText().toString();
                String maxTimeStay = maxTime.getText().toString();
                String priorityIndex = priority.getText().toString();
                String addPlaceCheckBackTime = checkBackTime.getText().toString();

                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                i.putExtra("address", addressName);
                i.putExtra("minTimeStay", minTimeStay);
                i.putExtra("maxTimeStay", maxTimeStay);
                i.putExtra("priorityIndex", priorityIndex);
                i.putExtra("addPlaceCheckBackTime", addPlaceCheckBackTime);
                i.putExtra("latitude", latitude);
                i.putExtra("longitude", longitude);
                startActivity(i);

            }
        });

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), mapSelect.class);
                startActivity(i);
            }

        });


    }
}
