package edu.curtin.madcity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.Timer;
import java.util.TimerTask;

import edu.curtin.madcity.database.DbHelper;
import edu.curtin.madcity.settings.Settings;

public class GameData
{
    private class Task extends TimerTask
    {

        @Override
        public void run()
        {
            mGameTime++;
        }
    }

    private static final GameData ourInstance = new GameData();

    public static GameData getInstance()
    {
        return ourInstance;
    }

    SQLiteDatabase db;
    Timer TIMER = new Timer();
    Task mTask = new Task();

    private StatusBar mStatusBar;


    Settings SETTINGS = new Settings();
    MapElement[][] mMap;

    private int mMoney = Settings.INITIAL_MONEY.getValue();
    int mGameTime = 10;
    int population = 100;

    private GameData()
    {
        TIMER.schedule(mTask, 0, 1000);
    }

    private void load(Context context)
    {
        this.db =
                new DbHelper(context.getApplicationContext()).getWritableDatabase();

    }

    public void newGame()
    {
        mMap =
                new MapElement[Settings.MAP_WIDTH.getValue()][Settings.MAP_HEIGHT.getValue()];
    }

    public int getMoney()
    {
        return mMoney;
    }

    public int getGameTime()
    {
        return mGameTime;
    }

    public int getPopulation()
    {
        return population;
    }

    public int getEmployment()
    {
        return 0; //TODO: write employment calculation
    }

    public void increaseTime()
    {
        mGameTime++;
    }

    public void setStatusBar(StatusBar statusBar)
    {
        mStatusBar = statusBar;
    }

    public String getFormattedTime()
    {
        String out;
        int seconds = mGameTime % 60;
        int minutes = mGameTime / 60;
        int hours = minutes / 60;

        if(hours == 0)
        {
            if(minutes == 0)
            {
                out = String.format("%02d", seconds);
            }
            else
            {
                out = String.format("%02d:%02d", minutes, seconds);
            }
        }
        else
        {
            out = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }
        return out;
    }

}
