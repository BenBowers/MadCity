package edu.curtin.madcity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.curtin.madcity.structure.Structure;
import edu.curtin.madcity.structure.StructureData;

import static android.app.Activity.RESULT_OK;
import static edu.curtin.madcity.StructureSelector.STRUCTURE_EXTRA;

public class SelectorFragment extends Fragment
{
// CLASS CONSTANTS -----------------------------------------------------------

    /**
     * Enum to identify the current button that's selected
     */
    private enum SelectedItem{
        INSPECT,
        TOUCH,
        ADD,
        REMOVE
    }

    private static final String TAG = "Selector";

    private static final String DIALOG_NUMBER = "DialogNumber";

    private static final String KEY_SELECTED = "selected";

    private static final int REQUEST_STRUCTURE_TYPE = 0;
    private static final int REQUEST_RESIDENTIAL = 1;
    private static final int REQUEST_COMMERCIAL = 2;

    private final GameData GAME_DATA = GameData.getInstance();

    private Structure mStructure;
    private SelectedItem mSelectedItem = SelectedItem.TOUCH; // This is
    // done to avoid null pointer exception!

    private Button mInspectButton;
    private Button mTouchButton;
    private Button mAddButton;
    private Button mRemoveButton;

    public SelectorFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
        {
            mSelectedItem = (SelectedItem) savedInstanceState
                            .getSerializable(KEY_SELECTED);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreateView() called");
        View view  = inflater.inflate(R.layout.selector_fragment,
                                      container, false);

        // Get references to UI elements
        mInspectButton = view.findViewById(R.id.inspect_button);
        mTouchButton = view.findViewById(R.id.touch_button);
        mAddButton = view.findViewById(R.id.add_button);
        mRemoveButton = view.findViewById(R.id.remove_button);

        // Add listeners to the buttons
        mInspectButton.setOnClickListener(this::inspectClicked);
        mTouchButton.setOnClickListener(this::touchClicked);
        mAddButton.setOnClickListener(this::addClicked);
        mRemoveButton.setOnClickListener(this::removeClicked);

        setSelectedItem(mSelectedItem);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null)
        {
            switch (requestCode)
            {
                case REQUEST_STRUCTURE_TYPE:
                    Log.d(TAG, "Structure Type Received");
                    selectStructure(
                            data.getIntExtra(STRUCTURE_EXTRA, 0));
                    break;
                case REQUEST_RESIDENTIAL:
                    Log.d(TAG, "Residential received");
                    structureReceived(REQUEST_RESIDENTIAL, data);
                    break;
                case REQUEST_COMMERCIAL:
                    Log.d(TAG, "Commercial received");
                    structureReceived(REQUEST_COMMERCIAL, data);
                    break;
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_SELECTED, mSelectedItem);
    }

    private void structureReceived(int type, Intent data)
    {
        int loc = data.getIntExtra(STRUCTURE_EXTRA, 0);
        mStructure = getType(type)[loc];
    }

    private Structure[] getType(int type)
    {
        Structure[] arr;
        switch (type)
        {
            case REQUEST_RESIDENTIAL:
                arr = StructureData.RESIDENTIAL;
                break;
            case REQUEST_COMMERCIAL:
                arr = StructureData.COMMERCIAL;
                break;
            default:
                throw new IllegalArgumentException("Invalid type");
        }

        return arr;
    }

    private void showList(int code)
    {
        StructureSelector selector =
                new StructureSelector(
                        getType(code));

        selector.setTargetFragment(SelectorFragment.this,
                                   code);

        selector.show(getFragmentManager(), DIALOG_NUMBER);
    }

    private void touchClicked(View view)
    {
        Log.d(TAG, "touchClicked() called");
        setSelectedItem(SelectedItem.TOUCH);
    }

    private void inspectClicked(View view)
    {
        Log.d(TAG, "inspectClicked() called");
        setSelectedItem(SelectedItem.INSPECT);

    }

    private void addClicked(View view)
    {
        Log.d(TAG, "addClicked() called");
        setSelectedItem(SelectedItem.ADD);


        StructureSelector selector = new StructureSelector();
        selector.setTargetFragment(SelectorFragment.this,
                                   REQUEST_STRUCTURE_TYPE);
        selector.show(getFragmentManager(), DIALOG_NUMBER);
    }

    private void removeClicked(View view)
    {
        Log.d(TAG, "removeClicked() called");
        setSelectedItem(SelectedItem.REMOVE);
    }

    private void selectStructure(int type)
    {
        switch (type)
        {
            case 0:

                showList(REQUEST_RESIDENTIAL);
                break;
            case 1:
                showList(REQUEST_COMMERCIAL);

                break;
            case 2:
                mStructure = StructureData.ROAD;
                break;
            default:
                throw new IllegalArgumentException("invalid type");
        }
    }

    public void addStructure(int x, int y)
    {
        try
        {
            GAME_DATA.addStructure(getContext(), mStructure, x, y);
        }
        catch (IllegalStateException e)
        {
            Toast.makeText(getContext(), R.string.structure_warning,
                           Toast.LENGTH_LONG).show();
        }
        catch (InsufficientFundsException e)
        {
            Toast.makeText(getContext(), R.string.insufficient_funds,
                           Toast.LENGTH_LONG).show();
        }
    }

    private void inspectItem(int x, int y)
    {
        Intent intent = MapDetailsActivity.newIntent(getContext(), x, y);
        startActivity(intent); //TODO: write it to return data has
        // changed
    }

    public void removeStructure(int x, int y)
    {
        GAME_DATA.removeStructure(x, y);
    }

    public boolean itemClicked(int x, int y)
    {
        boolean dataChanged = true;

        switch (mSelectedItem)
        {
            case TOUCH:
                dataChanged = false;
                break;
            case INSPECT:
                dataChanged = false;
                inspectItem(x, y);
                break;
            case ADD:
                if (mStructure != null)
                {
                    addStructure(x, y);
                }
                break;

            case REMOVE:
                removeStructure(x, y);
                break;
            default:
                dataChanged = false;
                break;
        }

        return dataChanged;
    }

    private void setSelectedItem(SelectedItem x)
    {

        getButton(mSelectedItem).setBackgroundTintList(ColorStateList.valueOf(
                getResources().getColor(R.color.not_selected, null)));

        getButton(x).setBackgroundTintList(ColorStateList.valueOf(
            getResources().getColor(R.color.selected, null)));
        mSelectedItem = x;
    }

    private Button getButton(@NonNull  SelectedItem x)
    {
        Button button = null;
        switch (x)
        {
            case INSPECT: button = mInspectButton; break;
            case TOUCH: button = mTouchButton; break;
            case ADD: button = mAddButton; break;
            case REMOVE: button = mRemoveButton; break;
        }
        return button;
    }
}
