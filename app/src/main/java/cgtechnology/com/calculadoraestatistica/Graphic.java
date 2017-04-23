package cgtechnology.com.calculadoraestatistica;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

/**
 * Created by celio on 27/03/17.
 */

public class Graphic extends AppCompatActivity {

    private static String TAG = "MainActivity";

    private float[] ydata = {25.3f, 30,55f, 44.15f};
    private String[] xdata = {"Celio","Joao","lidia"};
    PieChart pieChart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);

        Log.d(TAG, " OnCreate");

        Apresentacao();
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.i("INFO","LISTNER 1"+e+" ++ "+h+"");
            }

            @Override
            public void onNothingSelected() {
                Log.i("INFO","LISTNER 2");
            }
        });
    }



    public void Apresentacao()
    {
        pieChart = (PieChart) findViewById(R.id.PieChart);

        //pieChart.setDescription("Sale BY Employee (IN Thoausand)");
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Grafico");
        pieChart.setCenterTextSize(10);
        pieChart.setDrawEntryLabels(true);

        addDataSet(pieChart);
    }

    private void addDataSet(PieChart pieChart)
    {
        Log.i("INFO",TAG+" Started");
        ArrayList<PieEntry> yEntry = new ArrayList<>();
        ArrayList<String> xEntry = new ArrayList<>();



        for (int i = 0; i < ydata.length; i++)
        {
            yEntry.add(new PieEntry(ydata[i]));
        }
        for (int i = 1; i < xdata.length; i++)
        {
            xEntry.add(xdata[i]);
        }


        PieDataSet pieDataSet = new PieDataSet(yEntry,"Empoyee Sales");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(30);


        ArrayList<Integer> cores = new ArrayList<>();
        cores.add(Color.BLUE);
        cores.add(Color.RED);
        cores.add(Color.GREEN);

        pieDataSet.setColors(cores);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();



    }




}
