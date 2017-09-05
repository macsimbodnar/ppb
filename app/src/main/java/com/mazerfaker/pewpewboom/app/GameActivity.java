package com.mazerfaker.pewpewboom.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.mazerfaker.pewpewboom.R;
import com.mazerfaker.pewpewboom.controller.Surface;
import com.mazerfaker.pewpewboom.model.App;

public class GameActivity extends Activity {

    private static final String TAG = "GameActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        init();
    }


    /**
     * Have to init here because need game_layout size before initSurface
     */
    private void init() {

        final LinearLayout gameLayout = (LinearLayout) findViewById(R.id.game_layout);
        ViewTreeObserver vto = gameLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener (new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                gameLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                //Log.d(TAG, "SET SIZES W = " + width + "  H = " + height);
                _app = App.getInstance();
                _app.setWindowSize(gameLayout.getMeasuredWidth(), gameLayout.getMeasuredHeight());

                setListeners();

                initSurface();
            }
        });
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
    App _app;
}
