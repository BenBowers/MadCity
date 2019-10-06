package edu.curtin.madcity;

import android.graphics.Bitmap;

import edu.curtin.madcity.structure.Structure;

public class MapElement
{
    private Structure mStructure;
    private Bitmap mImage;
    private String mOwnerName;

    public MapElement(Structure structure)
    {
        mStructure = structure;
    }

    public MapElement()
    {

    }


    public Structure getStructure()
    {
        return mStructure;
    }

    public void setStructure(Structure structure)
    {
        mStructure = structure;
    }

    public Bitmap getImage()
    {
        return mImage;
    }

    public void setImage(Bitmap image)
    {
        mImage = image;
    }

    public String getOwnerName()
    {
        return mOwnerName;
    }

    public void setOwnerName(String ownerName)
    {
        mOwnerName = ownerName;
    }
}
