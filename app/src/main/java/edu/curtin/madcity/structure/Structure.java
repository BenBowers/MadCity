package edu.curtin.madcity.structure;

public abstract class Structure
{
    private final int mDrawableId;
    private int mName;

    public Structure(int drawableId, int name)
    {
        mDrawableId = drawableId;
        mName = name;
    }

    public int getDrawableId()
    {
        return mDrawableId;
    }

    public int getName()
    {
        return mName;
    }

}
