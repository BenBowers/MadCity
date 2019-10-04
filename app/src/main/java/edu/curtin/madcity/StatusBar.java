package edu.curtin.madcity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StatusBar extends Fragment
{

// CLASS CONSTANTS -----------------------------------------------------------

    public static final String TAG = "StatusBar";
    public static final GameData GAME_DATA = GameData.getInstance();

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

        update(0); // Set values to the text views

        return v;
    }

    public void update(int rate)
    {
        /*
        mTimeTextView.setText(GAME_DATA.getGameTime());
        mMoneyTextView.setText(GAME_DATA.getMoney());
        mRateTextView.setText(rate);
        mPopulationTextView.setText(GAME_DATA.getPopulation());
        mEmploymentTextView.setText(GAME_DATA.getEmployment());
        */
    }

}// StatusBar
