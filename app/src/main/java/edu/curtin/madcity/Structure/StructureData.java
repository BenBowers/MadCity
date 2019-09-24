package edu.curtin.madcity.Structure;

class StructureData
{
    private static final StructureData ourInstance = new StructureData();
    private Residential[] mResidential;
    private Commercial[] mCommercial;
    private Road[] mRoads;

    private StructureData()
    {
    }

    static StructureData getInstance()
    {
        return ourInstance;
    }
}
