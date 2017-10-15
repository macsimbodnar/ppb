package com.mazerfaker.pewpewboom.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mazerfaker.pewpewboom.R;
import com.mazerfaker.pewpewboom.controller.Surface;
import com.mazerfaker.pewpewboom.controller.App;
import com.mazerfaker.pewpewboom.util.Constants;

import java.util.ArrayList;

public class GameActivity extends Activity {

    private static final String TAG = "GameActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initViewElements();

        initApp();

        asyncInitSurface();

        setListeners();
    }


    @Override
    public void onRestart() {
        super.onRestart();
        // TODO
    }


    @Override
    public void onStart() {
        super.onStart();
        // TODO
    }


    @Override
    public void onResume() {
        super.onResume();
        hideNavbar();

        if(_surface != null) {
            _surface.resume();
        }
    }


    @Override
    public void onPause() {
        // TODO
        super.onPause();
    }


    @Override
    public void onBackPressed() {
        _surface.stop();
        super.onBackPressed();
    }


    @Override
    public void onStop() {
        // TODO stop music here
        super.onStop();
    }


    @Override
    public void onDestroy() {
        // TODO
        super.onDestroy();
    }


    private void initViewElements() {
        _gameLayout = (ConstraintLayout) findViewById(R.id.canvas_container);
        _fire = (ConstraintLayout) findViewById(R.id.bottom_bar);
        _pauseButton = (ImageButton) findViewById(R.id.pause_button);

        _pauseMenuContainer = (ConstraintLayout) findViewById(R.id.pause_menu_container);
        _pauseMenuContainer.setVisibility(View.GONE);

        _continueButton = (Button) findViewById(R.id.continue_button);
        _returnButton = (Button) findViewById(R.id.return_button);

        _scoreView = (TextView) findViewById(R.id.score_view);
    }


    private void initApp() {

        _app = App.getInstance();
        _app.setFireButton(_fire);
        _app.setScoreView(_scoreView);

        boolean continueGame = getIntent().getBooleanExtra(Constants.CONTINUE_GAME, false);
        if(!continueGame) {
            _app.reset();
        }
    }


    /**
     * Have to init here because need game_layout size before initSurface
     */
    private void asyncInitSurface() {

        ViewTreeObserver vto = _gameLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener (new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                _gameLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                //Log.d(TAG, "SET SIZES W = " + width + "  H = " + height);
                _app.setWindowSize(_gameLayout.getMeasuredWidth(), _gameLayout.getMeasuredHeight());

                initSurface();
            }
        });
    }


    private void initSurface() {

        _surface = new Surface(getApplicationContext());

        ArrayList<View> a = new ArrayList<>();
        a.add(_pauseButton);
        _surface.addTouchables(a);

        _gameLayout.addView(_surface);
    }


    private void setListeners() {

        LinearLayout left = (LinearLayout) findViewById(R.id.game_left);
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


        _fire.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    //Log.d(TAG, "_fire DOWN");
                    _app.fire();
                }

                //if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    //Log.d(TAG, "_fire UP");
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
                _surface.pause();
                _pauseButton.setVisibility(View.GONE);
                _pauseMenuContainer.setVisibility(View.VISIBLE);
            }
        });


        _continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _pauseMenuContainer.setVisibility(View.GONE);
                _pauseButton.setVisibility(View.VISIBLE);
                _surface.resume();
            }
        });


        _returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _surface.stop();
                GameActivity.this.finish();
            }
        });
    }


    private void hideNavbar() {
        _gameLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }


    private TextView _scoreView;
    private ConstraintLayout _gameLayout;
    private ImageButton _pauseButton;
    private Button _continueButton;
    private Button _returnButton;
    private ConstraintLayout _pauseMenuContainer;
    private Surface _surface;
    private ConstraintLayout _fire;
    private App _app;
}
