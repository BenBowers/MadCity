package edu.curtin.madcity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.curtin.madcity.settings.IntSetting;
import edu.curtin.madcity.settings.NumberPickerFragment;
import edu.curtin.madcity.settings.Setting;
import edu.curtin.madcity.settings.Settings;

import static android.app.Activity.RESULT_OK;

/**
 * Fragment containing a list of settings for the user to edit
 */
public class SettingsList extends Fragment
{
// CLASS CONSTANTS -----------------------------------------------------------
    private static final String TAG  = "SettingsList";
    private static final String DIALOG_NUMBER = "DialogNumber";
    private static final int REQUEST_INT = 0;
    private final Settings SETTINGS = GameData.getInstance(getContext())
            .settings;

    private RecyclerView mRecyclerView;
    private SettingsAdaptor mAdaptor = new SettingsAdaptor();
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
    public void onCreateOptionsMenu(@NonNull Menu menu,
                                    @NonNull MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.setting_menu, menu);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(
                R.string.settings_button);
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        boolean bool;

        if (item.getItemId() == R.id.setting_menu_default)
        {
            defaultOnClick();
            bool = true;
        }
        else
        {
            bool = super.onOptionsItemSelected(item);
        }

        return bool;
    }

    // PRIVATE METHODS -----------------------------------------------------------

    private void updateUI()
    {
        mAdaptor.notifyDataSetChanged();
    }

    private void defaultOnClick()
    {
        SETTINGS.setDefault();
        Toast.makeText(getContext(), R.string.default_settings_toast,
                       Toast.LENGTH_SHORT).show();
        updateUI();
        SETTINGS.updateDb(getContext());
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
        }// SettingsViewHolder()

        @Override
        public void onBindViewHolder(@NonNull SettingsViewHolder holder,
                                     int position)
        {
            Log.d(TAG, "onBindViewHolder() called");
            holder.bindSetting(SETTINGS.getSetting(position));
        }// onBindViewHolder()

        @Override
        public int getItemCount()
        {
            Log.d(TAG, "getItemCount() called");
            return SETTINGS.getSize();
        }// getItemCount()
    }// SettingsAdaptor.class

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
        }// SettingsViewHolder()

        public void bindSetting(Setting setting)
        {
            mSetting = setting;
            mNameTextView.setText(mSetting.getNameID());
            mValueTextView.setText(mSetting.getStringValue());
        }// bindSetting()

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
                if( fragmentManager != null)
                {
                    NumberPickerFragment dialog =
                            NumberPickerFragment.newInstance(setting);
                    dialog.setTargetFragment(SettingsList.this,
                                             REQUEST_INT);
                    dialog.show(fragmentManager, DIALOG_NUMBER);
                }
            }
        }// onClick()
    }// SettingsViewHolder.class
}// SettingsList.class
