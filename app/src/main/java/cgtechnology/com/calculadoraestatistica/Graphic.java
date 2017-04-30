package cgtechnology.com.calculadoraestatistica;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

/**
 * Created by celio on 27/03/17.
 */

public class Graphic extends AppCompatActivity {


    ArrayList<String> dados = new ArrayList<>();
    PieChart pieChart;
    BarChart barChart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);

        dados = getIntent().getStringArrayListExtra("Lista");

        pieChart = (PieChart) findViewById(R.id.PieChart);
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Grafico");
        pieChart.setCenterTextSize(20);
        pieChart.setDrawEntryLabels(true);
        addDataSet(pieChart);


        barChart = (BarChart) findViewById(R.id.barChart);
        Legend l = barChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11);
        l.setXEntrySpace(4f);
        addDataBar(barChart);

    }

    // METODO DE ENCERRAR ACTIVITY QUANDO SELECIONADO O BOTÃO DE VOLTAR
    @Override
    public void onBackPressed()
    {
       this.finish();
    }



    //METODO PARA A IMPLEMENTAÇÃO DO GRAFICO PIZZA
    private void addDataSet(PieChart pieChart) {
        ArrayList<PieEntry> yEntry = new ArrayList<>();

        for (int i = 0; i < dados.size(); i++)
        {
            float dado = Float.parseFloat(dados.get(i));
            yEntry.add(new PieEntry(dado));
        }


        PieDataSet pieDataSet = new PieDataSet(yEntry,"Classes");
        pieDataSet.setSliceSpace(5);
        pieDataSet.setValueTextSize(30);


        ArrayList<Integer> cores = new ArrayList<>();
        cores.add(Color.BLUE);
        cores.add(Color.RED);
        cores.add(Color.GREEN);
        cores.add(Color.YELLOW);
        cores.add(Color.DKGRAY);
        cores.add(Color.MAGENTA);
        cores.add(Color.CYAN);
        cores.add(Color.GRAY);
        cores.add(Color.WHITE);
        cores.add(Color.LTGRAY);

        pieDataSet.setColors(cores);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }


    //METODO PARA IMPLEMENTAÇÃO DO GRAFICO EM BARRA
    private void addDataBar(BarChart barChart){

        ArrayList<BarEntry> lista = new ArrayList<>();

        for (int i = 0; i < dados.size(); i++)
        {
            float dado = Float.parseFloat(dados.get(i));
            lista.add(new BarEntry(i, dado));
        }
        BarDataSet set1 = null;
        ArrayList<Integer> cores = new ArrayList<>();
        cores.add(Color.BLUE);
        cores.add(Color.RED);
        cores.add(Color.GREEN);
        cores.add(Color.YELLOW);
        cores.add(Color.DKGRAY);
        cores.add(Color.MAGENTA);
        cores.add(Color.CYAN);
        cores.add(Color.GRAY);
        cores.add(Color.WHITE);
        cores.add(Color.LTGRAY);
        set1 = new BarDataSet(lista, "Grafico Barra");
        set1.setColors(cores);
        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);
        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);
        data.setBarWidth(0.9f);
        barChart.setData(data);
    }

}
