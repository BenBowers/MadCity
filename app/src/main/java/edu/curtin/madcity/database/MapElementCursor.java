package edu.curtin.madcity.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import edu.curtin.madcity.MapElement;
import edu.curtin.madcity.database.DbSchema.MapElementTable.Cols;

/**
 * Cursor for a map element use get x and get y for the coordinates stored
 */
public class MapElementCursor extends CursorWrapper
{

    public MapElementCursor(Cursor cursor)
    {
        super(cursor);
    }

    public MapElement get()
    {
        return new MapElement(
                getInt(getColumnIndex(Cols.STRUCTURE)),
                getBitmap(getBlob(getColumnIndex(Cols.IMAGE))),
                getString(getColumnIndex(Cols.OWNER_NAME))
        );
    }

    public int getX()
    {
        return getInt(getColumnIndex(Cols.X_LOC));
    }

    public int getY()
    {
        return getInt(getColumnIndex(Cols.Y_LOC));
    }


    /**
     * Function to decode a bitmap image will return null if there is none
     * @param b
     * @return
     */
    private static Bitmap getBitmap(byte[] b)
    {
        return b == null ? null :
                BitmapFactory.decodeByteArray(b, 0, b.length);
    }

}

