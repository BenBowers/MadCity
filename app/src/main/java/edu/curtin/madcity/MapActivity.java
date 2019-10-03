package edu.curtin.madcity;

import androidx.fragment.app.Fragment;

public class MapActivity extends SingleFragmentActivity
{
    @Override
    protected Fragment createFragment()
    {
        return new MapGrid();
    }
}
