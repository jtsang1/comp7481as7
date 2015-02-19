package com.example.jeremy.comp7481_as7;

import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements OnGesturePerformedListener {
    public final static String EXTRA_MESSAGE = "com.example.jeremy.comp7481_as7.MESSAGE";
    private GestureLibrary mLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!mLibrary.load()) {
            finish();
        }

        GestureOverlayView gestures = (GestureOverlayView) findViewById(R.id.gestures);
        gestures.addOnGesturePerformedListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture){
        ArrayList<Prediction> predictions = mLibrary.recognize(gesture);

        if (predictions.size() > 0 && predictions.get(0).score > 1.0) {
            String result = predictions.get(0).name;

            if ("oletter".equalsIgnoreCase(result)) {
                Toast.makeText(this, "O gesture", Toast.LENGTH_LONG).show();

                // Do something in response to button
                Intent intent = new Intent(this, DisplayWebViewActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "O");
                startActivity(intent);

            } else if ("jletter".equalsIgnoreCase(result)) {
                Toast.makeText(this, "J gesture", Toast.LENGTH_LONG).show();

                // Do something in response to button
                Intent intent = new Intent(this, DisplayWebViewActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "J");
                startActivity(intent);
            }
        }
    }
}
