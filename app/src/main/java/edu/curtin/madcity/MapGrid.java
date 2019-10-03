package edu.curtin.madcity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.curtin.madcity.settings.Settings;

public class MapGrid extends Fragment
{
// CLASS CONSTANTS -----------------------------------------------------------

    private static final String TAG = "MapGrid";

    /**
     * Default setting for testing... TODO: Implement with user settings.
     */
    //public final GameData GAME_DATA = GameData.getInstance();
    //public final Settings SETTINGS = GAME_DATA.mSettings;
    public static final Settings SETTINGS = new Settings();

// CLASS FIELDS --------------------------------------------------------------

    //private  MapElement[][] mMapElements = GAME_DATA.mMap;

    private RecyclerView mRecyclerView;
    private MapAdaptor mAdaptor;

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

        View view = inflater.inflate(R.layout.map_grid, container, false);

        // SET UP RECYCLER VIEW

        mRecyclerView =
                view.findViewById(R.id.map_recycler_view);
        mAdaptor = new MapAdaptor();

        mRecyclerView.setLayoutManager(new GridLayoutManager(
                getActivity(),
                SETTINGS.getMapHeightValue(),
                GridLayoutManager.HORIZONTAL, false));

        mRecyclerView.setAdapter(mAdaptor);

        return view;
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

            return SETTINGS.getMapWidthValue() * SETTINGS.getMapHeightValue();
        }
    }

    private class MapViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        int pos;

        public MapViewHolder(@NonNull View itemView, ViewGroup parent)
        {
            super(itemView);

            // This was taken from worksheet 3 to make all the grid cells
            // equal size.

            int size = parent.getMeasuredHeight() /
                            SETTINGS.getMapHeightValue() + 1;
            ViewGroup.LayoutParams lp = itemView.getLayoutParams();
            lp.width = size;
            lp.height = size;

            itemView.setOnClickListener(this);


        }

        public void bind(int pos)
        {
            this.pos = pos;
        }

        @Override
        public void onClick(View view)
        {
            Log.d(TAG, "onClick() called for pos : " + pos);
        }
    }
}
