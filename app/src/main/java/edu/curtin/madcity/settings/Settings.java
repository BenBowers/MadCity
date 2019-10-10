package edu.curtin.madcity.settings;

import edu.curtin.madcity.R;

public class Settings
{

// CLASS CONSTANTS -----------------------------------------------------------


    public static final int DEFAULT_MAP_WIDTH = 10;
    public final  IntSetting MAP_WIDTH  =
            new IntSetting(R.string.settings_map_width, 500, 10);

    public static final int DEFAULT_MAP_HEIGHT = 10;
    public final IntSetting MAP_HEIGHT =
            new IntSetting(R.string.settings_map_height, 20, 5);

    public static final int DEFAULT_INITIAL_MONEY = 100;
    public final IntSetting INITIAL_MONEY =
            new IntSetting(R.string.settings_initial_money, 5000, 50);

    public static final int DEFAULT_FAMILY_SIZE = 4;
    public final IntSetting FAMILY_SIZE =
            new IntSetting(R.string.settings_family_size, 10, 1);

    public static final int DEFAULT_SHOP_SIZE = 6;
    public final IntSetting SHOP_SIZE =
            new IntSetting(R.string.settings_shop_size, 50, 1);

    public static final int DEFAULT_SALARY = 10;
    public final IntSetting SALARY =
            new IntSetting(R.string.settings_salary, 1000, 1);

    public static final float DEFAULT_TAX_RATE = 0.3f;
    public final FloatSetting TAX_RATE =
            new FloatSetting(R.string.settings_salary, 1f, 0.1f);

    public static final int DEFAULT_SERVICE_COST = 2;
    public final IntSetting SERVICE_COST =
            new IntSetting(R.string.settings_service_cost, 10, 1);

    public static final int DEFAULT_HOUSE_BUILDING_COST = 100;
    public final IntSetting HOUSE_BUILDING_COST =
            new IntSetting(R.string.settings_house_buildingCost, 1000, 1);

    public static final int DEFAULT_COMM_BUILDING_COST = 500;
    public final IntSetting COMM_BUILDING_COST =
            new IntSetting(R.string.settings_comm_buildingCost, 5000, 1);

    public static final int DEFAULT_ROAD_BUILDING_COST = 20;
    public final IntSetting ROAD_BUILDING_COST =
            new IntSetting(R.string.settings_road_buildingCost, 500, 1);

    /**
     * Array of all the settings
     */
    private final Setting[] SETTINGS = new Setting[]
    {
            MAP_WIDTH,
            MAP_HEIGHT,
            INITIAL_MONEY,
            FAMILY_SIZE,
            SHOP_SIZE,
            SALARY,
            TAX_RATE,
            SERVICE_COST,
            HOUSE_BUILDING_COST,
            COMM_BUILDING_COST,
            ROAD_BUILDING_COST
    };

// CONSTRUCTORS --------------------------------------------------------------

    public Settings()
    {
        setDefault();
    }

    public Settings(int mapWidth, int mapHeight, int initialMoney,
                   int familySize, int shopSize, int salary, float taxRate,
                   int serviceCost, int houseBuildingCost,
                   int commBuildingCost, int roadBuildingCost)
    {
        MAP_WIDTH.setValue(mapWidth);
        MAP_HEIGHT.setValue(mapHeight);
        INITIAL_MONEY.setValue(initialMoney);
        FAMILY_SIZE.setValue(familySize);
        SHOP_SIZE.setValue(shopSize);
        SALARY.setValue(salary);
        TAX_RATE.setValue(taxRate);
        SERVICE_COST.setValue(serviceCost);
        HOUSE_BUILDING_COST.setValue(houseBuildingCost);
        COMM_BUILDING_COST.setValue(commBuildingCost);
        ROAD_BUILDING_COST.setValue(roadBuildingCost);
    }

    public Setting getSetting(int pos)
    {
        return SETTINGS[pos];
    }

// PUBLIC METHODS ------------------------------------------------------------

    /**
     * Sets all the class fields to their default values
     */
    public void setDefault()
    {
        MAP_WIDTH.setValue(DEFAULT_MAP_WIDTH);
        MAP_HEIGHT.setValue(DEFAULT_MAP_HEIGHT);
        INITIAL_MONEY.setValue(DEFAULT_INITIAL_MONEY);
        FAMILY_SIZE.setValue(DEFAULT_FAMILY_SIZE);
        SHOP_SIZE.setValue(DEFAULT_SHOP_SIZE);
        SALARY.setValue(DEFAULT_SALARY);
        TAX_RATE.setValue(DEFAULT_TAX_RATE);
        SERVICE_COST.setValue(DEFAULT_SERVICE_COST);
        HOUSE_BUILDING_COST.setValue(DEFAULT_HOUSE_BUILDING_COST);
        COMM_BUILDING_COST.setValue(DEFAULT_COMM_BUILDING_COST);
        ROAD_BUILDING_COST.setValue(DEFAULT_ROAD_BUILDING_COST);
    }

    public int getSize()
    {
        return SETTINGS.length;
    }
}
