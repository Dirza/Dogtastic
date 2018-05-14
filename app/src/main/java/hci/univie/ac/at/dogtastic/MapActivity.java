package hci.univie.ac.at.dogtastic;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;


/**
 * Created by Jasmin on 13.05.2018.
 */


public class MapActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {
    private GoogleMap mMap;
    private Button startbutton;
    private Button pauseButton;
    TextView timer;

    private Handler mHandler;
    long start_time;


    private static final int LOCATION_REQUEST = 500;

    private long pauseOffset;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        startbutton = findViewById(R.id.start_button);
        startbutton.setOnClickListener(this);
        pauseButton = findViewById(R.id.pause_button);
        timer = (TextView)findViewById(R.id.timer);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mHandler = new Handler();

    }


    /**v
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    @Override
    public void onClick(View view) {
        if(startbutton.getText().toString().equals("START")){
            startbutton.setText("STOP");
            startbutton.setBackgroundColor(Color.RED);
            startTimer();;
        } else if(startbutton.getText().toString().equals("STOP")){
            startbutton.setBackgroundColor(Color.GRAY);
            startbutton.setText("SPEICHERN");
            pauseButton.setText("SCHLIESSEN");
            mHandler.removeCallbacks(mRunnable);
        }  else if(startbutton.getText().toString().equals("SPEICHERN")){
            Intent save = new Intent(this, SaveActivity.class);
            startActivity(save);
        }
    }

    public void startTimer() {
        running = true;
        mHandler.postDelayed(mRunnable, 10L);
        start_time = System.currentTimeMillis();
    }

    public void pauseRoute(View v) {
        if(pauseButton.getText().toString().equals("PAUSE")){
            pauseButton.setText("FORTSETZEN");
            running=false;
            mHandler.removeCallbacks(mRunnable);
        } else if(pauseButton.getText().toString().equals("FORTSETZEN")){
            pauseButton.setText("PAUSE");
            running = true;
            mRunnable.run();
        } else if(pauseButton.getText().toString().equals("SCHLIESSEN")) {
            Intent overview = new Intent(this, RoutesOverview.class);
            startActivity(overview);
        }
    }

    private final Runnable mRunnable = new Runnable(){
        @Override
        public void run() {
            if(running){
                long millis = (System.currentTimeMillis() - start_time);
                long seconds = millis/1000;
                timer.setText(String.format("%02d:%02d:%02d", seconds/60, seconds % 60, millis % 100));
                mHandler.postDelayed(mRunnable,10L);
            }
        }
    };
}