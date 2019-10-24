package edu.curtin.madcity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import static android.app.Activity.RESULT_OK;

/**
 * Dialog shown when the game is over prompting the user to return to the
 * title screen
 */
public class GameOverDialog extends DialogFragment
{
    private static final String TAG = "GameOverDialog";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreateDialog() called");
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        Button returnButton;
        View v = inflater.inflate(R.layout.game_over, null);
        returnButton = v.findViewById(R.id.game_over_button);
        returnButton.setOnClickListener(this::returnOnClick);
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .create();
    }

    /**
     * Closes the dialog with RESULT_OK
     * @param v
     */
    private void returnOnClick(View v)
    {
        Log.d(TAG, "return Clicked");
        Fragment target = getTargetFragment();

        if (target != null)
        {
            GameData.getInstance(getContext()).newGame();
            Intent intent = new Intent();
            target.onActivityResult(getTargetRequestCode(), RESULT_OK,
                                    intent);
            getDialog().dismiss();
        }
    }
}
