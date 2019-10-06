package edu.curtin.madcity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.curtin.madcity.structure.Structure;
import edu.curtin.madcity.structure.StructureData;

import static android.app.Activity.RESULT_OK;
import static edu.curtin.madcity.StructureSelector.STRUCTURE_EXTRA;

public class SelectorFragment extends Fragment
{
// CLASS CONSTANTS -----------------------------------------------------------

    private static final String TAG = "SelectorFragment";

    private static final String DIALOG_NUMBER = "DialogNumber";

    private static final int REQUEST_STRUCTURE_TYPE = 0;
    private static final int REQUEST_RESIDENTIAL = 1;
    private static final int REQUEST_COMMERCIAL = 2;
    private static final int REQUEST_ROAD = 3;

    private final GameData GAME_DATA = GameData.getInstance();


    private static final Structure[][] TYPE_ARR = new Structure[][]{
            StructureData.RESIDENTIAL,
            StructureData.COMMERCIAL,
            StructureData.ROADS
    };

    private static final int RESIDENTIAL_IDX = 0;
    private static final int COMMERCIAL_IDX  = 1;
    private static final int ROADS_IDX = 2;

    private static final SelectorItem TOUCH = new SelectorItem(
            R.string.selector_inspect, R.drawable.selector_inspect);

    private static final SelectorItem INSPECT = new SelectorItem(
            R.string.selector_touch, R.drawable.selector_touch);


    private static final SelectorItem ADD = new SelectorItem(
            R.string.selector_add, R.drawable.selector_add);

    private static final SelectorItem REMOVE = new SelectorItem(
            R.string.selector_remove, R.drawable.selector_remove);

    private static final SelectorItem[] ITEMS = {
            TOUCH,
            INSPECT,
            ADD,
            REMOVE,
    };

    private static final int TOUCH_POS = 0;
    private static final int INSPECT_POS = 1;
    private static final int ADD_POS = 2;
    private static final int REMOVE_POS = 3;

// PRIVATE CLASS FIELDS ------------------------------------------------------

    private RecyclerView mRecyclerView;
    private SelectorAdaptor mAdapter = new SelectorAdaptor();
    private int mSelectedPos = -1;

    private Structure mStructure;

// CONSTRUCTORS --------------------------------------------------------------

    public SelectorFragment()
    {
        // These have to be set at runtime because they're not static
        INSPECT.setOnClickListener(this::touchClicked);
        TOUCH.setOnClickListener(this::inspectClicked);
        ADD.setOnClickListener(this::addClicked);
        REMOVE.setOnClickListener(this::removeClicked);
    }


// OVERRIDE METHODS ----------------------------------------------------------

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreateView() called");

        View view = inflater.inflate(R.layout.selector, container, false);
        mRecyclerView = view.findViewById(R.id.selector_recycler_view);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity(),
                                        RecyclerView.HORIZONTAL, false));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(mRecyclerView.getContext(),
                                          DividerItemDecoration.HORIZONTAL));
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK)
        {
            switch (requestCode)
            {
                case REQUEST_STRUCTURE_TYPE :
                    Log.d(TAG, "Structure Type Received");
                    selectStructure(
                            data.getIntExtra(STRUCTURE_EXTRA, 0));
                    break;
                case REQUEST_RESIDENTIAL :
                    Log.d(TAG,"Residential received");
                    structureReceived(RESIDENTIAL_IDX, data);
                    break;
                case REQUEST_COMMERCIAL :
                    Log.d(TAG, "Commercial received");
                    structureReceived(COMMERCIAL_IDX, data);
                    break;
                case REQUEST_ROAD :
                    Log.d(TAG, "Road received");
                    structureReceived(ROADS_IDX, data);
            }
        }
    }

