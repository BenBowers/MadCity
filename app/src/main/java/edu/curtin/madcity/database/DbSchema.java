package edu.curtin.madcity.database;

public class DbSchema
{
    public static class SettingsTable
    {
        public static final String NAME = "settings";

        public static class Cols
        {
            public static final String MAP_WIDTH = "map_width";
            public static final String MAP_HEIGHT = "map_height";
            public static final String INITIAL_MONEY = "initial_money";
            public static final String FAMILY_SIZE = "family_size";
            public static final String SHOP_SIZE = "shop_size";
            public static final String SALARY = "salary";
            public static final String TAX_RATE = "tax_rate";
            public static final String SERVICE_COST = "service_cost";
            public static final String HOUSE_BUILDING_COST =
                    "house_building_cost";
            public static final String COMM_BUILDING_COST =
                    "comm_building_cost";
            public static final String ROAD_BUILDING_COST =
                    "road_building_cost";
        }

        public static String createTable()
        {
            return
                    "CREATE TABLE " + SettingsTable.NAME + "(" +
                            Cols.MAP_WIDTH + " INTEGER, " +
                            Cols.MAP_HEIGHT + " INTEGER, " +
                            Cols.INITIAL_MONEY + " INTEGER, " +
                            Cols.FAMILY_SIZE + " INTEGER, " +
                            Cols.SHOP_SIZE + " INTEGER, " +
                            Cols.SALARY + " INTEGER, " +
                            Cols.TAX_RATE + " REAL, " +
                            Cols.SERVICE_COST + " INTEGER, " +
                            Cols.HOUSE_BUILDING_COST + " INTEGER, " +
                            Cols.COMM_BUILDING_COST + " INTEGER, " +
                            Cols.ROAD_BUILDING_COST + " INTEGER)";
        }
    }

    public static class GameDataTable
    {
        public static final String NAME = "game_data";

        public static class Cols
        {
            public static final String MONEY = "money";
            public static final String TIME = "time";
        }

        public static String createTable()
        {
            return
                    "CREATE TABLE " + GameDataTable.NAME + "(" +
                            GameDataTable.Cols.MONEY + " INTEGER, " +
                            GameDataTable.Cols.TIME + " INTEGER)";
        }

    }

    public static class MapElementTable
    {
        public static final String NAME = "map_elements";

        public static class Cols
        {
            public static final String X_LOC = "x_loc";
            public static final String Y_LOC = "y_loc";
            public static final String STRUCTURE = "structure";
            public static final String IMAGE = "image";
            public static final String OWNER_NAME = "owner_name";
        }

        public static String createTable()
        {
            return
                    " CREATE TABLE " + MapElementTable.NAME + "(" +
                            MapElementTable.Cols.X_LOC + " INTEGER, " +
                            MapElementTable.Cols.Y_LOC + " INTEGER, " +
                            MapElementTable.Cols.STRUCTURE + " INTEGER, " +
                            MapElementTable.Cols.IMAGE + " BLOB, " +
                            MapElementTable.Cols.OWNER_NAME + " TEXT, " +
                            "PRIMARY KEY(" + MapElementTable.Cols.X_LOC + ", " + MapElementTable.Cols.Y_LOC + ")" +
                            ")";
        }
    }


}
