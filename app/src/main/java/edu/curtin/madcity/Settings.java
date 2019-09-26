package edu.curtin.madcity;

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

// PRIVATE CLASS FIELDS ------------------------------------------------------

    private int mMapWidth;
    private int mMapHeight;
    private int mInitialMoney;
    private int mFamilySize;
    private int mShopSize;
    private int mSalary;
    private float mTaxRate;
    private int mServiceCost;
    private int mHouseBuildingCost;
    private int mCommBuildingCost;
    private int mRoadBuildingCost;

// CONSTRUCTORS --------------------------------------------------------------

    public Settings()
    {
        setDefault();
    }

    public Settings(int mapWidth, int mapHeight, int initialMoney,
                    int familySize, int shopSize, int salary, float taxRate,
                    int serviceCost, int houseBuildingCost,
                    int commBuildingCost, int roadBuildingCost)
            throws IllegalArgumentException
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

    private boolean withinInclusive(int x, int min, int max)
    {
        return x >= min && x <= max;
    }

// ACCESSORS -----------------------------------------------------------------

    public int getMapWidth()
    {
        return mMapWidth;
    }

    public int getMapHeight()
    {
        return mMapHeight;
    }

    public int getInitialMoney()
    {
        return mInitialMoney;
    }

    public int getFamilySize()
    {
        return mFamilySize;
    }

    public int getShopSize()
    {
        return mShopSize;
    }

    public int getSalary()
    {
        return mSalary;
    }

    public float getTaxRate()
    {
        return mTaxRate;
    }

    public int getServiceCost()
    {
        return mServiceCost;
    }

    public int getHouseBuildingCost()
    {
        return mHouseBuildingCost;
    }

    public int getCommBuildingCost()
    {
        return mCommBuildingCost;
    }

    public int getRoadBuildingCost()
    {
        return mRoadBuildingCost;
    }

// MUTATORS ------------------------------------------------------------------

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
