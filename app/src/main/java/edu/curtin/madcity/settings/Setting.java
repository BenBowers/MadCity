package edu.curtin.madcity.settings;

public abstract class Setting
{
    private int mNameID;

    public Setting(int nameID)
    {
        mNameID = nameID;
    }
    
    public int getNameID()
    {
        return mNameID;
    }

    public abstract String getStringValue();
}