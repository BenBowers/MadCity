package edu.curtin.madcity.settings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import edu.curtin.madcity.GameData;
import edu.curtin.madcity.R;

import static android.app.Activity.RESULT_OK;

/**
 * Dialog shown when the user clicks on an integer based setting
 */
public class NumberPickerFragment extends DialogFragment
{
// CLASS CONSTANTS -----------------------------------------------------------

    private static final String TAG = "NumberPickerFragment";
    private static final String ARG_SETTING = "setting";

// PRIVATE CLASS FIELDS ------------------------------------------------------

    private NumberPicker mNumberPicker;
    private IntSetting mSetting;

// CONSTRUCTOR ---------------------------------------------------------------

    public static NumberPickerFragment newInstance(IntSetting setting)
    {
        Bundle args = new Bundle();
        args.putSerializable(ARG_SETTING, setting);


        NumberPickerFragment fragment = new NumberPickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private NumberPickerFragment() {} // only be made with new Instance

// OVERRIDE METHODS ----------------------------------------------------------

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreateDialog() called");
        Bundle args = getArguments();
        if (args == null)
        {
            throw new IllegalStateException("Arguments are null");
        }

        mSetting = (IntSetting) args.getSerializable(ARG_SETTING);

        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.setting_dialog_picker, null);
        mNumberPicker = view.findViewById(R.id.number_picker);

        mNumberPicker.setMaxValue(mSetting.MAX);
        mNumberPicker.setMinValue(mSetting.MIN);
        mNumberPicker.setValue(mSetting.getValue());

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(mSetting.getNameID())
                .setPositiveButton(android.R.string.ok, this::okClicked)
                .create();
    }

// ON CLICK METHODS ----------------------------------------------------------

    private void okClicked(DialogInterface dialog, int which)
    {
        Log.d(TAG, "ok button clicked");
        mSetting.setValue(mNumberPicker.getValue());
        Fragment target = getTargetFragment();
        if (target != null)
        {
            target.onActivityResult(getTargetRequestCode(),
                                    RESULT_OK, null);
        }
        GameData.getInstance(getContext()).settings.updateDb(getContext());
    }
}
