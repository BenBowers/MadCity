package edu.curtin.madcity;

public class Settings
{

// CLASS CONSTANTS -----------------------------------------------------------

    private static final short MAP_WIDTH_IDX  = 0;
    public static final int DEFAULT_MAP_WIDTH = 50;
    public static final int MIN_MAP_WIDTH = 10;
    public static final int MAX_MAP_WIDTH = 500;

    private static final short MAP_HEIGHT_IDX = 1;
    public static final int DEFAULT_MAP_HEIGHT = 10;
    public static final int MIN_MAP_HEIGHT = 5;
    public static final int MAX_MAP_HEIGHT = 20;

    private static final short INITIAL_MONEY_IDX = 2;
    public static final int DEFAULT_INITIAL_MONEY = 100;
    public static final int MIN_INITIAL_MONEY = 50;
    public static final int MAX_INITIAL_MONEY = 5000;

    private static final short FAMILY_SIZE_IDX = 3;
    public static final int DEFAULT_FAMILY_SIZE = 4;
    public static final int MIN_FAMILY_SIZE = 1;
    public static final int MAX_FAMILY_SIZE = 10;

    private static final int SHOP_SIZE_IDX = 4;
    public static final int DEFAULT_SHOP_SIZE = 6;
    public static final int MIN_SHOP_SIZE = 1;
    public static final int MAX_SHOP_SIZE = 50;

    public static final short SALARY_IDX = 5;
    public static final int DEFAULT_SALARY = 10;
    public static final int MIN_SALARY = 1;
    public static final int MAX_SALARY = 1000;

    public static final short TAX_RATE_IDX = 6;
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


// PRIVATE CLASS FIELDS ------------------------------------------------------


    private Setting[] mSettings = new Setting[]
    {
        new Setting<>(R.string.settings_map_width,
                      MAX_MAP_WIDTH, MIN_MAP_WIDTH),
        new Setting<>(R.string.settings_map_height,
                      MAX_MAP_HEIGHT, MIN_MAP_HEIGHT),
        new Setting<>(R.string.settings_initial_money,
                          MAX_INITIAL_MONEY, MIN_INITIAL_MONEY),
        new Setting<>(R.string.settings_family_size,
                      MAX_FAMILY_SIZE, MIN_FAMILY_SIZE),
        new Setting<>(R.string.settings_shop_size,
                      MAX_SHOP_SIZE, MIN_FAMILY_SIZE),
        new Setting<>(R.string.settings_salary,
                      MAX_SALARY, MIN_SALARY),
        new Setting<>(R.string.settings_tax_rate,
                      MAX_TAX_RATE, MIN_TAX_RATE),
        new Setting<>(R.string.settings_service_cost,
                      MAX_SERVICE_COST, MIN_SERVICE_COST),
        new Setting<>(R.string.settings_house_buildingCost,
                      MAX_HOUSE_BUILDING_COST, MIN_HOUSE_BUILDING_COST),
        new Setting<>(R.string.settings_comm_buildingCost,
                      MAX_COMM_BUILDING_COST, MIN_COMM_BUILDING_COST),
        new Setting<>(R.string.settings_road_buildingCost,
                      MAX_ROAD_BUILDING_COST, MIN_ROAD_BUILDING_COST)

    };


// CONSTRUCTORS --------------------------------------------------------------

    public Settings()
    {
        setDefault();
    }



// PUBLIC METHODS ------------------------------------------------------------

    /**
     * Sets all the class fields to their default values
     */
    public void setDefault()
    {
        mMapWidth = DEFAULT_MAP_WIDTH;
        mMapHeight = DEFAULT_MAP_HEIGHT;
        mInitialMoney = DEFAULT_INITIAL_MONEY;
        mFamilySize = DEFAULT_FAMILY_SIZE;
        mShopSize = DEFAULT_SHOP_SIZE;
        mSalary = DEFAULT_SALARY;
        mTaxRate = DEFAULT_TAX_RATE;
        mServiceCost = DEFAULT_SERVICE_COST;
        mHouseBuildingCost = DEFAULT_HOUSE_BUILDING_COST;
        mCommBuildingCost = DEFAULT_COMM_BUILDING_COST;
        mRoadBuildingCost = DEFAULT_ROAD_BUILDING_COST;
    }

// PRIVATE METHODS -----------------------------------------------------------

    private Object getValue(int idx)
    {
        return mSettings[idx].getValue();
    }


// ACCESSORS -----------------------------------------------------------------

    /**
     * 
     * @return map width
     */
    public int getMapWidth()
    {
        return (int)getValue(MAP_WIDTH_IDX);
    }

    /**
     * 
     * @return map height
     */
    public int getMapHeight()
    {
        return (int)getValue(MAP_HEIGHT_IDX);
    }

    /**
     * 
     * @return initial money
     */
    public int getInitialMoney()
    {
        return (int)getValue(INITIAL_MONEY_IDX);
    }

    /**
     * 
     * @return family size
     */
    public int getFamilySize()
    {
        return mFamilySize;
    }

    /**
     * 
     * @return shop size
     */
    public int getShopSize()
    {
        return mShopSize;
    }


