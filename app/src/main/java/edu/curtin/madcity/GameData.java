package edu.curtin.madcity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import edu.curtin.madcity.database.DbHelper;
import edu.curtin.madcity.settings.Settings;
import edu.curtin.madcity.structure.Commercial;
import edu.curtin.madcity.structure.Residential;
import edu.curtin.madcity.structure.Road;
import edu.curtin.madcity.structure.Structure;

public class GameData
{
    /**
     * Number of milliseconds for the game to increment by.
     */
    public static final int GAME_INCREMENT = 1000;

    public Settings settings;

    /**
     * Singleton instance
     */
    private static final GameData ourInstance = new GameData();

    /**
     * Singleton accessor
     * @return GameData instance
     */
    public static GameData getInstance()
    {
        return ourInstance;
    }

    private SQLiteDatabase db;
    private final Timer TIMER = new Timer();




// PRIVATE CLASS FIELDS ------------------------------------------------------

    /**
     * 2D array of map elements representing the game map
     */
    public MapElement[][] mMap;

    /**
     * Number of residential buildings in the town.
     */
    private int mNumResidential;

    /**
     * Number of commercial buildings in the town.
     */
    private int mNumCommercial;

    /**
     * Players money
     */
    private int mMoney;

    /**
     * Games time
     */
    private int mGameTime;

    private float mEarning;

    private GameData()
    {
        initTimer();
    }

    /**
     * Creates a new game
     */
    public void newGame()
    {
        // Set these class fields to zero.
        mGameTime = 0;
        mNumResidential = 0;
        mNumCommercial = 0;

        mMoney = settings.INITIAL_MONEY.getValue();

        // Generate a new map.
        mMap =
                new MapElement[settings.MAP_WIDTH.getValue()]
                        [settings.MAP_HEIGHT.getValue()];
    }

    /**
     * Accessor for the players money.
     * @return Money of the player.
     */
    public int getMoney()
    {
        return mMoney;
    }

    /**
     * Accessor for the integer value of the games time.
     * @return time of the game
     */
    public int getGameTime()
    {
        return mGameTime;
    }

    /**
     * Calculates the population of a town.
     * @return population of town
     */
    public int getPopulation()
    {
        return settings.FAMILY_SIZE.getValue() * mNumResidential;
    }

    /**
     * Gets the current employment of the town
     * @return
     */
    public float getEmployment()
    {
        float val = 0;
        int population = getPopulation();

        if(population != 0) // Prevent division by 0
        {
            val = Math.min(1, mNumCommercial *
                    (float) settings.SHOP_SIZE.getValue() / (float)population);
        }

        return val;
    }

    public float getEarning()
    {
        return mEarning;
    }

    /**
     * Function called by the games timer to increment the games time.
     */
    public void increaseTime()
    {
        mGameTime++;
        mEarning = getPopulation() * (getEmployment() *
                settings.SALARY.getValue() * settings.TAX_RATE.getValue()
                - settings.SERVICE_COST.getValue());
        mMoney += mEarning;
    }


    /**
     * Gets the game time and returns it in a formatted string to be used
     * in a ui.
     * @return String representing the time.
     */
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

    public void addStructure(Context context, Structure structure, int x,
                             int y)
            throws IllegalStateException, InsufficientFundsException,
            IllegalArgumentException
    {
        if( mMap[x][y] != null)
        {
            throw new IllegalArgumentException("Structure exists");
        }
        if ( structure instanceof Road)
        {
            withdrawFunds(settings.ROAD_BUILDING_COST.getValue());
            setStructure(context, structure, x, y);
        }
        else if (hasSurroundingRoad(x, y))
        {

            if(structure instanceof Residential)
            {
                withdrawFunds(settings.HOUSE_BUILDING_COST.getValue());
                mNumResidential++;
            }
            else
            {
                withdrawFunds(settings.COMM_BUILDING_COST.getValue());
                mNumCommercial++;
            }
            setStructure(context, structure, x, y);
        }
        else
        {
            throw new IllegalStateException("No surrounding road");
        }



    }

    public void removeStructure(int x, int y)
    {
        Structure structure = mMap[x][y].getStructure();
        if (structure instanceof Commercial)
        {
            mNumCommercial--;
        }
        else if (structure instanceof  Residential)
        {
            mNumResidential--;
        }
        mMap[x][y] = null;
    }




// PRIVATE METHODS -----------------------------------------------------------

    /**
     * Initialises the game timer.
     */
    private void initTimer()
    {
        TimerTask t = new TimerTask()
        {
            @Override
            public void run()
            {
                GameData.this.increaseTime();
            }
        };

        TIMER.scheduleAtFixedRate(t, 0, GAME_INCREMENT);
    }

    /**
     * Loads the games database.
     * @param context context that is loading it
     */
    private void load(Context context)
    {
        this.db =
                new DbHelper(context.getApplicationContext())
                        .getWritableDatabase();
    }

    private void setStructure(Context context, Structure structure, int x,
                              int y)
    {
        if (mMap[x][y] == null)
        {
            mMap[x][y] =
                    new MapElement(context.getResources().getString(
                            structure.getName()));
        }

        mMap[x][y].setStructure(structure);
    }

    private boolean hasSurroundingRoad(int x, int y)
    {
        return ((x - 1 >= 0) && roadExists(x - 1, y)) ||
                ((y + 1 < mMap[0].length) &&
                        roadExists(x, y + 1)) ||
                ((x + 1 < mMap.length) &&
                        roadExists(x + 1, y)) ||
                ((y - 1 >= 0) && roadExists(x, y - 1));
    }


    private boolean roadExists(int x, int y)
    {
        return (mMap[x][y] != null) &&
                (mMap[x][y].getStructure() != null) &&
                (mMap[x][y].getStructure() instanceof Road);
    }

    private void withdrawFunds(int amount) throws InsufficientFundsException
    {
        if(mMoney - amount >= 0 )
        {
            mMoney -= amount;
        }
        else
        {
            throw new InsufficientFundsException();
        }
    }

}
