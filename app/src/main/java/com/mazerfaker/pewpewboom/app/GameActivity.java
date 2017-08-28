package com.mazerfaker.pewpewboom.app;

import android.app.Activity;
import android.os.Bundle;

import com.mazerfaker.pewpewboom.R;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }
}
