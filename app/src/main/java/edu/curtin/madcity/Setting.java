package edu.curtin.madcity;

public class Setting<E extends Comparable<E>>
{
    private int mNameID;
    private E mValue;
    private E mMax;
    private E mMin;

    public Setting(int nameID, E max, E min)
    {
        mNameID = nameID;
        mMax = max;
        mMin = min;
    }

    public int getNameID()
    {
        return mNameID;
    }

    public E getValue()
    {
        return mValue;
    }

    public void setValue(E value) throws IllegalArgumentException
    {
        if (value.compareTo(mMin) >= 0 && value.compareTo(mMax) <= 0)
        {
            mValue = value;
        }
        else
        {
            throw new IllegalArgumentException("Value is out of range");
        }

    }
}
