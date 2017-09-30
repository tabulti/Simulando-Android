package com.simulando.joaopaulodribeiro.simulando.page.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.simulando.joaopaulodribeiro.simulando.R;
import com.simulando.joaopaulodribeiro.simulando.model.simulates.Test;

import java.util.List;
import java.util.Random;

/**
 * Created by joao.paulo.d.ribeiro on 30/09/2017.
 */

public class RecyclerSimulatesAdapter extends RecyclerView.Adapter<RecyclerSimulatesAdapter.SimulatesViewHolder>{

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Test> mSimulates;

    public RecyclerSimulatesAdapter(Context context, List<Test> simulates) {
        this.mContext = context;
        this.mSimulates = simulates;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public SimulatesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mLayoutInflater.inflate(R.layout.simulate_card, parent, false);

        return new SimulatesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimulatesViewHolder holder, int position) {
        holder.title.setText(mSimulates.get(position).getTitle() + " - " +
                mSimulates.get(position).getDescription());
        holder.endTime.setText("Acaba em " + mSimulates.get(position).getTest_end_date());
        holder.totalQuestions.setText(mSimulates.get(position).getTotal_questions() + " Questões - ");
        holder.endTime.setText(mSimulates.get(position).getEstimated_time() + " h");

        Random random = new Random();
        int choosen = random.nextInt(3) + 1;
        int imageId;

        switch (choosen) {
            case 1:
                imageId = R.drawable.bg01;
                break;
            case 2:
                imageId = R.drawable.bg02;
                break;
            case 3:
                imageId = R.drawable.bg03;
                break;
            default:
                imageId = R.drawable.bg01;
        }

        holder.background.setImageDrawable(ContextCompat.getDrawable(mContext, imageId));
    }

    @Override
    public int getItemCount() {
        return mSimulates.size();
    }


    /**********************************************************************************************/
    public class SimulatesViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView endDate;
        TextView totalQuestions;
        TextView endTime;
        ImageView background;

        public SimulatesViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.simulate_cv_title);
            endDate = (TextView) itemView.findViewById(R.id.simulate_cv_end_date_tv);
            totalQuestions = (TextView) itemView.findViewById(R.id.simulate_cv_details);
            endTime = (TextView) itemView.findViewById(R.id.simulate_cv_time);
            background = (ImageView) itemView.findViewById(R.id.disciplines_cv_bg);
        }
    }
}
