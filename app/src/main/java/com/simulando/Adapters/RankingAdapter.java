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
import com.simulando.Models.RankingRow;
import com.simulando.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Luciano José on 12/02/2017.
 */

public class RankingAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<RankingRow> mRanking;

    public RankingAdapter(Context context, ArrayList<RankingRow> ranking) {
        this.mContext = context;
        this.mRanking = ranking;
    }

    public void updateList(ArrayList<RankingRow> ranking){
        this.mRanking = ranking;
    };
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
        RankingRow rankingRow = (RankingRow) getItem(position);

        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.ranking_item_layout, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        if (position % 2 == 0) {
            view.setBackgroundColor(Color.WHITE);
        } else {
            view.setBackgroundColor(mContext.getResources().getColor(R.color.light_gray));
        }

        if (rankingRow.getPosition() <= 3) {
            holder.mTvPosition.setTypeface(null, Typeface.BOLD);
            holder.mTvPoints.setTypeface(null, Typeface.BOLD);
            holder.mTvProfileName.setTypeface(null, Typeface.BOLD);

            holder.mTvPosition.setTextSize(16);
            holder.mTvPoints.setTextSize(16);
            holder.mTvProfileName.setTextSize(16);
        } else {
            holder.mTvPosition.setTypeface(null, Typeface.NORMAL);
            holder.mTvPoints.setTypeface(null, Typeface.NORMAL);
            holder.mTvProfileName.setTypeface(null, Typeface.NORMAL);

            holder.mTvPosition.setTextSize(14);
            holder.mTvPoints.setTextSize(14);
            holder.mTvProfileName.setTextSize(14);
        }

        holder.mTvPosition.setText(rankingRow.position + "º");
        holder.mTvProfileName.setText(rankingRow.name);
        holder.mTvPoints.setText(String.valueOf(rankingRow.points));

        Glide.with(mContext)
                .load(rankingRow.profilePicture)
                .placeholder(R.drawable.ic_student)
                .error(R.drawable.ic_student)
                .override(30, 30)
                .fitCenter()
                .dontAnimate()
                .into(holder.mIvProfilePicture);
        return view;
    }

    public class ViewHolder {

        final TextView mTvPosition;
        final CircleImageView mIvProfilePicture;
        final TextView mTvProfileName;
        final TextView mTvPoints;


        public ViewHolder(View view) {

            mTvPosition = (TextView) view.findViewById(R.id.position);
            mIvProfilePicture = (CircleImageView) view.findViewById(R.id.profilePicture);
            mTvProfileName = (TextView) view.findViewById(R.id.profileName);
            mTvPoints = (TextView) view.findViewById(R.id.points);
        }
    }

}