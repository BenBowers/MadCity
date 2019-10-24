package edu.curtin.madcity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity for the title screen duh
 */
public class TitleScreen extends AppCompatActivity
{
// CLASS CONSTANTS -----------------------------------------------------------

    public static final String TAG = "TitleScreen";

// PRIVATE CLASS FIELDS ------------------------------------------------------

    private Button mNewGameButton;
    private Button mSettingsButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        GameData gameData = GameData.getInstance(getApplicationContext());

        setContentView(R.layout.title_screen);
        mNewGameButton = findViewById(R.id.start_button);
        mSettingsButton = findViewById(R.id.settings_button);

        mNewGameButton.setOnClickListener(this::newGameOnClick);
        mSettingsButton.setOnClickListener(this::settingsOnClick);

        if(gameData.mMap != null)
        {
            startActivity(MapActivity.newIntent(TitleScreen.this));
        }

    }

    private void newGameOnClick(View v)
    {
        GameData.getInstance(getApplicationContext()).newGame();
        startActivity(MapActivity.newIntent(TitleScreen.this));
    }

    private void settingsOnClick(View v)
    {
        GameData.getInstance(getApplicationContext()).newGame();
        startActivity(SettingsActivity.newIntent(TitleScreen.this));
    }

}//TitleScreen.class
