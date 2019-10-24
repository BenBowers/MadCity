package edu.curtin.madcity.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.curtin.madcity.database.DbSchema.GameDataTable;
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
        // CREATE settings TABLE
        sqLiteDatabase.execSQL(SettingsTable.createTable());


        // CREATE GAME TABLE
        sqLiteDatabase.execSQL(GameDataTable.createTable());

        /*
        // CREATE MAP ELEMENT TABLE
        sqLiteDatabase.execSQL(MapElementTable.createTable());
        */
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }
}
