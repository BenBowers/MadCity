package edu.curtin.madcity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


public class StatusBar extends Fragment
{

// CLASS CONSTANTS -----------------------------------------------------------
    public static final String TAG = "StatusBar";
    public static final GameData GAME_DATA = GameData.getInstance();


    private final Timer mTimer = new Timer();
    private final TimerTask mTimerTask = new TimerTask()
    {
        @Override
        public void run()
        {
            Activity activity = getActivity();
            if (activity != null)
            {
                activity.runOnUiThread(StatusBar.this::update);
            }
        }
    };





// PRIVATE CLASS FIELDS ------------------------------------------------------

    private TextView mTimeTextView;
    private TextView mPopulationTextView;
    private TextView mEmploymentTextView;
    private TextView mMoneyTextView;
    private TextView mRateTextView;

// PUBLIC METHODS ------------------------------------------------------------

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);
    }

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



        mTimer.scheduleAtFixedRate(mTimerTask, 0, 1000);

        return v;
    }


    public void update()
    {
        /*


        mRateTextView.setText(rate);
        mPopulationTextView.setText(GAME_DATA.getPopulation());
        mEmploymentTextView.setText(GAME_DATA.getEmployment());
        */
        mMoneyTextView.setText(String.format(Locale.UK,"$%d",
                                             GAME_DATA.getMoney()));
        mTimeTextView.setText(String.format(Locale.UK,"%d",
                                            GAME_DATA.getGameTime()));
    }

}// StatusBar
