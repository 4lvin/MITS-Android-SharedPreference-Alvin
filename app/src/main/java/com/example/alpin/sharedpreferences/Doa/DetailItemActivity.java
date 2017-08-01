package com.example.alpin.sharedpreferences.Doa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.alpin.sharedpreferences.AddDoaFragment;
import com.example.alpin.sharedpreferences.R;
import com.example.alpin.sharedpreferences.model.Doa;

import java.io.File;

import pl.aprilapps.easyphotopicker.EasyImage;

public class DetailItemActivity extends AppCompatActivity {

    private EditText etNama, etDoa, etKet;
    private ImageView imgFormDoa;
    private String path;
    private long id;


    private final String TAG = DetailDoaActivity.class.getSimpleName();
    private Doa doa = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgFormDoa = (ImageView) findViewById(R.id.img_formFile);
        etNama = (EditText) findViewById(R.id.et_judul);
        etDoa = (EditText) findViewById(R.id.et_Doa);
        etKet = (EditText) findViewById(R.id.et_keterangan);

        doa = getIntent().getParcelableExtra("doa");
        if (doa != null) {
            getSupportActionBar().setTitle("Update Data");
            etNama.setText(doa.getNama());
            etDoa.setText(doa.getDoa());
            etKet.setText(doa.getKet());
            Glide.with(DetailItemActivity.this).load(doa.getImage()).into(imgFormDoa);
        } else getSupportActionBar().setTitle("Add Data");
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    public void selectImage(View view) {
        EasyImage.openChooserWithDocuments(this, "Pilih gambar", 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new EasyImage.Callbacks() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {

            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                onPhotoReturned(imageFile);
            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                if (source == EasyImage.ImageSource.CAMERA) {
                    File photoFile = EasyImage.lastlyTakenButCanceledPhoto(DetailItemActivity.this);
                    if (photoFile != null) photoFile.delete();
                }
            }
        });
    }

    private void onPhotoReturned(File imageFile) {
        if (imageFile != null) {
            Glide.with(this)
                    .load(imageFile)
                    .crossFade()
                    .centerCrop()
                    .into(imgFormDoa);
            path = imageFile.getAbsolutePath();
            Log.d(TAG, "onPhotoReturned: " + path);
        }
    }

    public void submitSave(View view) {
        String etnama, etdoa, etket, imgDoa;

        etnama = etNama.getText().toString();
        etdoa = etDoa.getText().toString();
        etket = etKet.getText().toString();
        imgDoa = path;


        if (path == null) {
            imgDoa = doa.getImage();
        }

        if (etnama.isEmpty()) {
            etNama.setError("please insert name Doa");
            etNama.requestFocus();
            return;
        }
        if (etdoa.isEmpty()) {
            etDoa.setError("please insert Doa");
            etDoa.requestFocus();
            return;
        }
        if (etket.isEmpty()) {
            etKet.setError("please insert Keterangan");
            etKet.requestFocus();
            return;
        }
        Intent returnIntent = new Intent();
        if (doa != null) {
            returnIntent.putExtra("data_update", new Doa(etnama, etdoa, etket, imgDoa));
            setResult(AddDoaFragment.RESULT_UPDATE, returnIntent);
        } else {
            returnIntent.putExtra("data_add", new Doa(etnama, etdoa, etket, path));
            setResult(AddDoaFragment.RESULT_ADD, returnIntent);
        }
        finish();
    }

    public void submitCancel(View view) {
        finish();
    }
}


