package com.simulando.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.simulando.Models.RankItem;
import com.simulando.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Luciano José on 12/02/2017.
 */

public class RankingAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<RankItem> mRanking;

    public RankingAdapter(Context context, ArrayList<RankItem> ranking) {
        this.mContext = context;
        this.mRanking = ranking;
    }

    @Override
    public int getCount() {
        return mRanking.size();
    }

    @Override
    public Object getItem(int position) {
        return mRanking.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mRanking.get(position).getPosition();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        ViewHolder holder;
        RankItem rankingItem = (RankItem) getItem(position);

        if( convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.ranking_item_layout, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        if(position % 2 == 0){
            view.setBackgroundColor(Color.WHITE);
        }else{
            view.setBackgroundColor(mContext.getResources().getColor(R.color.light_gray));
        }

        if(rankingItem.getPosition() <= 3){
            holder.mTvPosition.setTypeface(null, Typeface.BOLD);
            holder.mTvPosition.setTextSize(16);
        }

        holder.mTvPosition.setText(rankingItem.position + "º");
        holder.mTvProfileName.setText(rankingItem.profile_name);
        holder.mTvScore.setText(rankingItem.score);

        Glide.with(mContext)
                .load(rankingItem.picture_url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_student)
                .error(R.drawable.ic_student)
                .override(30, 30)
                .centerCrop()
                .dontAnimate()
                .into(holder.mIvProfilePicture);
        return view;
    }

    public class ViewHolder {

        final TextView mTvPosition;
        final CircleImageView mIvProfilePicture;
        final TextView mTvProfileName;
        final TextView mTvScore;


        public ViewHolder(View view) {

            mTvPosition = (TextView) view.findViewById(R.id.position);
            mIvProfilePicture = (CircleImageView) view.findViewById(R.id.profilePicture);
            mTvProfileName = (TextView) view.findViewById(R.id.profileName);
            mTvScore = (TextView) view.findViewById(R.id.score);
        }
    }

}