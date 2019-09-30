package edu.curtin.madcity.settings;

import java.io.Serializable;

public abstract class Setting implements Serializable
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