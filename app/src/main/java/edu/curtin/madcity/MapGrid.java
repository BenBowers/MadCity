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


public class MapGrid extends Fragment
{
// CLASS CONSTANTS -----------------------------------------------------------

    private static final String TAG = "MapGrid";

    public final GameData GAME_DATA = GameData.getInstance();

// CLASS FIELDS --------------------------------------------------------------



    private RecyclerView mRecyclerView;
    private MapAdaptor mAdaptor;
    private SelectorFragment mSelector;


    public MapGrid(SelectorFragment selectorFragment)
    {
        mSelector = selectorFragment;
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

        View view = inflater.inflate(R.layout.map_grid, container, false);

        // SET UP RECYCLER VIEW

        mRecyclerView =
                view.findViewById(R.id.map_recycler_view);
        mAdaptor = new MapAdaptor();

        mRecyclerView.setLayoutManager(new GridLayoutManager(
                getActivity(),
                Settings.MAP_HEIGHT.getValue(),
                GridLayoutManager.HORIZONTAL, false));

        mRecyclerView.setAdapter(mAdaptor);

        return view;
    }

    private void updateUI(int pos)
    {
        mAdaptor.notifyItemChanged(pos);
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

            return Settings.MAP_WIDTH.getValue() * Settings.MAP_HEIGHT.getValue();
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

            // This was taken from worksheet 3 to make all the grid cells
            // equal size.

            int size = parent.getMeasuredHeight() /
                            Settings.MAP_HEIGHT.getValue() + 1;
            ViewGroup.LayoutParams lp = itemView.getLayoutParams();
            lp.width = size;
            lp.height = size;
            /////////////////////////////////////////////////////////////////

            mImageView = itemView.findViewById(R.id.map_cell_structure);


            itemView.setOnClickListener(this);
        }

        public void bind(int pos)
        {
            this.pos = pos;

            mMapElement = GAME_DATA.mMap[getX()][getY()];

            if(mMapElement != null)
            {
                mImageView.setImageResource(
                        mMapElement.getStructure().getDrawableId());
            }
            else
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

        private int getX()
        {
            return pos / Settings.MAP_HEIGHT.getValue();
        }

        private int getY()
        {
            return pos % Settings.MAP_HEIGHT.getValue();
        }
    }
}
