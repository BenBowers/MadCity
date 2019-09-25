package edu.curtin.madcity.Structure;

public abstract class Structure
{
    private final int mDrawableId;
    private String mName;

    public Structure(int drawableId, String name)
    {
        mDrawableId = drawableId;
        mName = name;
    }

    public int getDrawableId()
    {
        return mDrawableId;
    }

    public String getName()
    {
        return mName;
    }

}
