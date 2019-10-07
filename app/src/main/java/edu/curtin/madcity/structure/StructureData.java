package edu.curtin.madcity.structure;

import edu.curtin.madcity.R;

public class StructureData
{
    private static final StructureData ourInstance = new StructureData();
    public static StructureData getInstance()
    {
        return ourInstance;
    }

    public static final Residential[] RESIDENTIAL = new Residential[] {
            new Residential(R.drawable.ic_building1, R.string.ic_building1),
            new Residential(R.drawable.ic_building2, R.string.ic_building2),
            new Residential(R.drawable.ic_building3, R.string.ic_building3),
            new Residential(R.drawable.ic_building4, R.string.ic_building4)
    };

    public static final Commercial[] COMMERCIAL = new Commercial[] {
            new Commercial(R.drawable.ic_building5, R.string.ic_building5),
            new Commercial(R.drawable.ic_building6, R.string.ic_building6),
            new Commercial(R.drawable.ic_building7, R.string.ic_building7),
            new Commercial(R.drawable.ic_building8, R.string.ic_building8)
    };

    public static final Road[] ROADS = new Road[] {
            new Road(R.drawable.ic_road_n, R.string.ic_road_n), // 1000
            new Road(R.drawable.ic_road_e, R.string.ic_road_e),   // 0100
            new Road(R.drawable.ic_road_ne, R.string.ic_road_ne), // 1100
            new Road(R.drawable.ic_road_s, R.string.ic_road_s),   // 0010
            new Road(R.drawable.ic_road_ns, R.string.ic_road_ns), // 1010
            new Road(R.drawable.ic_road_se, R.string.ic_road_se),// 0110
            new Road(R.drawable.ic_road_nse, R.string.ic_road_nse), // 1110
            new Road(R.drawable.ic_road_w, R.string.ic_road_w), // 0001
            new Road(R.drawable.ic_road_nw, R.string.ic_road_nw), // 1001
            new Road(R.drawable.ic_road_ew, R.string.ic_road_ew), // 0101
            new Road(R.drawable.ic_road_new, R.string.ic_road_new), // 1101
            new Road(R.drawable.ic_road_sw, R.string.ic_road_sw), // 0011
            new Road(R.drawable.ic_road_nsw, R.string.ic_road_nsw), // 1011
            new Road(R.drawable.ic_road_sew, R.string.ic_road_sew), // 0111
            new Road(R.drawable.ic_road_nsew, R.string.ic_road_nsew) // 1111
    };

    public static final Structure[] TYPES = new Structure[] {
            new Residential(R.drawable.ic_building1,
                            R.string.residential_title),
            new Commercial(R.drawable.ic_building5,
                           R.string.commercial_title),
            new Road(R.drawable.ic_road_ew, R.string.road_title)
    };

    private StructureData()
    {

    }
}
