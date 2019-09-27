package edu.curtin.madcity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


public class SettingsMenu extends Fragment
{
    private static final int[] SETTING_NAMES = {
        R.string.settings_map_width,
        R.string.settings_map_height,
        R.string.settings_initial_money,
        R.string.settings_family_size,
        R.string.settings_shop_size,
        R.string.settings_salary,
        R.string.settings_tax_rate,
        R.string.settings_service_cost,
        R.string.settings_house_buildingCost,
        R.string.settings_comm_buildingCost
    };




    public RecyclerView mRecyclerView;
    public Settings mSettings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private class SettingsAdaptor extends RecyclerView.Adapter<SettingsViewHolder>
    {
        @NonNull
        @Override
        public SettingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull SettingsViewHolder holder, int position)
        {

        }

        @Override
        public int getItemCount()
        {
            return 0;
        }
    }

    private class SettingsViewHolder extends  RecyclerView.ViewHolder
    {
        public SettingsViewHolder(@NonNull View itemView)
        {
            super(itemView);
        }
    }

    private String getString(short ii)
    {

        if (ii > SETTING_NAMES.length || ii < 0 )
        {
            throw new IllegalArgumentException(
                    "index is not within the array");
        }

        return getString(SETTING_NAMES[ii]);
    }
}
