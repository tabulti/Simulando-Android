package com.certimais.UI.Register;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.certimais.Consts.AppConsts;
import com.certimais.R;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

    Uri mImgUri;
    Bitmap mBitmap;
    CircleImageView mIvCoverPhoto;
    Button mBtnRegister;


    View.OnClickListener coverPhotoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pickPhoto();
        }
    };

    View.OnClickListener registerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("REGISTER", "OK");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mIvCoverPhoto = (CircleImageView) findViewById(R.id.coverPhoto);
        mIvCoverPhoto.setImageResource(R.drawable.ic_pick_photo);
        mIvCoverPhoto.setOnClickListener(coverPhotoListener);

        mBtnRegister = (Button) findViewById(R.id.btnRegister);
        mBtnRegister.setOnClickListener(registerListener);

    }

    public void pickPhoto() {
        Intent photoPicker = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker, AppConsts.PICK_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConsts.PICK_PHOTO && resultCode == RESULT_OK) {
            mImgUri = data.getData();

            try {
                mBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mImgUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (mBitmap != null) {
                mIvCoverPhoto.setImageBitmap(mBitmap);
            }

        }
    }
}
