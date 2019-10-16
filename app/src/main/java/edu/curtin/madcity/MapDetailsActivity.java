package edu.curtin.madcity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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

/**
 * Activity displays all the details of a map element
 */
public class MapDetailsActivity extends AppCompatActivity
{
// CLASS CONSTANTS -----------------------------------------------------------

    private static final String TAG = "MapDetailsActivity";
    private static final String X_EXTRA =
            "edu.au.curtin.madcity.mapdetails.x";
    private static final String Y_EXTRA =
            "edu.curtin.madcity.mapdetails.y";

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

    /**
     * Intent to launch the camera app.
     */
    private final Intent CAMERA_INTENT = new Intent(
            MediaStore.ACTION_IMAGE_CAPTURE);

// PRIVATE CLASS FIELDS ------------------------------------------------------

    private MapElement mMapElement;
    private ImageView mImageView;

    /**
     * Boolean representing whether the parent activity needs to update
     * it's view.
     */
    private boolean mUpdateView = false;

// OVERRIDE METHODS ----------------------------------------------------------


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate() called");

        TextView xTextView;
        TextView yTextView;
        TextView structureTextView;
        EditText nameEditText;
        ImageButton cameraButton;
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

        xTextView = findViewById(R.id.x_text_view);
        yTextView = findViewById(R.id.y_text_view);
        structureTextView = findViewById(R.id.structure_text_view);
        mImageView = findViewById(R.id.details_image_view);
        nameEditText = findViewById(R.id.details_name_edit_text);
        cameraButton = findViewById(R.id.camera_button);

        // Set the details of the ui elements.

        xTextView.setText(Integer.toString(xLoc));
        yTextView.setText(Integer.toString(yLoc));
        structureTextView.setText(getStructureType(structure));


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
            nameEditText.setText(mMapElement.getOwnerName());
        }

        // On Click Listeners
        nameEditText.addTextChangedListener(TEXT_LISTENER);

        //Check if the phone has a camera.

        PackageManager pm = getPackageManager();
        if (pm.resolveActivity(CAMERA_INTENT,
                               PackageManager.MATCH_DEFAULT_ONLY) != null)
        {
            cameraButton.setOnClickListener(this::cameraOnClick);
        }
        else
        {
            cameraButton.setEnabled(false);
        }
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
                mUpdateView = true;
            }
        }
    }

    /**
     * Methods to be called when the camera is clicked.
     * @param v View of the activity.
     */
    private void cameraOnClick(View v)
    {
        Log.d(TAG, "cameraOnClick() called");


        startActivityForResult(CAMERA_INTENT, REQUEST_THUMBNAIL);
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
}//MapDetailsActivity.class
