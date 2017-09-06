package com.mazerfaker.pewpewboom.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.mazerfaker.pewpewboom.R;
import com.mazerfaker.pewpewboom.controller.Surface;
import com.mazerfaker.pewpewboom.controller.App;

public class GameActivity extends Activity {

    private static final String TAG = "GameActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        _gameLayout = (ConstraintLayout) findViewById(R.id.game_container);

        init();
    }


    @Override
    protected void onResume() {
        super.onResume();

        hideNavbar();
    }


    private void hideNavbar() {
        _gameLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }


    /*@Override
    public void onBackPressed() {
        pausaButton.setVisibility(View.GONE);
        PauseMenu.setVisibility(View.VISIBLE);
        game_panel.Pause_game=true;
    }

    @Override
    protected void onStop() {
        if (MainMusic.isPlaying())
            MainMusic.stop();
        super.onStop();
    }*/


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

                _app.setWindowSize(_gameLayout.getMeasuredWidth(), _gameLayout.getMeasuredHeight());

                initSurface();

                setListeners();

            }
        });
    }


    private void initSurface() {

        _surface = new Surface(getApplicationContext());
        _gameLayout.addView(_surface);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    private void setListeners() {

        RelativeLayout left = (RelativeLayout) findViewById(R.id.game_left);
        RelativeLayout center = (RelativeLayout) findViewById(R.id.game_center);
        RelativeLayout right = (RelativeLayout) findViewById(R.id.game_right);

        _pauseButton = (ImageButton) findViewById(R.id.pause_button);


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
                    _app.fire();
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


        _pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _pauseButton.setVisibility(View.GONE);
                //PauseMenu.setVisibility(View.VISIBLE);
                _surface.pause(true);
            }
        });
    }


    private ConstraintLayout _gameLayout;
    private ImageButton _pauseButton;
    private Surface _surface;
    private App _app;
}
