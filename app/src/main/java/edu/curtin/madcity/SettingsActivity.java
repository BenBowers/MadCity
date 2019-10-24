package edu.curtin.madcity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

public class SettingsActivity extends SingleFragmentActivity
{
    private static final String TAG = "SettingsActivity";
    @Override
    protected Fragment createFragment()
    {
        return new SettingsList();
    }

    public static Intent newIntent(Context packageContext)
    {
        Intent intent = new Intent(packageContext, SettingsActivity.class);
        return intent;
    }

}
