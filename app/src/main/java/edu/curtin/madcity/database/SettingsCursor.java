package edu.curtin.madcity.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import edu.curtin.madcity.Settings;
import edu.curtin.madcity.database.DbSchema.SettingsTable.Cols;

public class SettingsCursor extends CursorWrapper
{
    public SettingsCursor(Cursor cursor)
    {
        super(cursor);
    }

    public Settings getSettings()
    {
        return new Settings(
                getInt(getColumnIndex(Cols.MAP_WIDTH)),
                getInt(getColumnIndex(Cols.MAP_HEIGHT)),
                getInt(getColumnIndex(Cols.INITIAL_MONEY)),
                getInt(getColumnIndex(Cols.FAMILY_SIZE)),
                getInt(getColumnIndex(Cols.SHOP_SIZE)),
                getInt(getColumnIndex(Cols.SALARY)),
                getFloat(getColumnIndex(Cols.TAX_RATE)),
                getInt(getColumnIndex(Cols.SERVICE_COST)),
                getInt(getColumnIndex(Cols.HOUSE_BUILDING_COST)),
                getInt(getColumnIndex(Cols.COMM_BUILDING_COST)),
                getInt(getColumnIndex(Cols.ROAD_BUILDING_COST))
        );
    }

}
