package edu.curtin.madcity;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import edu.curtin.madcity.structure.Residential;
import edu.curtin.madcity.structure.Road;
import edu.curtin.madcity.structure.Structure;
import edu.curtin.madcity.structure.StructureData;

public class MapElement
{
    private int mStructure;
    private Bitmap mImage;
    private String mOwnerName;

    public MapElement(int structure)
    {
        mStructure = structure;
        defaultName();
    }

    private void defaultName()
    {
        Structure s = getStructure();
        if(s instanceof Road)
        {
            mOwnerName = "Road";
        }
        else if (s instanceof Residential)
        {
            mOwnerName = "Residential";
        }
        else
        {
            mOwnerName = "Commercial";
        }
    }

    public MapElement(int structure, Bitmap image, String ownerName)
    {
        mStructure = structure;
        mImage = image;
        if(ownerName != null)
        {
            mOwnerName = ownerName;
        }
        else
        {
            defaultName();
        }
    }


    public Structure getStructure()
    {
        return StructureData.getStructure(mStructure);
    }

    public void setStructure(int structure)
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

    public int getStructureID()
    {
        return mStructure;
    }

    public byte[] getImageBytes()
    {
        byte[] arr;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        if(mImage != null)
        {
            mImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
            arr = stream.toByteArray();
            try
            {
                stream.close();
            }
            catch (IOException e)
            {
                // TODO: handle
            }
        }
        else
        {
            arr = null;
        }

        return arr;
    }
}
