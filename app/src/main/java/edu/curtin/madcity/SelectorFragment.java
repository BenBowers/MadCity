package edu.curtin.madcity;

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

public class SelectorFragment extends Fragment
{
// CLASS CONSTANTS -----------------------------------------------------------

    private static final String TAG = "SelectorFragment";

    private static final SelectorItem INSPECT = new SelectorItem(
            R.string.selector_touch, R.drawable.selector_touch);

    private static final SelectorItem TOUCH = new SelectorItem(
            R.string.selector_inspect, R.drawable.selector_inspect);

    private static final SelectorItem ADD = new SelectorItem(
            R.string.selector_add, R.drawable.selector_add);

    private static final SelectorItem REMOVE = new SelectorItem(
            R.string.selector_remove, R.drawable.selector_remove);

    private static final SelectorItem[] ITEMS = {
            INSPECT,
            TOUCH,
            ADD,
            REMOVE,
    };

// PRIVATE CLASS FIELDS ------------------------------------------------------

    private RecyclerView mRecyclerView;
    private SelectorAdaptor mAdapter = new SelectorAdaptor();
    private int mSelectedPos = -1;

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

// PRIVATE METHODS -----------------------------------------------------------
    
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
