package com.simulando.joaopaulodribeiro.simulando.page.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.simulando.joaopaulodribeiro.simulando.R;
import com.simulando.joaopaulodribeiro.simulando.utils.DisciplinesEnum;


/**
 * Created by joao.paulo.d.ribeiro on 27/09/2017.
 */

public class RecyclerDisciplinesAdapter extends RecyclerView.Adapter<RecyclerDisciplinesAdapter.DisciplinesViewHolder> {

    private DisciplinesEnum[] mDisciplines;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public RecyclerDisciplinesAdapter(Context context) {
        this.mDisciplines = DisciplinesEnum.values();
        this.mContext = context;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public DisciplinesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.dicipline_card, parent, false);

        return new DisciplinesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DisciplinesViewHolder holder, int position) {
        holder.cvTitle.setText(mDisciplines[position].getDisciplineName());

        int imageLocator = mDisciplines[position].getBackgroundImageId();
        holder.cvBackground.setImageDrawable(imageLocator == 0 ?
                ContextCompat.getDrawable(mContext, R.drawable.card_default_background) :
                ContextCompat.getDrawable(mContext, imageLocator));
    }


    @Override
    public int getItemCount() {
        return mDisciplines.length;
    }

    /**********************************************************************************************/

    public class DisciplinesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView cvTitle;
        public RadioButton cvRadioBtn;
        public ImageView cvBackground;

        public DisciplinesViewHolder(View itemView) {
            super(itemView);

            this.cvTitle = (TextView) itemView.findViewById(R.id.disciplines_cv_title);
            this.cvRadioBtn = (RadioButton) itemView.findViewById(R.id.disciplines_cv_rb);
            this.cvBackground = (ImageView) itemView.findViewById(R.id.disciplines_cv_bg);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            v.setSelected(true);
            cvRadioBtn.setSelected(true);
        }
    }
}
