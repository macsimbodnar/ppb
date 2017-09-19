package com.mazerfaker.pewpewboom.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.mazerfaker.pewpewboom.R;
import com.mazerfaker.pewpewboom.util.Constants;

public class MainMenuActivity extends Activity {

    private static final String TAG = "MainMenuActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        _mainView = findViewById(R.id.main_layout);

        Button startGame = (Button) findViewById(R.id.mainMenu_startButton);

        _continueButton = (Button) findViewById(R.id.mainMenu_continueButton);
        _continueButton.setVisibility(View.GONE);


        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gameIntent = new Intent(MainMenuActivity.this, GameActivity.class);
                gameIntent.putExtra(Constants.CONTINUE_GAME, false);
                startActivity(gameIntent);

            }
        });

        _continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameIntent = new Intent(MainMenuActivity.this, GameActivity.class);
                gameIntent.putExtra(Constants.CONTINUE_GAME, true);
                startActivity(gameIntent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        hideNavbar();
    }


    @Override // WHEN was in background and come back
    protected void onRestart() {
        super.onRestart();
        // TODO forse non è il posto giusto questo per metterlo a visibile (da verificare se quando ritorno da background scatta)
        _continueButton.setVisibility(View.VISIBLE);
    }

    private void hideNavbar() {
        _mainView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LOW_PROFILE |
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }


    private View _mainView;
    private Button _continueButton;
}
