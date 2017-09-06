package com.mazerfaker.pewpewboom.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mazerfaker.pewpewboom.R;

public class MainMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        _mainView = findViewById(R.id.main_layout);

        Button startGame = (Button) findViewById(R.id.mainMenu_startButton);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameIntent = new Intent(MainMenuActivity.this, GameActivity.class);
                startActivity(gameIntent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        hideNavbar();
    }


    private void hideNavbar() {
        _mainView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }


    private View _mainView;
}