    /**
     * 
     * @return salary
     */
    public int getSalary()
    {
        return mSalary;
    }

    /**
     * 
     * @return tax rate
     */
    public float getTaxRate()
    {
        return mTaxRate;
    }

    /**
     * 
     * @return service cost
     */
    public int getServiceCost()
    {
        return mServiceCost;
    }

    /**
     * 
     * @return house building cost
     */
    public int getHouseBuildingCost()
    {
        return mHouseBuildingCost;
    }

    /**
     * 
     * @return comm building cost
     */
    public int getCommBuildingCost()
    {
        return mCommBuildingCost;
    }

    /**
     * 
     * @return road building cost
     */
    public int getRoadBuildingCost()
    {
        return mRoadBuildingCost;
    }

// MUTATORS ------------------------------------------------------------------

    /**
     * sets the map width
     * 
     * @param mapWidth parameter to set the width to
     * @throws IllegalArgumentException width is out of the max or min width
     */
    public void setMapWidth(int mapWidth) throws IllegalArgumentException
    {
        if (withinInclusive(mapWidth, MIN_MAP_WIDTH, MAX_MAP_WIDTH))
        {
            mMapWidth = mapWidth;
        }
        else
        {
            throw new IllegalArgumentException("Width out of range");
        }
    }

    /**
     * Sets the map height
     * 
     * @param mapHeight Parameter to set the map hieght to
     * @throws IllegalArgumentException Map height out of the max and min 
     * bounds
     */
    public void setMapHeight(int mapHeight) throws IllegalArgumentException
    {
        if (withinInclusive(mapHeight, MIN_MAP_HEIGHT, MAX_MAP_HEIGHT))
        {
            mMapHeight = mapHeight;
        }
        else
        {
            throw new IllegalArgumentException("Height out of range");
        }
    }

    /**
     * Sets the InitalMoney
     *
     * @param initialMoney Parameter to set the inital money to
     * @throws IllegalArgumentException if the intial money is out of range
     */
    public void setInitialMoney(int initialMoney)
            throws IllegalArgumentException
    {
        if (withinInclusive(initialMoney,
                            MIN_INITIAL_MONEY, MAX_INITIAL_MONEY))
        {
            mInitialMoney = initialMoney;
        }
        else
        {
            throw new IllegalArgumentException("Initial money out of range");
        }
    }

    public void setFamilySize(int familySize) throws IllegalArgumentException
    {
        if (withinInclusive(familySize, MIN_FAMILY_SIZE, MAX_FAMILY_SIZE))
        {
            mFamilySize = familySize;
        }
        else
        {
            throw new IllegalArgumentException("family size out of range");
        }

    }

    public void setShopSize(int shopSize) throws IllegalArgumentException
    {
        if (withinInclusive(shopSize, MIN_SHOP_SIZE, MAX_SHOP_SIZE))
        {
            mShopSize = shopSize;
        }
        else
        {
            throw new IllegalArgumentException("shop size out of range");
        }
    }

    public void setSalary(int salary) throws IllegalArgumentException
    {
        if (withinInclusive(salary, MIN_SALARY, MAX_SALARY))
        {
            mSalary = salary;
        }
        else
        {
            throw new IllegalArgumentException("Salary out of range");
        }
    }

    public void setTaxRate(float taxRate) throws IllegalArgumentException
    {
        if (taxRate >= MIN_TAX_RATE && taxRate <= MAX_TAX_RATE)
        {
            mTaxRate = taxRate;
        }
        else
        {
            throw new IllegalArgumentException("Tax rate out of range");
        }
    }

    public void setServiceCost(int serviceCost)
            throws IllegalArgumentException
    {
        if (withinInclusive(serviceCost, MIN_SERVICE_COST, MAX_SERVICE_COST))
        {
            mServiceCost = serviceCost;
        }
        else
        {
            throw new IllegalArgumentException("Service cost out of range");
        }
    }

    public void setHouseBuildingCost(int houseBuildingCost)
            throws IllegalArgumentException
    {
        if (withinInclusive(houseBuildingCost,
                            MIN_HOUSE_BUILDING_COST, MAX_HOUSE_BUILDING_COST))
        {
            mHouseBuildingCost = houseBuildingCost;
        }
        else
        {
            throw new IllegalArgumentException(
                    "house building cost out of range");
        }
    }

    public void setCommBuildingCost(int commBuildingCost)
            throws IllegalArgumentException
    {
        if (withinInclusive(commBuildingCost,
                            MIN_COMM_BUILDING_COST, MAX_COMM_BUILDING_COST))
        {
            mCommBuildingCost = commBuildingCost;
        }
        else
        {
            throw new IllegalArgumentException(
                    "comm building cost is out of range");
        }
    }

    public void setRoadBuildingCost(int roadBuildingCost)
            throws IllegalArgumentException
    {
        if (withinInclusive(roadBuildingCost,
                            MIN_ROAD_BUILDING_COST, MAX_ROAD_BUILDING_COST))
        {
            mRoadBuildingCost = roadBuildingCost;
        }
        else
        {
            throw new IllegalArgumentException(
                    "road building cost out of range");
        }
    }
}
