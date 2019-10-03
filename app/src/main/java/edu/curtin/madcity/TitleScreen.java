package edu.curtin.madcity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TitleScreen extends AppCompatActivity
{
// CLASS CONSTANTS -----------------------------------------------------------

    public static final String TAG = "TitleScreen";

// PRIVATE CLASS FIELDS ------------------------------------------------------

    private Button mResumeGameButton;
    private Button mNewGameButton;
    private Button mSettingsButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_screen);
        mNewGameButton = findViewById(R.id.start_button);
        mSettingsButton = findViewById(R.id.settings_button);

        mNewGameButton.setOnClickListener(this::newGameOnClick);
        mSettingsButton.setOnClickListener(this::settingsOnClick);

    }

    private void newGameOnClick(View v)
    {
        startActivity(MapActivity.newIntent(TitleScreen.this));
    }

    private void settingsOnClick(View v)
    {
        startActivity(SettingsActivity.newIntent(TitleScreen.this));
    }

}//TitleScreen.class
