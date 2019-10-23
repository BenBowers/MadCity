package edu.curtin.madcity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Timer;
import java.util.TimerTask;

import static android.app.Activity.RESULT_OK;

/**
 * Class for the status bar UI displaying the current statistics of the game.
 * This is also responsible for detecting whether the game is over (i've
 * done this to avoid complexities with android's activities
 */
public class StatusBar extends Fragment
{

// CLASS CONSTANTS -----------------------------------------------------------

    public static final String TAG = "StatusBar";
    private final GameData GAME_DATA = GameData.getInstance(getContext());
    private static final int GAME_OVER = 1;


    /**
     * Timer to update the UI on this (running a seperate timer makes it
     * easy to update rather than adding it to the game data timer task.
     */
    private final Timer TIMER = new Timer();
    private final TimerTask TIMER_UI_UPDATE = new TimerTask()
    {
        @Override
        public void run()
        {
            Activity activity = getActivity();
            if (activity != null)
            {
                activity.runOnUiThread(StatusBar.this::update);
                GAME_DATA.increaseTime();
            }
        }
    };


// PRIVATE CLASS FIELDS ------------------------------------------------------

    private TextView mTimeTextView;
    private TextView mPopulationTextView;
    private TextView mEmploymentTextView;
    private TextView mMoneyTextView;
    private TextView mRateTextView;

// CONSTRUCTOR ---------------------------------------------------------------

    public StatusBar(){} // Empty constructor because android
    // Yes the app would actually crash if i don't have this

// OVERRIDE METHODS ----------------------------------------------------------

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreateView() called");

        // Inflate the layout
        View v = inflater.inflate(R.layout.status_bar, container, false);

        // Assign class fields from view
        mTimeTextView = v.findViewById(R.id.status_time);
        mMoneyTextView = v.findViewById(R.id.status_money);
        mRateTextView = v.findViewById(R.id.status_rate);
        mPopulationTextView = v.findViewById(R.id.status_population);
        mEmploymentTextView = v.findViewById(R.id.status_employment);

        // Set the timer to update the UI every second.
        TIMER.scheduleAtFixedRate(TIMER_UI_UPDATE, 0, GameData.GAME_INCREMENT);

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
        {
            if(requestCode == GAME_OVER)
            {
                Log.d(TAG, "GAME OVER");
                getActivity().finish();
            }
        }
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        TIMER.cancel(); // Stop the UI update call when there is no UI to
        // update
    }

// PRIVATE METHODS -----------------------------------------------------------

    /**
     * Updates all the UI elements with the current data (also checks if
     * the game is over.
     */
    private void update()
    {
        int money = GAME_DATA.getMoney();
        final float earning =  GAME_DATA.getEarning();
        int colour;

        if (money < 0) // Game over condition
        {
            TIMER.cancel();
            GameOverDialog dialog = new GameOverDialog();
                    dialog.setTargetFragment(StatusBar.this, GAME_OVER);
                    dialog.setCancelable(false);
                    dialog.show(getFragmentManager(), "dialogNumber");
        }

        // Set the rate text and colour
        mRateTextView.setText(getString(R.string.status_rate, earning));
        colour = getEarningColour(earning);
        mRateTextView.setTextColor(colour);

        // Set money text
        mMoneyTextView.setText(getString(R.string.money_format,
                               GAME_DATA.getMoney()));

        // Set time text
        mTimeTextView.setText(GAME_DATA.getFormattedTime());

        // Set population text
        mPopulationTextView.setText(getString(R.string.pop_format,
                                              GAME_DATA.getPopulation()));
        //Set employment text
        mEmploymentTextView.setText(
                getString(R.string.emp_format,
                          GAME_DATA.getEmployment() * 100));
    }

    /**
     * Gets a colour based on the current earning
     * @param earning current earning
     * @return colour int
     */
    private int getEarningColour(final float earning)
    {
        int colour;
        final Resources res = getResources();

        if(earning > 0)
        {
            colour =  res.getColor(R.color.plus_income, null);
        }
        else if (earning < 0)
        {
            colour = res.getColor(R.color.minus_income, null);
        }
        else
        {
            colour = res.getColor(R.color.zero_income, null);
        }

        return colour;
    }

}// StatusBar
