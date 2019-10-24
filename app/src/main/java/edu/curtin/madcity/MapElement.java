package edu.curtin.madcity;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import edu.curtin.madcity.structure.Residential;
import edu.curtin.madcity.structure.Road;
import edu.curtin.madcity.structure.Structure;
import edu.curtin.madcity.structure.StructureData;

/**
 * Class holding all the data of an individual map element
 */
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

    /**
     * Returns the bitmap image in the form of a byte array
     * @return
     */
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

    /**
     * Sets the name to the default one of the structure type
     */
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
}
