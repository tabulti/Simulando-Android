package com.simulando.UI.Register;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.simulando.API.User.StudentService;
import com.simulando.Consts.AppConsts;
import com.simulando.Consts.LoginConsts;
import com.simulando.Interfaces.Callback;
import com.simulando.Manager.SessionManager;
import com.simulando.Models.Session;
import com.simulando.Models.UserAuthInfo;
import com.simulando.R;
import com.simulando.Utils.AppUtils;
import com.simulando.Utils.CommonUtils;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

    StudentService mStudentService;
    SessionManager mSessionManager;
    String language;

    Uri mImgUri;
    Bitmap mBitmap;
    CircleImageView mIvCoverPhoto;
    Button mBtnRegister;

    Switch mSwEnglish;
    Switch mSwSpanish;
    TextView mEdtName;
    TextView mEdtEmail;
    TextView mEdtPassword;
    TextView mEdtRepeatPassword;

    CoordinatorLayout mCoordinatorLayout;

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

    CompoundButton.OnCheckedChangeListener mSwitchListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (buttonView.getId() == R.id.englishLanguage) {
                if (isChecked) {
                    language = "ENGLISH";
                    mSwSpanish.setChecked(false);
                } else {
                    language = "SPANISH";
                    mSwSpanish.setChecked(true);
                }

            } else {
                if (isChecked) {
                    language = "SPANISH";
                    mSwEnglish.setChecked(false);
                } else {
                    language = "ENGLISH";
                    mSwEnglish.setChecked(true);
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mStudentService = StudentService.getInstance(this);
        mSessionManager = SessionManager.getInstance(this);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        mEdtName = (TextView) findViewById(R.id.edtName);
        mEdtEmail = (TextView) findViewById(R.id.edtEmail);
        mEdtPassword = (TextView) findViewById(R.id.edtPassword);
        mEdtRepeatPassword = (TextView) findViewById(R.id.edtRepeatPassword);
        mSwEnglish = (Switch) findViewById(R.id.englishLanguage);
        mSwSpanish = (Switch) findViewById(R.id.spanishLanguage);

        mSwEnglish.setOnCheckedChangeListener(mSwitchListener);
        mSwSpanish.setOnCheckedChangeListener(mSwitchListener);
        mSwEnglish.setChecked(true);


       /* mIvCoverPhoto = (CircleImageView) findViewById(R.id.coverPhoto);
        mIvCoverPhoto.setImageResource(R.drawable.ic_pick_photo);
        mIvCoverPhoto.setOnClickListener(coverPhotoListener);
        */

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
            AppUtils.showSnackBar(mCoordinatorLayout, R.string.different_passwords);
        } else {
            CommonUtils.showLoadingDialog(this);
            UserAuthInfo userAuthInfo = new UserAuthInfo(false, name, "", email, password,
                    "", language);
            mStudentService.registerUser(userAuthInfo, new Callback() {
                @Override
                public void onSuccess(Object response) {
                    Session session = (Session) response;
                    mSessionManager.setCurrentUser(session.user, LoginConsts.SESSION_LOGIN_API, session.token);
                    AppUtils.goToDashboard(RegisterActivity.this);
                    CommonUtils.hideDialog();
                }

                @Override
                public void onError(String message) {
                    CommonUtils.hideDialog();
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
