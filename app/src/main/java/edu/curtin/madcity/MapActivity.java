package edu.curtin.madcity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import edu.curtin.madcity.selector.SelectorFragment;

public class MapActivity extends AppCompatActivity
{
    private static final String TAG = "MapActivity";

    StatusBar mStatusBar;
    MapGrid mMapGrid;
    SelectorFragment mSelectorFragment;

    public static Intent newIntent(Context packageContext)
    {
        return new Intent(packageContext, MapActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate() called");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        FragmentManager fm = getSupportFragmentManager();


        mStatusBar = (StatusBar)fm.findFragmentById(R.id.map_status_bar);

        if (mStatusBar == null)
        {
            Log.d(TAG, "creating StatusBar fragment");

            mStatusBar = new StatusBar();
            fm.beginTransaction()
                    .add(R.id.map_status_bar, mStatusBar).commit();
        }

        mMapGrid = (MapGrid)fm.findFragmentById(R.id.map_grid);

        if (mMapGrid == null)
        {
            Log.d(TAG, "creating MapGrid fragment");

            mMapGrid =  new MapGrid();
            fm.beginTransaction().add(R.id.map_grid, mMapGrid).commit();
        }

        mSelectorFragment  =
                (SelectorFragment)fm.findFragmentById(R.id.map_selector);
        if(mSelectorFragment == null)
        {
            Log.d(TAG, "creating Selector fragment");
            mSelectorFragment = new SelectorFragment();
            fm.beginTransaction()
                    .add(R.id.map_selector, mSelectorFragment).commit();
        }


    }
}
