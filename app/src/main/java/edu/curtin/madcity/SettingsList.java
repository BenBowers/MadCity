package edu.curtin.madcity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.curtin.madcity.settings.IntSetting;
import edu.curtin.madcity.settings.Setting;
import edu.curtin.madcity.settings.Settings;

import static android.app.Activity.RESULT_OK;


public class SettingsList extends Fragment
{
// CLASS CONSTANTS -----------------------------------------------------------
    private static final String TAG  = "SettingsList";
    private static final String DIALOG_NUMBER = "DialogNumber";
    private static final int REQUEST_INT = 0;

    private RecyclerView mRecyclerView;
    private SettingsAdaptor mAdaptor = new SettingsAdaptor();
    private Settings mSettings = new Settings(); //TODO: pass gameinstances
    // settings

// OVERRIDE METHODS ----------------------------------------------------------

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreateView() called");
        View view = inflater.inflate(R.layout.settings_menu, container,
                                     false);
        mRecyclerView =
                view.findViewById(R.id.settings_recyclerview);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdaptor);
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(mRecyclerView.getContext(),
                                          DividerItemDecoration.VERTICAL));
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK)
        {
            switch (requestCode)
            {
                case REQUEST_INT :
                    updateUI();
                    break;
            }
        }

    }

// PRIVATE METHODS -----------------------------------------------------------

    private void updateUI()
    {
        mAdaptor.notifyDataSetChanged();
    }



// PRIVATE CLASSES -----------------------------------------------------------

    private class SettingsAdaptor extends
            RecyclerView.Adapter<SettingsViewHolder>
    {
        @NonNull
        @Override
        public SettingsViewHolder onCreateViewHolder(
                @NonNull ViewGroup parent, int viewType)
        {
            Log.d(TAG, "onCreateViewHolder() called");
            LayoutInflater layoutInflater =
                    LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.setting,
                                               parent, false);
            return new SettingsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SettingsViewHolder holder,
                                     int position)
        {
            Log.d(TAG, "onBindViewHolder() called");
            holder.bindSetting(mSettings.getSetting(position));
        }

        @Override
        public int getItemCount()
        {
            Log.d(TAG, "getItemCount() called");
            return mSettings.getSize();
        }
    }

    private class SettingsViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        Setting mSetting;
        TextView mNameTextView;
        TextView mValueTextView;

        public SettingsViewHolder(@NonNull View itemView)
        {
            super(itemView);
            mNameTextView =
                    itemView.findViewById(R.id.setting_name);
            mValueTextView =
                    itemView.findViewById(R.id.setting_value);
            itemView.setOnClickListener(this);
        }

        public void bindSetting(Setting setting)
        {
            mSetting = setting;
            mNameTextView.setText(mSetting.getNameID());
            mValueTextView.setText(mSetting.getStringValue());

        }

        @Override
        public void onClick(View view)
        {
            Log.d(TAG,
                  "Setting " + getString(mSetting.getNameID())
                          + " onCLick() called.");

            if (mSetting instanceof IntSetting)
            {
                IntSetting setting = (IntSetting) mSetting;
                FragmentManager fragmentManager = getFragmentManager();
                NumberPickerFragment dialog =
                        NumberPickerFragment.newInstance(setting);
                dialog.setTargetFragment(SettingsList.this, REQUEST_INT);
                dialog.show(fragmentManager, DIALOG_NUMBER);
            }

        }
    }

}
