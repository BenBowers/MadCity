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
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.curtin.madcity.structure.Structure;
import edu.curtin.madcity.structure.StructureData;

import static android.app.Activity.RESULT_OK;

/**
 * Used for selecting the structure to place down
 */
public class StructureSelector extends DialogFragment
{
// CLASS CONSTANTS -----------------------------------------------------------

    private static final String TAG = "StructureSelector";
    public static final String STRUCTURE_EXTRA = "edu.curtin.madcity" +
            ".StructureSelector.structure";

// CLASS FIELDS --------------------------------------------------------------

    private final Structure[] ARR;

    private StructureAdaptor mStructureAdaptor = new StructureAdaptor();

// CONSTRUCTORS --------------------------------------------------------------

    public StructureSelector()
    {
        ARR = StructureData.TYPES;
    }

    public StructureSelector(Structure[] arr)
    {
        ARR = arr;
    }

// OVERRIDE METHODS ----------------------------------------------------------

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreateView() called");

        View v = getLayoutInflater().inflate(R.layout.structure_selector,
                                             container);
        RecyclerView recyclerView =
                v.findViewById(R.id.structure_selector_recycler_view);
        recyclerView.setLayoutManager
                (new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mStructureAdaptor);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(recyclerView.getContext(),
                                          DividerItemDecoration.HORIZONTAL));

        return v;
    }

// PRIVATE CLASSES -----------------------------------------------------------

    private class StructureAdaptor
            extends RecyclerView.Adapter<StructureHolder>
    {
        @NonNull
        @Override
        public StructureHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                  int viewType)
        {
            Log.d(TAG, "onCreateViewHolder() called");

            LayoutInflater layoutInflater =
                    LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.structure_cell,
                                               parent, false);
            return new StructureHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull StructureHolder holder,
                                     int position)
        {
            holder.bind(position);
        }

        @Override
        public int getItemCount()
        {
            return ARR.length;
        }
    }// StructureAdaptor.class

    private class StructureHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
    // PRIVATE CLASS FIELDS --------------------------------------------------

        private int mPos;
        private ImageView mImageView;
        private TextView mTextView;

     // CONSTRUCTOR ----------------------------------------------------------

        public StructureHolder(@NonNull View itemView)
        {
            super(itemView);
            mImageView = itemView.findViewById(R.id.structure_image_view);
            mTextView = itemView.findViewById(R.id.structure_text_view);

            itemView.setClickable(false);
        }

     // PUBLIC METHODS -------------------------------------------------------

        public void bind(int pos)
        {
            mPos = pos;
            Structure structure = ARR[pos];
            mImageView.setImageResource(structure.getDrawableId());
            mTextView.setText(structure.getName());

            itemView.setOnClickListener(this);
            itemView.setClickable(true);
        }

        @Override
        public void onClick(View view)
        {
            Log.d(TAG, "Structure Selected");
            Fragment target = getTargetFragment();

            if (target != null)
            {
                Intent intent = new Intent();
                intent.putExtra(STRUCTURE_EXTRA, mPos);
                target.onActivityResult(getTargetRequestCode(), RESULT_OK,
                                        intent);
                getDialog().dismiss();
            }
        }
    }//StructureHolder.class
} //StructureSelector.class
