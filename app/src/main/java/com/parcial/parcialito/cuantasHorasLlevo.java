package com.parcial.parcialito;

import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
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

public class cuantasHorasLlevo extends AppCompatActivity {

    private PieChart pieChart;
    private String [] pastel = new String[]{"Logrado","Faltante"};
    private int[] grafica = new int[2];
    private int[] colors = new int[]{Color.GREEN,Color.YELLOW};
    private int acumulado=0;
    private int logro=0;
    private String Usuario="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuantas_horas_llevo);

        pieChart=(PieChart)findViewById(R.id.pieChart);

        Usuario user = validarLogin();

        logro = user.getLogroH();
        acumulado = user.getHoraAcum();
        int pAcumulado=0;
        int pFaltante=0;
        pAcumulado=(acumulado*100)/logro;
        pFaltante=100-pAcumulado;
        grafica[0]=pAcumulado;
        grafica[1]=pFaltante;
        createCharts();

    }

    public Usuario validarLogin(){
        //Variable que almacenará el resultado de la consulta
        Usuario usuario = null;

        /*try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn;
            Conexion conexion = new Conexion();
            conn = conexion.connect();
            Statement st = conn.createStatement();
            String sql="select login_name, pass, correo, nombre, apellido, id_estado, id_rol from usuario where login_name = '"+txtUsuario.getText().toString()  +"' and password = '"+txtPassword.getText().toString() + "'";
            ResultSet rs = st.executeQuery(sql);
            if(rs.first())
            {
                do
                {
                    usuario = new Usuario(rs.getString("pk_loginName"), rs.getString("password"), rs.getString("nombre"), rs.getString("apellido"), rs.getInt("fk_rol"), rs.getInt("activo"));
                }while(rs.next());
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

         */

        //Asignamos el driver de conexion
        String driver = "com.mysql.jdbc.Driver";
        try{
            //Cargamos el driver con el conector jdbc
            Class.forName(driver).newInstance();
            Usuario user = new Usuario("cas16454", "","","",0,0,0,0,"",0,0,0);
            usuario = new DatosGrafica().execute(user).get();
        } catch(Exception ex){
            Toast.makeText(cuantasHorasLlevo.this, "Error al conectarse a la BD" + ex.getMessage(), Toast.LENGTH_LONG).show();
        }


        return usuario;
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
    private ArrayList<PieEntry>getPieEntries(){
        ArrayList<PieEntry> entries = new ArrayList <>();
        for (int i =0; i<grafica.length;i++){
            entries.add(new PieEntry(grafica[i]));
        }
        return entries;
    }

    public void createCharts(){
        pieChart=(PieChart)getSameChart(pieChart,"logros", Color.GRAY, 045333,3000);
        pieChart.setHoleRadius(10);
        pieChart.setTransparentCircleRadius(20);
        pieChart.setData(getPieData());
        pieChart.invalidate();
        //pieChart.setDrawHoleEnabled(false);

    }
    private DataSet getData(DataSet dataSet){
        dataSet.setColors(colors);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(10);
        return dataSet;

    }
    private PieData getPieData(){
        PieDataSet pieDataSet=(PieDataSet)getData(new PieDataSet(getPieEntries(),"Tu Logro"));
        pieDataSet.setSliceSpace(0);
        pieDataSet.setValueFormatter(new PercentFormatter());

        return new PieData(pieDataSet);
    }
}
