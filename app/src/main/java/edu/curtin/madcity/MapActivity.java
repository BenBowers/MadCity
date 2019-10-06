package edu.curtin.madcity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MapActivity extends AppCompatActivity
{
    private static final String TAG = "MapActivity";

    private Button mUpdateButton;
    private StatusBar mStatusBar;

    public static Intent newIntent(Context packageContext)
    {
        return new Intent(packageContext, MapActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate() called");


        MapGrid mapGrid;
        SelectorFragment selectorFragment;
        FragmentManager fm = getSupportFragmentManager();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mUpdateButton = findViewById(R.id.updateButton);
        mUpdateButton.setOnClickListener(this::update);

        mStatusBar = (StatusBar)fm.findFragmentById(R.id.map_status_bar);

        if (mStatusBar == null)
        {
            Log.d(TAG, "creating StatusBar fragment");

            mStatusBar = new StatusBar();
            fm.beginTransaction()
                    .add(R.id.map_status_bar, mStatusBar).commit();
        }

        selectorFragment =
                (SelectorFragment)fm.findFragmentById(R.id.map_selector);
        if(selectorFragment == null)
        {
            Log.d(TAG, "creating Selector fragment");
            selectorFragment = new SelectorFragment();
            fm.beginTransaction()
                    .add(R.id.map_selector, selectorFragment).commit();
        }

        mapGrid = (MapGrid)fm.findFragmentById(R.id.map_grid);

        if (mapGrid == null)
        {
            Log.d(TAG, "creating MapGrid fragment");

            mapGrid =  new MapGrid(selectorFragment);
            fm.beginTransaction().add(R.id.map_grid, mapGrid).commit();
        }


    }

    private void update(View v)
    {
        Log.d(TAG, "updating");
        mStatusBar.update(0);
    }
}
