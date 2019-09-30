package edu.curtin.madcity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import edu.curtin.madcity.settings.IntSetting;

import static android.app.Activity.RESULT_OK;

public class NumberPickerFragment extends DialogFragment
{
    private static final String ARG_SETTING = "setting";

    public static final String VALUE_EXTRA =
            "edu.curtin.madcity.NumberPickerFragment.value";
    public static final String LOC_EXTRA =
            "edu.curtin.madcity.NumberPickerFragment.loc";


    private NumberPicker mNumberPicker;
    private IntSetting mSetting;

    public static NumberPickerFragment newInstance(IntSetting setting)
    {
        Bundle args = new Bundle();
        args.putSerializable(ARG_SETTING, setting);


        NumberPickerFragment fragment = new NumberPickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private NumberPickerFragment() {}

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {

        mSetting = (IntSetting) getArguments().getSerializable(ARG_SETTING);



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

    private void okClicked(DialogInterface dialog, int which)
    {
        mSetting.setValue(mNumberPicker.getValue());
        getTargetFragment().onActivityResult(getTargetRequestCode(),
                                             RESULT_OK, null);
    }


}
