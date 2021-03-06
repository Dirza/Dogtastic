package hci.univie.ac.at.dogtastic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jasmin on 13.05.2018.
 */

public class RoutesOverview extends FragmentActivity implements View.OnClickListener{

    private ListView lv;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);

        lv = (ListView) findViewById(R.id.listview);
        List<String> routen = new ArrayList<String>();
        routen.add("Route 1");
        routen.add("Route 2");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,routen
        );
        lv.setAdapter(arrayAdapter);

        confirmButton = findViewById(R.id.new_walk_button);
        confirmButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent map = new Intent(this, MapActivity.class);
        startActivity(map);
    }
}

