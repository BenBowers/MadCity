package edu.curtin.madcity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import edu.curtin.madcity.database.DbHelper;
import edu.curtin.madcity.database.DbSchema;
import edu.curtin.madcity.database.DbSchema.GameDataTable;
import edu.curtin.madcity.database.DbSchema.MapElementTable;
import edu.curtin.madcity.database.GameDataCursor;
import edu.curtin.madcity.database.MapElementCursor;
import edu.curtin.madcity.database.SettingsCursor;
import edu.curtin.madcity.settings.Settings;
import edu.curtin.madcity.structure.Commercial;
import edu.curtin.madcity.structure.Residential;
import edu.curtin.madcity.structure.Road;
import edu.curtin.madcity.structure.Structure;
import edu.curtin.madcity.structure.StructureData;

/**
 * Singleton class holding all the game data shared across activities should
 * be initialized before a game is started
 */
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
    private static GameData ourInstance;

    /**
     * Singleton accessor
     * @return GameData instance
     */
    public static GameData getInstance(Context context)
    {
        if(ourInstance == null)
        {
            ourInstance = new GameData(context);
        }
        return ourInstance;
    }

    public static GameData getInstance()
    {
        return ourInstance;
    }


// PRIVATE CLASS FIELDS ------------------------------------------------------

    /**
     * 2D array of map elements representing the game map
     */
    public MapElement[][] mMap;

    private SQLiteDatabase db;
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

    private GameData(Context context)
    {
        load(context);
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
        initMap();

        // Delete all the old game data
        db.delete(MapElementTable.NAME, null,null);
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

    public void setGameTime(int gameTime)
    {
        mGameTime = gameTime;
    }

    public void setMoney(int money)
    {
        mMoney = money;
    }

    public void setNumCommercial(int numCommercial)
    {
        mNumCommercial = numCommercial;
    }

    public void setNumResidential(int numResidential)
    {
        mNumResidential = numResidential;
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
        updateDb();

    }



    public int getNumResidential()
    {
        return mNumResidential;
    }

    public int getNumCommercial()
    {
        return mNumCommercial;
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

    /**
     * Adds a structure to the map and database
     * @param structureId id of the structure to add
     * @param x x coordinate to add to
     * @param y y coordinate to add to
     * @throws IllegalStateException no road is surrounding the structure
     * @throws InsufficientFundsException not enough funds to buy the
     * structure
     * @throws IllegalArgumentException Structure already exists on cell
     */
    public void addStructure(int structureId, int x,
                             int y)
            throws IllegalStateException, InsufficientFundsException,
            IllegalArgumentException
    {
        Structure structure = StructureData.getStructure(structureId);

        if( mMap[x][y] != null)
        {
            throw new IllegalArgumentException("Structure exists");
        }
        if ( structure instanceof Road)
        {
            withdrawFunds(settings.ROAD_BUILDING_COST.getValue());
            setStructure(structureId, x, y);
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
            setStructure(structureId, x, y);
        }
        else
        {
            throw new IllegalStateException("No surrounding road");
        }

        db.insert(DbSchema.MapElementTable.NAME, null,
                  MapElementTable.CV(mMap[x][y], x, y));
        updateDb();

    }

    /**
     * Removes a structure from the game map
     * @param x x coordinate of the structure
     * @param y y  coordinate of the structure
     */
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
        db.delete(MapElementTable.NAME,
                  MapElementTable.Cols.X_LOC + " = ? AND " +
                    MapElementTable.Cols.Y_LOC + " = ?",
                  new String[] {Integer.toString(x), Integer.toString(y)});

        updateDb(); // Update the database
    }

    /**
     *
     * @return Database of the game
     */
    public SQLiteDatabase getDb()
    {
        return db;
    }




// PRIVATE METHODS -----------------------------------------------------------

    /**
     * Loads the games database.
     * @param context context that is loading it
     */
    private void load(Context context)
    {
        this.db =
                new DbHelper(context.getApplicationContext())
                        .getWritableDatabase();

        // Java 7 automatic resource control no need for cursor.close()
        loadSettings();
        loadGameData();
            initMap(); // Initialize the array from the settings
            loadMap(); // Load the map from the database
    }

    /**
     * Loads the game map elements into mMap from database
     */
    private void loadMap()
    {
        try(MapElementCursor cursor = new MapElementCursor(
                db.query(DbSchema.MapElementTable.NAME,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null)
        ))
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                mMap[cursor.getX()][cursor.getY()] = cursor.get();
                cursor.moveToNext();
            }
        }
    }

    /**
     * Loads the gameData fields from the database into memory
     */
    private void loadGameData()
    {
        // Load the game data
        try (GameDataCursor cursor = new GameDataCursor(
                db.query(GameDataTable.NAME,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null)
        ))
        {
            if( cursor.getCount() <= 0)
            {
                db.insert(GameDataTable.NAME, null,
                          GameDataTable.CV(this));
            }
            else
            {
                cursor.moveToFirst();
                cursor.get(this);
            }
        }
    }

    /**
     * Loads the settings from the database into memory
     */
    private void loadSettings()
    {
        // Load the settings
        try (SettingsCursor cursor = new SettingsCursor(
                db.query(DbSchema.SettingsTable.NAME,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null)
        ))
        {
            if(cursor.getCount() == 0) // There is no column set default
                // and insert
            {
                settings = new Settings();
                db.insert(DbSchema.SettingsTable.NAME, null,
                          DbSchema.SettingsTable.CV(settings));
            }
            else
            {
                cursor.moveToFirst();
                settings = cursor.get();
            }
        }
    }

    /**
     * Updates the game data database called when the a structure is
     * inserted or deleted or the game time is increased.
     */
    private void updateDb()
    {
        // Update the database whenever time is increased
        db.update(GameDataTable.NAME,
                  GameDataTable.CV(this),
                  "ID = ?", new String[] {"1"});
    }

    /**
     * Sets a structure at the x y coordinates
     * @param structure structure id to store there
     * @param x x coordinate
     * @param y y coordinate
     */
    private void setStructure(int structure, int x, int y)
    {
            mMap[x][y] = new MapElement(structure);
    }

    /**
     * Returns true if the map element has a surrounding road
     * @param x x coordinate
     * @param y y coordinate
     * @return true if the structure has a surrounding road
     */
    private boolean hasSurroundingRoad(int x, int y)
    {
        return ((x - 1 >= 0) && roadExists(x - 1, y)) ||
                ((y + 1 < mMap[0].length) &&
                        roadExists(x, y + 1)) ||
                ((x + 1 < mMap.length) &&
                        roadExists(x + 1, y)) ||
                ((y - 1 >= 0) && roadExists(x, y - 1));
    }


    /**
     * Returns true if the cell contains a road
     * @param x x coordinate of the cell
     * @param y y coordinate of the cell
     * @return true if the cell contains a road
     */
    private boolean roadExists(int x, int y)
    {
        return (mMap[x][y] != null) &&
                (mMap[x][y].getStructure() != null) &&
                (mMap[x][y].getStructure() instanceof Road);
    }

    /**
     * Withdraws funds from the players account
     * @param amount amount to withdraw
     * @throws InsufficientFundsException player doesn't have enough money
     */
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
        updateDb();
    }

    /**
     * Initializes the map based on the current settings
     */
    private void initMap()
    {
        // Generate a new map.
        mMap =
                new MapElement[settings.MAP_WIDTH.getValue()]
                        [settings.MAP_HEIGHT.getValue()];
    }


}
