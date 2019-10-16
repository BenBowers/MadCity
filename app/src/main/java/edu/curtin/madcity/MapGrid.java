package edu.curtin.madcity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.curtin.madcity.settings.Settings;
import edu.curtin.madcity.structure.Road;
import edu.curtin.madcity.structure.Structure;
import edu.curtin.madcity.structure.StructureData;


public class MapGrid extends Fragment
{
// CLASS CONSTANTS -----------------------------------------------------------

    /**
     * Tag for debugging
     */
    private static final String TAG = "MapGrid";

    /**
     * Reference to game data
     */
    public final GameData GAME_DATA = GameData.getInstance();

    /**
     * Reference of the settings to use
     */
    public final Settings SETTINGS = GAME_DATA.SETTINGS;

// CLASS FIELDS --------------------------------------------------------------

    /**
     * Adaptor for the recycler view
     */
    private MapAdaptor mAdaptor;

    /**
     * Selector to send the onClick data to.
     */
    private SelectorFragment mSelector;


    /**
     * Constructor for the map grid
     * @param selectorFragment The selector to send the on click data to.
     */
    public MapGrid(SelectorFragment selectorFragment)
    {
        mSelector = selectorFragment;
    }

    public MapGrid()
    {

    }

// OVERRIDE METHODS ----------------------------------------------------------


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreateView() called");
        RecyclerView recyclerView;

        View view = inflater.inflate(R.layout.map_grid, container, false);

        // SET UP RECYCLER VIEW

        recyclerView =
                view.findViewById(R.id.map_recycler_view);
        mAdaptor = new MapAdaptor();

        recyclerView.setLayoutManager(new GridLayoutManager(
                getActivity(),
                SETTINGS.MAP_HEIGHT.getValue(),
                GridLayoutManager.HORIZONTAL, false));

        recyclerView.setAdapter(mAdaptor);

        return view;
    }

    private void updateUI(int pos)
    {
        mAdaptor.notifyItemChanged(pos);
    }

    /**
     * Gets the appropriate road drawable depending on the surrounding
     * structures.
     * @param x x-coordinate of the structure
     * @param y y-coordinate of the structure
     * @return drawable of the road
     */
    private int getRoadDrawable(int x, int y)
    {
        /**
         * This uses a binary or to index the different road drawables
         * eg .
         * 0001 = w
         * 1001 = nw
         * 1101 = new
         * ...
         */
        int a = 0b0000;

        if (x - 1 >= 0) // IF A STRUCTURE IS NORTH OF IT
        {
            if (structureExists(x - 1, y))
            {
                a = a | 0b1000;
            }
        }

        if (y + 1 < GAME_DATA.mMap[0].length) // IF A STRUCTURE IS EAST
        {
            if (structureExists(x, y+1))
            {
                a = a | 0b0100;
            }
        }

        if (x + 1 < GAME_DATA.mMap.length) // IF A STRUCTURE IS SOUTH
        {
            if (structureExists(x + 1, y))
            {
                a = a | 0b0010;
            }
        }

        if (y - 1 >= 0) // IF A STRUCTURE IS WEST
        {
            if (structureExists(x, y - 1))
            {
                a = a | 0b0001;
            }
        }

        return StructureData.ROADS[a];
    }


    /**
     * Returns true if there is a structure located at the specified
     * coordinates
     * @param x x-coordinate
     * @param y y-coordinate
     * @return true if structure is located there false otherwise
     */
    private boolean structureExists(int x, int y)
    {
        return GAME_DATA.mMap[x][y] != null;
    }




// PRIVATE CLASSES -----------------------------------------------------------

    private class MapAdaptor extends RecyclerView.Adapter<MapViewHolder>
    {
        private static final String TAG = "MapAdaptor";

        @NonNull
        @Override
        public MapViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                int viewType)
        {
            Log.d(TAG, "onCreateViewHolder() called");

            LayoutInflater layoutInflater =
                    LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(
                    R.layout.map_cell, parent,false);


            return new MapViewHolder(view, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MapViewHolder holder,
                                     int position)
        {
            Log.d(TAG, "onBindViewHolder() called");

            holder.bind(position);

        }

        @Override
        public int getItemCount()
        {
            Log.d(TAG, "getItemCount() called");

            return SETTINGS.MAP_WIDTH.getValue() *
                    SETTINGS.MAP_HEIGHT.getValue();
        }
    }

    private class MapViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        int pos;
        MapElement mMapElement;
        ImageView mImageView;

        public MapViewHolder(@NonNull View itemView, ViewGroup parent)
        {
            super(itemView);
            makeSquare(itemView, parent);

            mImageView = itemView.findViewById(R.id.map_cell_structure);


            itemView.setOnClickListener(this);
        }

        /**
         * Binds a position to the Map view holder this will set the
         * drawable structure of the view holder
         * @param pos position of the view holder
         */
        public void bind(int pos)
        {
            Structure structure;

            this.pos = pos;

            mMapElement = GAME_DATA.mMap[getX()][getY()];

            if(mMapElement != null) // Avoid null pointer exception
            {
                if(mMapElement.getImage() == null) // If it's a drawable
                {
                    structure = mMapElement.getStructure();
                    if (structure instanceof Road)
                    {
                        mImageView.setImageResource(
                                getRoadDrawable(getX(), getY()));
                    }
                    else
                    {
                        mImageView.setImageResource(structure.getDrawableId());
                    }
                }
                else // if it's a bitmap
                {
                    mImageView.setImageBitmap(mMapElement.getImage());
                }
            }
            else // If there is no structure there set the drawable to trans.
            {
                mImageView.setImageResource(R.color.trans);
            }
        }

        @Override
        public void onClick(View view)
        {
            Log.d(TAG, "onClick() called for pos : " + pos);

            if(mSelector.itemClicked(getX(), getY()))
            {
                mAdaptor.notifyDataSetChanged();
            }
        }

        /**
         * This was taken from worksheet 3 to make all the grid cells
         * equal size
         * @param itemView
         * @param parent
         */
        private void makeSquare(@NonNull View itemView, ViewGroup parent)
        {
            int size = parent.getMeasuredHeight() /
                    SETTINGS.MAP_HEIGHT.getValue() + 1;
            ViewGroup.LayoutParams lp = itemView.getLayoutParams();
            lp.width = size;
            lp.height = size;
        }

        /**
         *
         * @return x coordinate of the view holder
         */
        private int getX()
        {
            return pos / SETTINGS.MAP_HEIGHT.getValue();
        }

        /**
         *
         * @return y-coordinate of the view holder
         */
        private int getY()
        {
            return pos % SETTINGS.MAP_HEIGHT.getValue();
        }
    }
}
