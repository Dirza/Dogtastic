package hci.univie.ac.at.dogtastic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity implements View.OnClickListener{

    //comment naomi xxx yyy zzz
    //comment AGAIN
    Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        confirmButton = (Button)findViewById(R.id.spazieren);
        confirmButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent map = new Intent(this, RoutesOverview.class);
        startActivity(map);
    }
}
