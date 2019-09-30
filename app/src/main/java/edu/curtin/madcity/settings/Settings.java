package edu.curtin.madcity.settings;

import edu.curtin.madcity.R;

public class Settings
{

// CLASS CONSTANTS -----------------------------------------------------------


    public static final int DEFAULT_MAP_WIDTH = 50;
    public static final int MIN_MAP_WIDTH = 10;
    public static final int MAX_MAP_WIDTH = 500;

    public static final int DEFAULT_MAP_HEIGHT = 10;
    public static final int MIN_MAP_HEIGHT = 5;
    public static final int MAX_MAP_HEIGHT = 20;

    public static final int DEFAULT_INITIAL_MONEY = 100;
    public static final int MIN_INITIAL_MONEY = 50;
    public static final int MAX_INITIAL_MONEY = 5000;

    public static final int DEFAULT_FAMILY_SIZE = 4;
    public static final int MIN_FAMILY_SIZE = 1;
    public static final int MAX_FAMILY_SIZE = 10;

    public static final int DEFAULT_SHOP_SIZE = 6;
    public static final int MIN_SHOP_SIZE = 1;
    public static final int MAX_SHOP_SIZE = 50;

    public static final int DEFAULT_SALARY = 10;
    public static final int MIN_SALARY = 1;
    public static final int MAX_SALARY = 1000;

    public static final float DEFAULT_TAX_RATE = 0.3f;
    public static final float MIN_TAX_RATE = 0.1f;
    public static final float MAX_TAX_RATE = 1f;

    public static final int DEFAULT_SERVICE_COST = 2;
    public static final int MIN_SERVICE_COST = 1;
    public static final int MAX_SERVICE_COST = 10;

    public static final int DEFAULT_HOUSE_BUILDING_COST = 100;
    public static final int MIN_HOUSE_BUILDING_COST = 1;
    public static final int MAX_HOUSE_BUILDING_COST = 1000;

    public static final int DEFAULT_COMM_BUILDING_COST = 500;
    public static final int MIN_COMM_BUILDING_COST = 1;
    public static final int MAX_COMM_BUILDING_COST = 5000;

    public static final int DEFAULT_ROAD_BUILDING_COST = 20;
    public static final int MIN_ROAD_BUILDING_COST = 1;
    public static final int MAX_ROAD_BUILDING_COST = 500;

    /**
     * Positions of all the settings
     */
    
    private static final short MAP_WIDTH_IDX           = 0;
    private static final short MAP_HEIGHT_IDX          = 1;
    private static final short INITIAL_MONEY_IDX       = 2;
    private static final short FAMILY_SIZE_IDX         = 3;
    private static final short SHOP_SIZE_IDX           = 4;
    private static final short TAX_RATE_IDX            = 6;
    private static final short SALARY_IDX              = 5;
    private static final short SERVICE_COST_IDX        = 7;
    private static final short HOUSE_BUILDING_COST_IDX = 8;
    private static final short COMM_BUILDING_COST_IDX  = 9;
    private static final short ROAD_BUILDING_COST_IDX  = 10;

// PRIVATE CLASS FIELDS ------------------------------------------------------


    /**
     * Array of all the settings
     */
    private Setting[] mSettings = new Setting[]
    {
        new IntSetting(R.string.settings_map_width,
                       MAX_MAP_WIDTH, MIN_MAP_WIDTH),
        new IntSetting(R.string.settings_map_height,
                      MAX_MAP_HEIGHT, MIN_MAP_HEIGHT),
        new IntSetting(R.string.settings_initial_money,
                          MAX_INITIAL_MONEY, MIN_INITIAL_MONEY),
        new IntSetting(R.string.settings_family_size,
                      MAX_FAMILY_SIZE, MIN_FAMILY_SIZE),
        new IntSetting(R.string.settings_shop_size,
                      MAX_SHOP_SIZE, MIN_SHOP_SIZE),
        new IntSetting(R.string.settings_salary,
                      MAX_SALARY, MIN_SALARY),
        new FloatSetting(R.string.settings_tax_rate,
                      MAX_TAX_RATE, MIN_TAX_RATE),
        new IntSetting(R.string.settings_service_cost,
                      MAX_SERVICE_COST, MIN_SERVICE_COST),
        new IntSetting(R.string.settings_house_buildingCost,
                      MAX_HOUSE_BUILDING_COST, MIN_HOUSE_BUILDING_COST),
        new IntSetting(R.string.settings_comm_buildingCost,
                      MAX_COMM_BUILDING_COST, MIN_COMM_BUILDING_COST),
        new IntSetting(R.string.settings_road_buildingCost,
                      MAX_ROAD_BUILDING_COST, MIN_ROAD_BUILDING_COST)

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
        setMapWidth(mapWidth);
        setMapHeight(mapHeight);
        setInitialMoney(initialMoney);
        setFamilySize(familySize);
        setShopSize(shopSize);
        setSalary(salary);
        setTaxRate(taxRate);
        setServiceCost(serviceCost);
        setHouseBuildingCost(houseBuildingCost);
        setCommBuildingCost(commBuildingCost);
        setRoadBuildingCost(roadBuildingCost);
    }



// PUBLIC METHODS ------------------------------------------------------------

