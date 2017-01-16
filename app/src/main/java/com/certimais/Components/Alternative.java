package com.certimais.Components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.certimais.Consts.AppConsts;
import com.certimais.R;

/**
 * Created by Luciano José on 07/01/2017.
 */

public class Alternative extends LinearLayout {

    LinearLayout mAlternative;
    private String ApiID;
    TextView mAlternativeLetter;
    TextView mAlternativeText;


    public Alternative(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public void initView(Context context, AttributeSet attrs) {
        View.inflate(context, R.layout.alternative_layout, this);

        mAlternative = (LinearLayout) findViewById(R.id.alternative);
        mAlternativeLetter = (TextView) findViewById(R.id.alternativeLetter);
        mAlternativeText = (TextView) findViewById(R.id.alternativeText);

        if (attrs != null) {
            TypedArray prop = context.getTheme()
                    .obtainStyledAttributes(attrs, R.styleable.Alternative, 0, 0);

            String text = "";
            String letter = "";

            try {
                text = prop.getString(R.styleable.Alternative_text);
                letter = prop.getString(R.styleable.Alternative_letter);
            } catch (Exception e) {
                Log.e("ALTERNATIVE", "There was an error loading attributes.");
            } finally {
                prop.recycle();
            }

            setText(text);
            setLetter(letter);

        }
    }

    public void setSelected(boolean selected) {

        if (selected) {

            if (AppConsts.SDK_VERSION < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                mAlternative.setBackgroundDrawable(getResources().getDrawable(R.drawable.alternative_selected_background));
                mAlternativeLetter.setBackgroundDrawable(getResources().getDrawable(R.drawable.alternative_letter_selected_background));
            } else {
                mAlternative.setBackground(getResources().getDrawable(R.drawable.alternative_selected_background));
                mAlternativeLetter.setBackgroundDrawable(getResources().getDrawable(R.drawable.alternative_letter_selected_background));
            }

            mAlternativeLetter.setTextColor(getResources().getColor(R.color.colorAccent));
            mAlternativeText.setTextColor(Color.WHITE);

        } else {

            if (AppConsts.SDK_VERSION < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                mAlternative.setBackgroundDrawable(getResources().getDrawable(R.drawable.alternative_background));
                mAlternativeLetter.setBackgroundDrawable(getResources().getDrawable(R.drawable.alternative_letter_background));
            } else {
                mAlternative.setBackground(getResources().getDrawable(R.drawable.alternative_background));
                mAlternativeLetter.setBackgroundDrawable(getResources().getDrawable(R.drawable.alternative_letter_background));
            }

            mAlternativeLetter.setTextColor(Color.WHITE);
            mAlternativeText.setTextColor(Color.GRAY);
        }
    }

    public void setText(String text) {
        mAlternativeText.setText(text);
    }

    public void setLetter(String letter) {
        mAlternativeLetter.setText(letter);
    }

    public void setApiID(String apiID) {
        ApiID = apiID;
    }

    public String getApiID() {
        return ApiID;
    }
}
