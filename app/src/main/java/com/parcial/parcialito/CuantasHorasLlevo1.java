package com.parcial.parcialito;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.Toast;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

public class CuantasHorasLlevo1 extends AppCompatActivity {
    private PieChart pieChart;
    private TextView txtAcumuladas, txtMeta, txtFaltantes;

    private String [] pastel = new String[]{"Logrado","Faltante"};
    private int[] grafica = new int[2];
    private int[] colors = new int[]{Color.GREEN,Color.YELLOW};
    private int acumulado=0;
    private int logro=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuantas_horas_llevo);



        pieChart=(PieChart)findViewById(R.id.pieChart);
        txtAcumuladas=(TextView) findViewById(R.id.txtAcumuladas);
        txtMeta=(TextView) findViewById(R.id.txtMeta);
        txtFaltantes=(TextView) findViewById(R.id.txtFaltantes);

        Usuario user = cargarDatos();

        //variables que almacenan el logro
        logro = user.getLogroH();
        acumulado = user.getHoraAcum();
        //variables que se usaran para hacer los porcentajes
        int pAcumulado=0;
        int pFaltante=0;
        pAcumulado=(acumulado*100)/logro;
        pFaltante=100-pAcumulado;
        //llenar los parámetros que usará el metodo para llenar la gráfica
        grafica[0]=pAcumulado;
        grafica[1]=pFaltante;
        //LLamar el método que llena la gráfica
        createCharts();

        //Llena los txt
        int faltante = logro-acumulado;
        txtAcumuladas.setText(""+acumulado);
        txtMeta.setText(""+logro);
        txtFaltantes.setText(""+faltante);

    }

    public Usuario cargarDatos(){
        //Variable que almacenará el resultado de la consulta
        Usuario usuario = null;

        //carga el usuario
        String loginName = cargarUsuario();
        try{
            Usuario user = new Usuario(loginName, "","","",0,0,0,0,"",0,0,0);
            usuario = new DatosGrafica().execute(user).get();
        } catch(Exception ex){
            Toast.makeText(CuantasHorasLlevo1.this, "Error al conectarse a la BD" , Toast.LENGTH_LONG).show();
        }


        return usuario;
    }

    //método que carga las preferencias
    private String cargarUsuario() {
        SharedPreferences preferences = getSharedPreferences("username", MODE_PRIVATE);
        String loginName=preferences.getString("user","");

        return loginName;
    }

    private Chart getSameChart(Chart chart,String description, int textColor, int background, int animateY){
        chart.getDescription().setText(description);
        chart.getDescription().setTextSize(15);
        chart.setBackgroundColor(background);
        chart.animateY(animateY);
        legend(chart);
        return chart;
    }

    private void legend(Chart chart){
        Legend legend =chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        ArrayList<LegendEntry>entries=new ArrayList<>();
        for (int i =0; i<pastel.length;i++){
            LegendEntry entry=new LegendEntry();
            entry.formColor=colors[i];
            entry.label=pastel[i];
            entries.add(entry);
        }
        legend.setCustom(entries);

    }

    //Para agregar las leyendas de la gráfica
    private ArrayList<PieEntry>getPieEntries(){
        ArrayList<PieEntry> entries = new ArrayList <>();
        for (int i =0; i<grafica.length;i++){
            entries.add(new PieEntry(grafica[i]));
        }
        return entries;
    }

    //El que llama a los demás métodos
    public void createCharts(){
        pieChart=(PieChart)getSameChart(pieChart,"Tu logro", Color.GRAY, 045333,3000);
        pieChart.setHoleRadius(20);
        pieChart.setTransparentCircleRadius(12);
        pieChart.setData(getPieData());
        pieChart.invalidate();
        //pieChart.setDrawHoleEnabled(false);

    }
    private DataSet getData(DataSet dataSet){
        dataSet.setColors(colors);
        dataSet.setValueTextColor(Color.BLUE);
        dataSet.setValueTextSize(20);
        return dataSet;

    }
    private PieData getPieData(){
        PieDataSet pieDataSet=(PieDataSet)getData(new PieDataSet(getPieEntries(),""));
        pieDataSet.setSliceSpace(0);
        pieDataSet.setValueFormatter(new PercentFormatter());

        return new PieData(pieDataSet);
    }
}

