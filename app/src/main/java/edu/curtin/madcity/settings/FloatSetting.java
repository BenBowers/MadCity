package edu.curtin.madcity.settings;

public class FloatSetting extends Setting
{
    private float mValue;
    public final float MAX;
    public final float MIN;

    public FloatSetting(int nameID, float max, float min)
    {
        super(nameID);
        MAX = max;
        MIN = min;
    }

    public float getValue()
    {
        return mValue;
    }

    @Override
    public String getStringValue()
    {
        return Float.toString(mValue);
    }

    public void setValue(float value)
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