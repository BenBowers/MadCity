package edu.curtin.madcity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import edu.curtin.madcity.structure.Commercial;
import edu.curtin.madcity.structure.Residential;
import edu.curtin.madcity.structure.Road;
import edu.curtin.madcity.structure.Structure;

public class MapDetailsActivity extends AppCompatActivity
{
// CLASS CONSTANTS -----------------------------------------------------------

    private static final String TAG = "MapDetailsActivity";
    private static final String X_EXTRA = "X_EXTRA";
    private static final String Y_EXTRA = "Y_EXTRA";

    private static final int REQUEST_THUMBNAIL = 1;

    /**
     * Listener for the edit text of the name of the structure.
     */
    private final TextWatcher TEXT_LISTENER = new TextWatcher()
    {
        @Override
        public void beforeTextChanged(CharSequence charSequence,
                                      int i, int i1, int i2)
        {

        }

        @Override
        public void onTextChanged(CharSequence charSequence,
                                  int i, int i1, int i2)
        {

        }

        @Override
        public void afterTextChanged(@NonNull Editable editable)
        {
            Log.d(TAG, "afterTextChanged() called");
            mMapElement.setOwnerName(editable.toString());
        }
    };

// PRIVATE CLASS FIELDS ------------------------------------------------------

    private MapElement mMapElement;

    private TextView mXTextView;
    private TextView mYTextView;
    private TextView mStructureTextView;
    private ImageView mImageView;
    private EditText mNameEditText;
    private ImageButton mCameraButton;


// OVERRIDE METHODS ----------------------------------------------------------


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate() called");

        int xLoc;
        int yLoc;
        Structure structure;
        Intent intent = getIntent();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_details);


        // Process the intent extras

        xLoc = intent.getIntExtra(X_EXTRA, -1);
        yLoc = intent.getIntExtra(Y_EXTRA, -1);
        mMapElement = GameData.getInstance().mMap[xLoc][yLoc];
        structure = mMapElement.getStructure();

        //Get references to UI elements

        mXTextView = findViewById(R.id.x_text_view);
        mYTextView = findViewById(R.id.y_text_view);
        mStructureTextView = findViewById(R.id.structure_text_view);
        mImageView = findViewById(R.id.details_image_view);
        mNameEditText = findViewById(R.id.details_name_edit_text);
        mCameraButton = findViewById(R.id.camera_button);

        // Set the details of the ui elements.

        mXTextView.setText(Integer.toString(xLoc));
        mYTextView.setText(Integer.toString(yLoc));
        mStructureTextView.setText(getStructureType(structure));


            //Check if the map element has a bit map set it to that
        if( mMapElement.getImage() != null)
        {
            mImageView.setImageBitmap(mMapElement.getImage());
        }
        else // if not set it to the drawable.
        {
            mImageView.setImageResource(structure.getDrawableId());
        }

        if( mMapElement.getOwnerName() != null)
        {
            mNameEditText.setText(mMapElement.getOwnerName());
        }

        // On Click Listeners
        mNameEditText.addTextChangedListener(TEXT_LISTENER);
        mCameraButton.setOnClickListener(this::cameraOnClick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data)
    {
        Log.d(TAG, "onActivityResult() called");
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK )
        {
            if( requestCode == REQUEST_THUMBNAIL)
            {
                Log.d(TAG, "retrieving thumbnail");

                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                mMapElement.setImage(thumbnail);
                mImageView.setImageBitmap(thumbnail);
            }
        }
    }

    private void cameraOnClick(View v)
    {
        Log.d(TAG, "cameraOnClick() called");

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_THUMBNAIL);
    }


// PUBLIC STATIC METHODS -----------------------------------------------------

    /**
     * Creates a new intent to start this activity with.
     * @param packageContext context of the package starting the activity
     * @param x x coordinate of the map location
     * @param y y coordinate of the map location
     * @return intent to start the activity with.
     * @throws IllegalArgumentException inputted coordinates don't
     * correspond to a map element.
     */
    public static Intent newIntent(Context packageContext, int x, int y)
            throws IllegalArgumentException
    {
        Intent intent;

        // Throw exception if the map element doesn't exist
        if (GameData.getInstance().mMap[x][y] == null)
        {
            throw new IllegalArgumentException("Invalid location");
        }

        intent = new Intent(packageContext, MapDetailsActivity.class);
        intent.putExtra(X_EXTRA, x);
        intent.putExtra(Y_EXTRA, y);

        return intent;
    }

// PRIVATE STATIC METHODS ----------------------------------------------------

    /**
     * Returns a string resource id based on the inputted structure.
     * @param structure structure input
     * @return string resource id.
     * @throws IllegalArgumentException Couldn't resolve type.
     */
    private static int getStructureType(Structure structure)
            throws IllegalArgumentException
    {
        int stringId;

        if (structure instanceof Road)
        {
            stringId = R.string.road_title;
        }
        else if(structure instanceof Residential)
        {
            stringId = R.string.residential_title;
        }
        else if (structure instanceof Commercial)
        {
            stringId = R.string.commercial_title;
        }
        else
        {
            throw new IllegalArgumentException(
                    "Couldn't find structure type");
        }

        return stringId;
    }
}