    /**
     * Sets all the class fields to their default values
     */
    public void setDefault()
    {
        setMapWidth(DEFAULT_MAP_WIDTH);
        setMapHeight(DEFAULT_MAP_HEIGHT);
        setInitialMoney(DEFAULT_INITIAL_MONEY);
        setFamilySize(DEFAULT_FAMILY_SIZE);
        setShopSize(DEFAULT_SHOP_SIZE);
        setSalary(DEFAULT_SALARY);
        setTaxRate(DEFAULT_TAX_RATE);
        setServiceCost(DEFAULT_SERVICE_COST);
        setHouseBuildingCost(DEFAULT_HOUSE_BUILDING_COST);
        setCommBuildingCost(DEFAULT_COMM_BUILDING_COST);
        setRoadBuildingCost(DEFAULT_ROAD_BUILDING_COST);
    }

    public int getSize()
    {
        return mSettings.length;
    }

// PRIVATE METHODS -----------------------------------------------------------

    private int getIntValue(short idx)
    {
        IntSetting setting;

        if(!(mSettings[idx] instanceof IntSetting))
        {
            throw new IllegalArgumentException(
                    "Idx is not of type IntSetting");
        }

        setting = (IntSetting)mSettings[idx];
        return setting.getValue();
    }

    private float getFloatValue(short idx)
    {
        FloatSetting setting;

        if(!(mSettings[idx] instanceof FloatSetting))
        {
            throw new IllegalArgumentException(
                    "Idx is not of type FloatSetting");
        }

        setting = (FloatSetting)mSettings[idx];
        return setting.getValue();
    }

    private int getName(short idx)
    {
        return mSettings[idx].getNameID();
    }


    private void setValue(int value, short idx)
    {
        IntSetting setting;

        if(!(mSettings[idx] instanceof IntSetting))
        {
            throw new IllegalArgumentException(
                    "Idx is not of type IntSetting");
        }

        setting = (IntSetting)mSettings[idx];
        setting.setValue(value);
    }

    private void setValue(float value, short idx)
    {
        FloatSetting setting;

        if(!(mSettings[idx] instanceof FloatSetting))
        {
            throw new IllegalArgumentException(
                    "Idx is not of type FloatSetting");
        }

        setting = (FloatSetting)mSettings[idx];
        setting.setValue(value);
    }

// ACCESSORS -----------------------------------------------------------------

    public final Setting getSetting(int idx)
    {
        return mSettings[idx];
    }

    public int getMapWidthValue()
    {
        return getIntValue(MAP_WIDTH_IDX);
    }

    public int getMapHeightValue()
    {
        return getIntValue(MAP_HEIGHT_IDX);
    }

    public int getInitialMoneyValue()
    {
        return getIntValue(INITIAL_MONEY_IDX);
    }

    public int getFamilySizeValue()
    {
        return getIntValue(FAMILY_SIZE_IDX);
    }

    public int getShopSizeValue()
    {
        return getIntValue(SHOP_SIZE_IDX);
    }

    public int getSalaryValue()
    {
        return getIntValue(SALARY_IDX);
    }

    public float getTaxRateValue()
    {
        return getFloatValue(TAX_RATE_IDX);
    }

    public int getServiceCostValue()
    {
        return getIntValue(SERVICE_COST_IDX);
    }

    public int getHouseBuildingCostValue()
    {
        return getIntValue(HOUSE_BUILDING_COST_IDX);
    }

    public int getCommBuildingCostValue()
    {
        return getIntValue(COMM_BUILDING_COST_IDX);
    }

    public int getRoadBuildingCostValue()
    {
        return getIntValue(ROAD_BUILDING_COST_IDX);
    }

// MUTATORS ------------------------------------------------------------------

    public void setMapWidth(int mapWidth) throws IllegalArgumentException
    {
        setValue(mapWidth, MAP_WIDTH_IDX);
    }


    public void setMapHeight(int mapHeight) throws IllegalArgumentException
    {
        setValue(mapHeight, MAP_HEIGHT_IDX);
    }

    public void setInitialMoney(int initialMoney)
            throws IllegalArgumentException
    {
        setValue(initialMoney, INITIAL_MONEY_IDX);
    }

    public void setFamilySize(int familySize) throws IllegalArgumentException
    {
        setValue(familySize, FAMILY_SIZE_IDX);
    }

    public void setShopSize(int shopSize) throws IllegalArgumentException
    {
        setValue(shopSize, SHOP_SIZE_IDX);
    }

    public void setSalary(int salary) throws IllegalArgumentException
    {
        setValue(salary, SALARY_IDX);
    }

    public void setTaxRate(float taxRate) throws IllegalArgumentException
    {
        setValue(taxRate, TAX_RATE_IDX);
    }

    public void setServiceCost(int serviceCost)
            throws IllegalArgumentException
    {
        setValue(serviceCost, SERVICE_COST_IDX);
    }

    public void setHouseBuildingCost(int houseBuildingCost)
            throws IllegalArgumentException
    {
        setValue(houseBuildingCost, HOUSE_BUILDING_COST_IDX);
    }

    public void setCommBuildingCost(int commBuildingCost)
            throws IllegalArgumentException
    {
        setValue(commBuildingCost, COMM_BUILDING_COST_IDX);
    }

    public void setRoadBuildingCost(int roadBuildingCost)
            throws IllegalArgumentException
    {
        setValue(roadBuildingCost, ROAD_BUILDING_COST_IDX);
    }
}
