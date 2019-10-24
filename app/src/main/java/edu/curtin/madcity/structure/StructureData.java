package edu.curtin.madcity.structure;

import edu.curtin.madcity.R;

/**
 * This class is designed to hold all the information for any existing
 * structures
 */
public class StructureData
{
    private static final StructureData ourInstance = new StructureData();
    public static StructureData getInstance()
    {
        return ourInstance;
    }

    /**
     * Array of all the resdential structures
     */
    public static final Residential[] RESIDENTIAL = new Residential[] {
            new Residential(R.drawable.ic_building1, R.string.ic_building1),
            new Residential(R.drawable.ic_building2, R.string.ic_building2),
            new Residential(R.drawable.ic_building3, R.string.ic_building3),
            new Residential(R.drawable.ic_building4, R.string.ic_building4)
    };

    /**
     * Array of all the commercial structures
     */
    public static final Commercial[] COMMERCIAL = new Commercial[] {
            new Commercial(R.drawable.ic_building5, R.string.ic_building5),
            new Commercial(R.drawable.ic_building6, R.string.ic_building6),
            new Commercial(R.drawable.ic_building7, R.string.ic_building7),
            new Commercial(R.drawable.ic_building8, R.string.ic_building8)
    };

    /**
     * Individual road structure for displaying in menu
     */
    public static final Road ROAD = new Road(R.drawable.ic_road_nsew,
                                             R.string.road_title);

    /**
     * Array of drawables for roads
     */
    public static final int[] ROADS = new int[] {
            R.drawable.ic_road, // Really really good road texture!
            R.drawable.ic_road_n,   // 1000
            R.drawable.ic_road_e,   // 0100
            R.drawable.ic_road_ne,  // 1100
            R.drawable.ic_road_s,   // 0010
            R.drawable.ic_road_ns,  // 1010
            R.drawable.ic_road_se,  // 0110
            R.drawable.ic_road_nse, // 1110
            R.drawable.ic_road_w,   // 0001
            R.drawable.ic_road_nw,  // 1001
            R.drawable.ic_road_ew,  // 0101
            R.drawable.ic_road_new, // 1101
            R.drawable.ic_road_sw,  // 0011
            R.drawable.ic_road_nsw, // 1011
            R.drawable.ic_road_sew, // 0111
            R.drawable.ic_road_nsew // 1111
    };

    /**
     * Array of the different structure types for use in the selector menu.
     */
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

    public static Structure getStructure(int structureId)
            throws IllegalArgumentException
    {
        Structure structure;
        if (structureId == 0)
        {
            structure = ROAD;
        }
        else if(structureId >= 1 && structureId < 4)
        {
            structure = RESIDENTIAL[structureId - 1];
        }
        else if (structureId < 8)
        {
            structure = COMMERCIAL[structureId - 4];
        }
        else
        {
            throw new IllegalArgumentException("InvalidId");
        }

        return structure;
    }
}
