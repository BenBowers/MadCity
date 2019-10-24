package edu.curtin.madcity.settings;


/**
 * Class for holding an int setting
 */
public class IntSetting extends Setting
{
    private int mValue;
    public final int MAX;
    public final int MIN;

    public IntSetting(int nameID, int max, int min)
    {
        super(nameID);
        MAX = max;
        MIN = min;
    }

    public int getValue()
    {
        return mValue;
    }

    @Override
    public String getStringValue()
    {
        return Integer.toString(mValue);
    }


    public void setValue(int value)
    {
        if(value >= MIN && value <= MAX)
        {
            mValue = value;
        }
        else
        {
            throw new IllegalArgumentException("out of range");
        }
    }
}