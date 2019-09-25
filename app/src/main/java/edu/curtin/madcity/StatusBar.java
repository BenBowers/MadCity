package edu.curtin.madcity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StatusBar extends Fragment
{
    final GameData mGameData = GameData.getInstance();

    TextView mTimeTextView;
    TextView mPopulationTextView;
    TextView mEmploymentTextView;
    TextView mMoneyTextView;
    TextView mRateTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.status_bar, container);

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
        mTimeTextView.setText(mGameData.getGameTime());
        mMoneyTextView.setText(mGameData.getMoney());
        mRateTextView.setText(rate);
        mPopulationTextView.setText(mGameData.getPopulation());
        mEmploymentTextView.setText(mGameData.getEmployment());
    }
}
