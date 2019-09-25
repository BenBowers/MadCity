package edu.curtin.madcity;

public class Settings
{
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

    public Settings(int mapWidth, int mapHeight, int initialMoney,
                    int familySize, int shopSize, int salary, float taxRate,
                    int serviceCost, int houseBuildingCost,
                    int commBuildingCost, int roadBuildingCost) throws IllegalArgumentException
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

    public Settings()
    {
        setDefault();
    }

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


    public int getMapWidth()
    {
        return mMapWidth;
    }

    public void setMapWidth(int mapWidth) throws IllegalArgumentException
    {
        if (mapWidth >= MIN_MAP_WIDTH && mapWidth <= MAX_MAP_WIDTH)
        {
            mMapWidth = mapWidth;
        }
        else
        {
            throw new IllegalArgumentException("Width out of range");
        }
    }

    public int getMapHeight()
    {
        return mMapHeight;
    }

    public void setMapHeight(int mapHeight) throws IllegalArgumentException
    {
        if (mapHeight >= MIN_MAP_HEIGHT && mapHeight <= MAX_MAP_HEIGHT)
        {
            mMapHeight = mapHeight;
        }
        else
        {
            throw new IllegalArgumentException("Height out of range");
        }
    }

    public int getInitialMoney()
    {
        return mInitialMoney;
    }

    public void setInitialMoney(int initialMoney) throws IllegalArgumentException
    {
        if (initialMoney >= MIN_INITIAL_MONEY && initialMoney <= MAX_INITIAL_MONEY)
        {
            mInitialMoney = initialMoney;
        }
        else
        {
            throw new IllegalArgumentException("Initial money out of range");
        }
    }

    public int getFamilySize()
    {
        return mFamilySize;
    }

    public void setFamilySize(int familySize) throws IllegalArgumentException
    {
        if (familySize >= MIN_FAMILY_SIZE && familySize <= MAX_FAMILY_SIZE)
        {
            mFamilySize = familySize;
        }
        else
        {
            throw new IllegalArgumentException("family size out of range");
        }

    }

    public int getShopSize()
    {
        return mShopSize;
    }

    public void setShopSize(int shopSize) throws IllegalArgumentException
    {
        if (shopSize >= MIN_SHOP_SIZE && shopSize <= MAX_SHOP_SIZE)
        {
            mShopSize = shopSize;
        }
        else
        {
            throw new IllegalArgumentException("shop size out of range");
        }
    }

    public int getSalary()
    {
        return mSalary;
    }

    public void setSalary(int salary) throws IllegalArgumentException
    {
        if (salary >= MIN_SALARY && salary <= MAX_SALARY)
        {
            mSalary = salary;
        }
        else
        {
            throw new IllegalArgumentException("Salary out of range");
        }
    }

    public float getTaxRate()
    {
        return mTaxRate;
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

    public int getServiceCost()
    {
        return mServiceCost;
    }

    public void setServiceCost(int serviceCost) throws IllegalArgumentException
    {
        if (serviceCost >= MIN_SERVICE_COST && serviceCost <= MAX_SERVICE_COST)
        {
            mServiceCost = serviceCost;
        }
        else
        {
            throw new IllegalArgumentException("Service cost out of range");
        }
    }

    public int getHouseBuildingCost()
    {
        return mHouseBuildingCost;
    }

    public void setHouseBuildingCost(int houseBuildingCost) throws IllegalArgumentException
    {
        if (houseBuildingCost >= MIN_HOUSE_BUILDING_COST && houseBuildingCost >= MAX_HOUSE_BUILDING_COST)
        {
            mHouseBuildingCost = houseBuildingCost;
        }
        else
        {
            throw new IllegalArgumentException("house building cost out of " +
                                                       "range");
        }
    }

    public int getCommBuildingCost()
    {
        return mCommBuildingCost;
    }

    public void setCommBuildingCost(int commBuildingCost) throws IllegalArgumentException
    {
        if (commBuildingCost >= MIN_COMM_BUILDING_COST && commBuildingCost <= MAX_COMM_BUILDING_COST)
        {
            mCommBuildingCost = commBuildingCost;
        }
        else
        {
            throw new IllegalArgumentException("comm building cost is out " +
                                                       "of range");
        }
    }

    public int getRoadBuildingCost()
    {
        return mRoadBuildingCost;
    }

    public void setRoadBuildingCost(int roadBuildingCost) throws IllegalArgumentException
    {
        if (roadBuildingCost >= MIN_ROAD_BUILDING_COST && roadBuildingCost <= MAX_ROAD_BUILDING_COST)
        {
            mRoadBuildingCost = roadBuildingCost;
        }
        else
        {
            throw new IllegalArgumentException("road building cost out of " +
                                                       "range");
        }
    }
}
