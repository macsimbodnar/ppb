package com.mazerfaker.pewpewboom.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
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

        _gameLayout = (ConstraintLayout) findViewById(R.id.game_container);

        init();
    }


    /**
     * Have to init here because need game_layout size before initSurface
     */
    private void init() {

        ViewTreeObserver vto = _gameLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener (new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                _gameLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                //Log.d(TAG, "SET SIZES W = " + width + "  H = " + height);
                _app = App.getInstance();
                Log.d(TAG, _app.toString());
                _app.setWindowSize(_gameLayout.getMeasuredWidth(), _gameLayout.getMeasuredHeight());

                initSurface();

                setListeners();

            }
        });
    }


    private void initSurface() {

        _surface = new Surface(getApplicationContext());
        _gameLayout.addView(_surface);
    }


    private void setListeners() {

        LinearLayout left = (LinearLayout) findViewById(R.id.game_left);
        LinearLayout center = (LinearLayout) findViewById(R.id.game_center);
        LinearLayout right = (LinearLayout) findViewById(R.id.game_right);


        left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    //Log.d(TAG, "LEFT DOWN");
                    _app.moveLeft(true);
                }

                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    //Log.d(TAG, "LEFT UP");
                    _app.moveLeft(false);
                }

                return true;
            }
        });


        center.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    //Log.d(TAG, "CENTER DOWN");
                    _app.fire(true);
                }

                //if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    //Log.d(TAG, "CENTER UP");
                //}

                return true;
            }
        });


        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    //Log.d(TAG, "RIGHT DOWN");
                    _app.moveRight(true);
                }

                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    //Log.d(TAG, "RIGHT UP");
                    _app.moveRight(false);
                }

                return true;
            }
        });
    }


    private ConstraintLayout _gameLayout;
    private Surface _surface;
    private App _app;
}
