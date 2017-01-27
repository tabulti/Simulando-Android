package com.simulando.UI.Dashboard.Profile;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.simulando.Manager.SessionManager;
import com.simulando.Models.User;
import com.simulando.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {


    SessionManager mSessionManager;

    CircleImageView mIvProfilePicture;
    TextView mTvUserName;

    public ProfileFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mSessionManager = SessionManager.getInstance(view.getContext());
        User currentUser = mSessionManager.getCurrentUser();

        mIvProfilePicture = (CircleImageView) view.findViewById(R.id.profilePicture);
        mTvUserName = (TextView) view.findViewById(R.id.userName);

        mTvUserName.setText(currentUser.nome);
        Glide.with(this)
                .load(currentUser.photo)
                .override(250, 250)
                .dontAnimate()
                .centerCrop()
                .into(mIvProfilePicture);

        return view;
    }

}
