package edu.curtin.madcity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;



public class MapActivity extends SingleFragmentActivity
{
    @Override
    protected Fragment createFragment()
    {
        return new MapGrid();
    }

    public static Intent newIntent(Context packageContext)
    {
        Intent intent = new Intent(packageContext, MapActivity.class);
        return intent;
    }
}
