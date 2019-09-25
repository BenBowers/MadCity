package edu.curtin.madcity.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.curtin.madcity.database.DbSchema.GameDataTable;
import edu.curtin.madcity.database.DbSchema.MapElementTable;
import edu.curtin.madcity.database.DbSchema.SettingsTable;

public class DbHelper extends SQLiteOpenHelper
{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "mad_city.db";

    public DbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        // CREATE SETTINGS TABLE
        sqLiteDatabase.execSQL
                (
                        "CREATE TABLE " + SettingsTable.NAME + "(" +
                                SettingsTable.Cols.MAP_WIDTH + " INTEGER, " +
                                SettingsTable.Cols.MAP_HEIGHT + " INTEGER, " +
                                SettingsTable.Cols.INITIAL_MONEY + " INTEGER, " +
                                SettingsTable.Cols.FAMILY_SIZE + " INTEGER, " +
                                SettingsTable.Cols.SHOP_SIZE + " INTEGER, " +
                                SettingsTable.Cols.SALARY + " INTEGER, " +
                                SettingsTable.Cols.TAX_RATE + " REAL, " +
                                SettingsTable.Cols.SERVICE_COST + " INTEGER, " +
                                SettingsTable.Cols.HOUSE_BUILDING_COST + " INTEGER, " +
                                SettingsTable.Cols.COMM_BUILDING_COST + " INTEGER, " +
                                SettingsTable.Cols.ROAD_BUILDING_COST + " INTEGER)"
                );

        // CREATE GAME TABLE
        sqLiteDatabase.execSQL
                (
                        "CREATE TABLE " + GameDataTable.NAME + "(" +
                                GameDataTable.Cols.MONEY + " INTEGER, " +
                                GameDataTable.Cols.TIME + " INTEGER)"
                );

        // CREATE MAP ELEMENT TABLE
        sqLiteDatabase.execSQL
                (
                        " CREATE TABLE " + MapElementTable.NAME + "(" +
                                MapElementTable.Cols.X_LOC + " INTEGER, " +
                                MapElementTable.Cols.Y_LOC + " INTEGER, " +
                                MapElementTable.Cols.STRUCTURE + " INTEGER, " +
                                MapElementTable.Cols.IMAGE + " BLOB, " +
                                MapElementTable.Cols.OWNER_NAME + " TEXT " +
                                "PRIMARY KEY (" + MapElementTable.Cols.X_LOC + " " + MapElementTable.Cols.Y_LOC + ")" +
                                ")"
                );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }
}