// PUBLIC METHODS ------------------------------------------------------------


    // PRIVATE METHODS -----------------------------------------------------------

    private void selectStructure(int type)
    {
        int resultType;
        StructureSelector selector =
                new StructureSelector(
                        TYPE_ARR[type]);

        switch (type)
        {
            case 0 :
                resultType = REQUEST_RESIDENTIAL;
                break;
            case 1 :
                resultType = REQUEST_COMMERCIAL;
                break;
            case 2:
                resultType = REQUEST_ROAD;
                break;
            default:
                throw new IllegalArgumentException("invalid type");
        }

        selector.setTargetFragment(SelectorFragment.this,
                                   resultType);
        selector.show(getFragmentManager(), DIALOG_NUMBER);
    }

    private void structureReceived(int type, Intent data)
    {
        int loc = data.getIntExtra(STRUCTURE_EXTRA, 0);
        mStructure = TYPE_ARR[type][loc];
    }

    private void touchClicked(View view)
    {
        Log.d(TAG, "touchClicked() called");
        mSelectedPos = 0;
        updateUI();

    }

    private void inspectClicked(View view)
    {
        Log.d(TAG, "inspectClicked() called");
        mSelectedPos  = 1;
        updateUI();
    }

    private void addClicked(View view)
    {
        Log.d(TAG, "addClicked() called");
        mSelectedPos = 2;
        updateUI();

        StructureSelector selector = new StructureSelector();
        selector.setTargetFragment(SelectorFragment.this,
                                   REQUEST_STRUCTURE_TYPE);
        selector.show(getFragmentManager(), DIALOG_NUMBER);
    }

    private void removeClicked(View view)
    {
        Log.d(TAG, "removeClicked() called");
        mSelectedPos = 3;
        updateUI();
    }

    private void updateUI()
    {
        mAdapter.notifyDataSetChanged();
    }

// PRIVATE CLASSES -----------------------------------------------------------

    private class SelectorAdaptor
            extends RecyclerView.Adapter<SelectorViewHolder>
    {
        @NonNull
        @Override
        public SelectorViewHolder onCreateViewHolder(
                @NonNull ViewGroup parent, int viewType)
        {
            Log.d(TAG, "onCreateViewHolder() called");

            LayoutInflater layoutInflater =
                    LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(
                    R.layout.selector_cell, parent,false);
            return new SelectorViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SelectorViewHolder holder,
                                     int position)
        {
            Log.d(TAG, "onBindViewHolder() called");
            holder.bind(ITEMS[position]);

            if(position == mSelectedPos)
            {
                holder.itemView.setBackgroundColor(
                        getResources().getColor(R.color.selected, null));
            }
            else
            {
                holder.itemView.setBackgroundColor(
                        getResources().getColor(R.color.trans, null));
            }
        }

        @Override
        public int getItemCount()
        {
            Log.d(TAG, "getItemCount() called");
            return ITEMS.length;
        }
    }

    private class SelectorViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        private ImageView mImageView;
        private TextView mTextView;

        private SelectorItem mSelectorItem;

        public SelectorViewHolder(@NonNull View itemView)
        {
            super(itemView);
            mImageView = itemView.findViewById(R.id.selector_image_view);
            mTextView = itemView.findViewById(R.id.selector_text_view);
            itemView.setOnClickListener(this);
        }

        public void bind(SelectorItem selectorItem)
        {
            mSelectorItem = selectorItem;
            mTextView.setText(mSelectorItem.NAME);
            mImageView.setImageResource(mSelectorItem.DRAWABLE);
        }

        @Override
        public void onClick(View view)
        {
            mSelectorItem.onClick(view);
        }
    }

    private static class SelectorItem implements View.OnClickListener
    {
        public final int NAME;
        public final int DRAWABLE;
        private View.OnClickListener ON_CLICK;

        public SelectorItem(int name, int drawable)
        {
            NAME = name;
            DRAWABLE = drawable;
        }

        public void setOnClickListener(View.OnClickListener v)
        {
            ON_CLICK = v;
        }

        @Override
        public void onClick(View view)
        {
            ON_CLICK.onClick(view);
        }
    }
}
