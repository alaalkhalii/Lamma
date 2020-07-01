package com.example.lamma.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.lamma.Model.Upload;
import com.example.lamma.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class addSeries extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Button mChooseImage;
    private Button mUploadBtn;
    private EditText mEditTextSeriesName;
    private EditText mEditTextYear;
    private EditText mEditTextNumOfSeasons;
    private RatingBar mRatingBar;
    private CheckBox mActionCheckB;
    private CheckBox mHorrorCheckB;
    private CheckBox mAdventureCheckB;
    private CheckBox mComedyCheckB;
    private CheckBox mThrillerCheckB;
    private CheckBox mDramaCheckB;
    private CheckBox mScienceCheckB;
    private CheckBox mRomanticCheckB;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private Uri mImageUri;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;
    private int counter=0;
    String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_series);

        mChooseImage=findViewById(R.id.UploadPosterBtn);
        mUploadBtn = findViewById(R.id.AddMovieBtn);
        mEditTextSeriesName = findViewById(R.id.SeriesNameEditTxt);
        mEditTextYear = findViewById(R.id.YearEditTxt);
        mEditTextNumOfSeasons = findViewById(R.id.numOfSeasonsEditTxt);
        mRatingBar = findViewById(R.id.ratingBar);
        mActionCheckB = findViewById(R.id.ActionCB);
        mHorrorCheckB = findViewById(R.id.HorrorCB);
        mAdventureCheckB = findViewById(R.id.AdventureCB);
        mComedyCheckB = findViewById(R.id.ComedyCB);
        mThrillerCheckB = findViewById(R.id.ThrillerCB);
        mDramaCheckB = findViewById(R.id.DramaCB);
        mScienceCheckB = findViewById(R.id.ScienceFictionCB);
        mRomanticCheckB = findViewById(R.id.RomanticCB);
        mImageView = findViewById(R.id.imageview);
        mProgressBar = findViewById(R.id.progress_bar);

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        mChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        mUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUploadTask != null && mUploadTask.isInProgress()){
                    Toast.makeText(addSeries.this,"Uploads in progress",Toast.LENGTH_LONG).show();

                }else {

                    reportFile();
                }
            }
        });
    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData()!= null){
            mImageUri = data.getData();
            Picasso.with(this).load(mImageUri).into(mImageView);
        }
    }
    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void reportFile() {

        final List<String> mType = new ArrayList<>();

        if(mActionCheckB.isChecked()){
            mType.add("Action");
            counter++;
        }
        if(mHorrorCheckB.isChecked()){
            mType.add("Horror");
            counter++;
        }
        if(mAdventureCheckB.isChecked()){
            mType.add("Adventure");
            counter++;
        }
        if(mComedyCheckB.isChecked()){
            mType.add("Comedy");
            counter++;
        }
        if(mThrillerCheckB.isChecked()){
            mType.add("Thriller");
            counter++;
        }
        if(mDramaCheckB.isChecked()){
            mType.add("Drama");
            counter++;
        }
        if(mScienceCheckB.isChecked()){
            mType.add("Science");
            counter++;
        } if(mRomanticCheckB.isChecked()){
            mType.add("Romantic");
            counter++;
        }

        if (mImageUri != null) {
            mProgressBar.setVisibility(View.VISIBLE);
            mUploadBtn.setVisibility(View.GONE);


            final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri);
            mUploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {

                        mProgressBar.setVisibility(View.GONE);
                        mUploadBtn.setVisibility(View.VISIBLE);
                        Toast.makeText(addSeries.this,"Series Uploaded Successfully",Toast.LENGTH_SHORT).show();


                        String downloadUri = task.getResult().toString();
                        Upload submit = new Upload(
                                mEditTextSeriesName.getText().toString().trim(),mEditTextYear.getText().toString().trim(),mEditTextNumOfSeasons.getText().toString().trim(),
                                (int) mRatingBar.getRating(),mType,downloadUri,"Series");

                        String uploadId = mDatabaseRef.push().getKey();
                        mDatabaseRef.child(uploadId).setValue(submit);

                    } else {
                        // Handle failures
                        Log.e("Upload","Upload failed");
                    }
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(addSeries.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "Please select Image to upload", Toast.LENGTH_SHORT).show();
        }

    }

}
