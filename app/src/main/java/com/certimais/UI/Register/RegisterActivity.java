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
import android.widget.TextView;

import com.certimais.API.User.UserService;
import com.certimais.Consts.AppConsts;
import com.certimais.Interfaces.APICallback;
import com.certimais.Models.RegisterInfo;
import com.certimais.R;
import com.certimais.Utils.AppUtils;

import org.w3c.dom.Text;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

    UserService mUserService;

    Uri mImgUri;
    Bitmap mBitmap;
    CircleImageView mIvCoverPhoto;
    Button mBtnRegister;

    TextView mEdtName;
    TextView mEdtEmail;
    TextView mEdtPassword;
    TextView mEdtRepeatPassword;


    View.OnClickListener coverPhotoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pickPhoto();
        }
    };

    View.OnClickListener registerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            registerUser();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUserService = UserService.getInstance(this);

        mEdtName = (TextView) findViewById(R.id.edtName);
        mEdtEmail = (TextView) findViewById(R.id.edtEmail);
        mEdtPassword = (TextView) findViewById(R.id.edtPassword);
        mEdtRepeatPassword = (TextView) findViewById(R.id.edtRepeatPassword);

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

    public void registerUser() {

        String name = mEdtName.getText().toString();
        String email = mEdtEmail.getText().toString();
        String password = mEdtPassword.getText().toString();
        String repeatPassword = mEdtRepeatPassword.getText().toString();

        if (!password.equals(repeatPassword)) {
            Log.d("REGISTER", "SENHAS DIFERENTES");
        } else {
            AppUtils.showLoadingDialog(this);
            RegisterInfo registerInfo = new RegisterInfo(name, "", email, password);
            mUserService.registerUser(registerInfo, new APICallback() {
                @Override
                public void onSuccess(Object response) {
                    AppUtils.hideDialog();
                }

                @Override
                public void onError(String message) {
                    AppUtils.hideDialog();
                }
            });

        }
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
