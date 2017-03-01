package com.simulando.UI.Dashboard.Exams.ExamResult;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.simulando.R;

import java.util.ArrayList;

public class ExamResultActivity extends AppCompatActivity {

    PieChart mChart;
    ArrayList<Integer> colors;
    ArrayList<PieEntry> entries;
    PieDataSet dataSet;
    PieData chartData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_result);

        mChart = (PieChart) findViewById(R.id.resultChart);
        mChart.getDescription().setEnabled(false);

        mChart.setCenterText(getAverage(342));

        entries = new ArrayList<>();
        colors = new ArrayList<>();

        getData();
        generateColors();

        dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);
        dataSet.setColors(colors);

        chartData = new PieData(dataSet);
        chartData.setValueTextSize(18);
        chartData.setValueTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        chartData.setValueTextColor(Color.WHITE);
        chartData.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return String.format("%.2f", value);
            }
        });

        mChart.setHoleRadius(45f);
        mChart.setTransparentCircleRadius(50f);
        mChart.getLegend().setEnabled(false);
        mChart.setDrawEntryLabels(false);
        mChart.setDrawSliceText(false);

        mChart.setData(chartData);
        mChart.animateY(2000);
        mChart.invalidate();

    }

    public void getData() {
        entries.clear();
        entries.add(new PieEntry((float) 525.44, "Ciências da Natureza e suas Tecnologias"));
        entries.add(new PieEntry(344, "Ciências Humanas e suas Tecnologias"));
        entries.add(new PieEntry(344, "Linguagens e Códigos"));
        entries.add(new PieEntry(525.44f, "Matemática"));
    }

    public void generateColors() {
        final int GREEN_COLOR = Color.rgb(102, 187, 106);
        final int BLUE_COLOR = Color.rgb(38, 198, 218);
        final int YELLOW_COLOR = Color.rgb(255, 235, 59);
        final int ORANGE_COLOR = Color.rgb(255, 152, 0);
        colors.clear();
        colors.add(BLUE_COLOR);
        colors.add(GREEN_COLOR);
        colors.add(YELLOW_COLOR);
        colors.add(ORANGE_COLOR);
    }

    private SpannableString getAverage(float average) {
        String averageGrade = getResources().getString(R.string.average_grade, average);
        SpannableString averageText = new SpannableString(averageGrade);
        averageText.setSpan(new RelativeSizeSpan(1.8f), 0, 11, 0);
        averageText.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 0, 11, 0);
        averageText.setSpan(new ForegroundColorSpan(getResources().getColor(android.R.color.darker_gray)), 0, 12, 0);

        averageText.setSpan(new RelativeSizeSpan(2.5f), 12, 19, 0);
        averageText.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 12, averageText.length(), 0);
        averageText.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), averageText.length() - 6, averageText.length(), 0);
        return averageText;
    }

}
