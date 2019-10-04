package edu.curtin.madcity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import edu.curtin.madcity.database.DbHelper;
import edu.curtin.madcity.settings.Settings;

public class GameData
{
    private static final GameData ourInstance = new GameData();

    public static GameData getInstance()
    {
        return ourInstance;
    }

    SQLiteDatabase db;

    Settings mSettings;
    MapElement[][] mMap;
    int mMoney = 50;
    int mGameTime = 10;
    int population = 100;

    private GameData()
    {
        mSettings = new Settings();
    }

    private void load(Context context)
    {
        this.db =
                new DbHelper(context.getApplicationContext()).getWritableDatabase();

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
}
