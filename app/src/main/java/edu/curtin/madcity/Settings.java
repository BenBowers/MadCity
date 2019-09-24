package edu.curtin.madcity;

public class Settings
{
    private int mMapWidth = 50;
    private int mMapHeight = 10;
    private int mInitialMoney = 100;
    private int mFamilySize = 4;
    private int mShopSize = 6;
    private int mSalary = 10;
    private float mTaxRate = 0.3f;
    private int mServiceCost = 2;
    private int mHouseBuildingCost = 100;
    private int mCommBuildingCost = 500;
    private int mRoadBuildingCost = 20;

    public int getMapWidth()
    {
        return mMapWidth;
    }

    public void setMapWidth(int mapWidth)
    {
        mMapWidth = mapWidth;
    }

    public int getMapHeight()
    {
        return mMapHeight;
    }

    public void setMapHeight(int mapHeight)
    {
        mMapHeight = mapHeight;
    }

    public int getInitialMoney()
    {
        return mInitialMoney;
    }

    public void setInitialMoney(int initialMoney)
    {
        mInitialMoney = initialMoney;
    }

    public int getFamilySize()
    {
        return mFamilySize;
    }

    public void setFamilySize(int familySize)
    {
        mFamilySize = familySize;
    }

    public int getShopSize()
    {
        return mShopSize;
    }

    public void setShopSize(int shopSize)
    {
        mShopSize = shopSize;
    }

    public int getSalary()
    {
        return mSalary;
    }

    public void setSalary(int salary)
    {
        mSalary = salary;
    }

    public float getTaxRate()
    {
        return mTaxRate;
    }

    public void setTaxRate(float taxRate)
    {
        mTaxRate = taxRate;
    }

    public int getServiceCost()
    {
        return mServiceCost;
    }

    public void setServiceCost(int serviceCost)
    {
        mServiceCost = serviceCost;
    }

    public int getHouseBuildingCost()
    {
        return mHouseBuildingCost;
    }

    public void setHouseBuildingCost(int houseBuildingCost)
    {
        mHouseBuildingCost = houseBuildingCost;
    }

    public int getCommBuildingCost()
    {
        return mCommBuildingCost;
    }

    public void setCommBuildingCost(int commBuildingCost)
    {
        mCommBuildingCost = commBuildingCost;
    }

    public int getRoadBuildingCost()
    {
        return mRoadBuildingCost;
    }

    public void setRoadBuildingCost(int roadBuildingCost)
    {
        mRoadBuildingCost = roadBuildingCost;
    }
}
