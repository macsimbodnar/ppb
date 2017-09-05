package com.mazerfaker.pewpewboom.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.mazerfaker.pewpewboom.R;
import com.mazerfaker.pewpewboom.view.Surface;

public class GameActivity extends Activity {

    private static final String TAG = "GameActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initSurface();

        setListeners();


    }


    private void initSurface() {

        LinearLayout gameView = (LinearLayout) findViewById(R.id.game_layout);
        _surface = new Surface(getApplicationContext());
        gameView.addView(_surface);
    }


    private void setListeners() {

        LinearLayout left = (LinearLayout) findViewById(R.id.game_left);
        LinearLayout center = (LinearLayout) findViewById(R.id.game_center);
        LinearLayout right = (LinearLayout) findViewById(R.id.game_right);


        // TODO left listener
        left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.d(TAG, "LEFT DOWN");
                }

                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    Log.d(TAG, "LEFT UP");
                }

                return true;
            }
        });


        // TODO center listener
        center.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.d(TAG, "CENTER DOWN");
                }

                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    Log.d(TAG, "CENTER UP");
                }

                return true;
            }
        });


        // TODO right listener
        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.d(TAG, "RIGHT DOWN");
                }

                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    Log.d(TAG, "RIGHT UP");
                }

                return true;
            }
        });
    }


    Surface _surface;
}
