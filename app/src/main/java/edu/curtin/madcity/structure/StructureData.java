package edu.curtin.madcity.structure;

import edu.curtin.madcity.R;

public class StructureData
{
    private static final StructureData ourInstance = new StructureData();
    public static StructureData getInstance()
    {
        return ourInstance;
    }

    private static final Residential[] RESIDENTIAL = new Residential[]
            {
                    new Residential(R.drawable.ic_building1, "building 1"),
                    new Residential(R.drawable.ic_building2, "building 2"),
                    new Residential(R.drawable.ic_building3, "building 3"),
                    new Residential(R.drawable.ic_building4, "building 4")
            };

    private static final Commercial[] COMMERCIAL = new Commercial[]
            {
                    new Commercial(R.drawable.ic_building5, "building 5"),
                    new Commercial(R.drawable.ic_building6, "building 6"),
                    new Commercial(R.drawable.ic_building7, "building 7"),
                    new Commercial(R.drawable.ic_building8, "building 8")

            };

    private static final Road[] ROADS = new Road[]
            {
                    new Road(R.drawable.ic_road_ns, "Straight"),
                    new Road(R.drawable.ic_road_ew, "Straight"),
                    new Road(R.drawable.ic_road_nsew, "Cross"),
                    new Road(R.drawable.ic_road_ne, "Corner"),
                    new Road(R.drawable.ic_road_nw, "Corner"),
                    new Road(R.drawable.ic_road_se, "Corner"),
                    new Road(R.drawable.ic_road_sw, "Corner"),
                    new Road(R.drawable.ic_road_n, "Turn"),
                    new Road(R.drawable.ic_road_e, "Turn"),
                    new Road(R.drawable.ic_road_s, "Turn"),
                    new Road(R.drawable.ic_road_w, "Turn"),
                    new Road(R.drawable.ic_road_nse, "T junction"),
                    new Road(R.drawable.ic_road_nsw, "T junction"),
                    new Road(R.drawable.ic_road_new, "T junction"),
                    new Road(R.drawable.ic_road_sew, "T junction")
            };


    private StructureData()
    {

    }




}
