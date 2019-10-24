package edu.curtin.madcity.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import edu.curtin.madcity.GameData;
import edu.curtin.madcity.database.DbSchema.GameDataTable.Cols;
/**
 * Cursor class for all the game data contained in the database
 */
public class GameDataCursor extends CursorWrapper
{
    public GameDataCursor(Cursor cursor)
    {
        super(cursor);
    }

    public void get(GameData gameData) //cannot create a new game data as
    // it is a singleton
    {
        gameData.setGameTime(getInt(getColumnIndex(Cols.TIME)));
        gameData.setMoney(getInt(getColumnIndex(Cols.MONEY)));
        gameData.setNumCommercial(getInt(getColumnIndex(Cols.COMM_NUM)));
        gameData.setNumResidential(getInt(getColumnIndex(Cols.RES_NUM)));
    }
}
