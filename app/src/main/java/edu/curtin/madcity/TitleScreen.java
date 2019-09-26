package edu.curtin.madcity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TitleScreen extends AppCompatActivity
{
// CLASS CONSTANTS -----------------------------------------------------------

    public static final String TAG = "TitleScreen";

// PRIVATE CLASS FIELDS ------------------------------------------------------
    private Button mStartButton;
    private Button mSettingsButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate() called.");
        super.onCreate(savedInstanceState);

        // Get references of the buttons
        mStartButton = findViewById(R.id.start_button);
        mSettingsButton = findViewById(R.id.settings_button);

        // Set listeners for buttons

        mStartButton.setOnClickListener((View v) -> startButtonOnClick());

        mSettingsButton.setOnClickListener(
                (View v) -> settingsButtonOnClick());

    }

// PRIVATE METHODS -----------------------------------------------------------

    private void startButtonOnClick()
    {
        Log.d(TAG, "startButtonOnClick() called");
        // TODO: launch activity
    }

    private void settingsButtonOnClick()
    {
        Log.d(TAG, "settingsButtonOnClick() called");
        //TODO : launch activity
    }

}
