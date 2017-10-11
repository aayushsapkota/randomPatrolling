package aayush.randompatrolling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class places extends AppCompatActivity {

    MapsActivity map = new MapsActivity();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        TextView view = (TextView) findViewById(R.id.textView2);
        Button addPlace = (Button) findViewById(R.id.addPlaceButton);
        Button back = (Button) findViewById(R.id.placesBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);
            }
        });

        addPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), addPlace.class);
                startActivity(i);
            }
        });

        ArrayList<SelectedLocation> selectedLocationList = map.getSelectedLocationList();
        for(SelectedLocation s: selectedLocationList){
            
        }
        if (selectedLocationList.toString() != null || selectedLocationList.toString().equals("")) {
            view.setText(" ALl Selected Locations \n" +
                    selectedLocationList.toString());
            Log.d("Selection list",  selectedLocationList.toString() );
        } else {
            view.setText(" ALl Selected Locations \n No Data present yet");
        }

    }
}
