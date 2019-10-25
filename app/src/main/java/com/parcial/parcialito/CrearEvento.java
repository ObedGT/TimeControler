package com.parcial.parcialito;



import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.time.OffsetDateTime.now;

public class CrearEvento extends AppCompatActivity implements View.OnClickListener {

    private EditText txtNombre;
    private EditText txtDescripcion;
    private EditText txtInicio;
    private EditText txtFinal;
    private EditText horaInicio;
    private  EditText horaFinal;
    private EditText txtCantidad;
    private EditText txtLimite;

    private Button btnGuardar;
    private Button btnInicio;
    private Button btnFinal;
    private Button btnInicioH;
    private Button btnFinalH;

    private int dia, mes, ano, hora, minutos, segundos;

    public CrearEvento() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtDescripcion = (EditText) findViewById(R.id.txtDescripcion);
        txtInicio = (EditText) findViewById(R.id.txtInicio);
        txtFinal = (EditText) findViewById(R.id.txtFinal);
        horaInicio = (EditText) findViewById(R.id.horaInicio);
        horaFinal = (EditText) findViewById(R.id.horaFinal);
        txtCantidad = (EditText) findViewById(R.id.txtCantidad);
        txtLimite = (EditText) findViewById(R.id.txtLimite);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnInicio = (Button) findViewById(R.id.btnInicio);
        btnFinal = (Button) findViewById(R.id.btnFinal);
        btnInicioH = (Button) findViewById(R.id.btninicioH);
        btnFinalH = (Button) findViewById(R.id.btnFinalH);
        btnInicio.setOnClickListener(this);
        btnFinal.setOnClickListener(this);
        btnInicioH.setOnClickListener(this);
        btnFinalH.setOnClickListener(this);





        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {



                if (txtNombre.getText().toString().isEmpty() && txtDescripcion.getText().toString().isEmpty() && txtInicio.getText().toString().isEmpty() && txtFinal.getText().toString().isEmpty() && txtCantidad.getText().toString().isEmpty() && txtLimite.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Todos los campos vacíos, por favor ingrese los datos requeridos", Toast.LENGTH_LONG).show();
                } else {
                    AgregarRegistro();
                    txtNombre.setText("");
                    txtDescripcion.setText("");
                    txtInicio.setText("");
                    txtFinal.setText("");
                    horaInicio.setText("");
                    horaFinal.setText("");
                    txtCantidad.setText("");
                    txtLimite.setText("");


                }


            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void AgregarRegistro(){
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            String dateTime = txtInicio.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
            Log.i("tifalab", dateTime);

            String dateTime1 = txtFinal.getText().toString();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MMM-dd");
            Log.i("tifalab", dateTime1);


            String time = horaInicio.getText().toString();
            DateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
            Log.i("tifalab", time);

            String time1 = horaFinal.getText().toString();
            DateFormat sdf3 = new SimpleDateFormat("hh:mm:ss");
            Log.i("tifalab", time1);

            String dateInicio = dateTime +" " +time;
            String dateFin = dateTime1 +" " +time1;

            String sql="insert into evento(nombre, descripcion,  estado, fk_loginName, fechaInicio, fechaFin, cantidadH, dCancelar) values('" + txtNombre.getText().toString() + "', '" + txtDescripcion.getText().toString()+"' ,  1,'admin', '"  + dateInicio+ "', '"  + dateFin+ "'," +  Integer.parseInt(txtCantidad.getText().toString() )+ ", "+  Integer.parseInt(txtLimite.getText().toString()) +")";

            Conexion conexion = new Conexion();
            conn = conexion.connect();
            pst = conn.prepareStatement(sql);
            pst.executeUpdate();

            Toast.makeText(getApplicationContext(),"REGISTRO EXITOSO",Toast.LENGTH_SHORT).show();
        }catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        finally
        {
            try
            {
                if (conn!=null) {
                    conn.close();
                }
                if (pst!=null) {
                    pst.close();
                }
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        if(view==btnInicio) {
            final Calendar c= Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                    txtInicio.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
                }
            }

            ,ano,mes,dia);
            datePickerDialog.show();
        }

        if (view==btnFinal){

            final Calendar c= Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                    txtFinal.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
                }
            }

                    ,ano,mes,dia);
            datePickerDialog.show();
        }

        if(view==btnInicioH) {
            final Calendar c= Calendar.getInstance();
            hora=c.get(Calendar.HOUR_OF_DAY);
            minutos= c.get(Calendar.MINUTE);
            segundos = c.get(Calendar.SECOND);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                  horaInicio.setText(hourOfDay+":"+minute+":" +00);

                }
            },hora,minutos,false);
            timePickerDialog.show();

        }



        if(view==btnFinalH) {
            final Calendar c= Calendar.getInstance();
            hora=c.get(Calendar.HOUR_OF_DAY);
            minutos= c.get(Calendar.MINUTE);
            segundos = c.get(Calendar.SECOND);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                   horaFinal.setText(hourOfDay+":"+minute+":" +00);

                }
            },hora,minutos,false);
            timePickerDialog.show();

        }





    }
    }





/*
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parcial.parcialito.R;

public class CrearEvento extends AppCompatActivity {


    private Button btnGuardar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);




        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(txtNombre.equals("")|| txtDescripcion.equals("") ||txtInicio.equals("") || txtFinal.equals("") || txtCantidad.equals("") || txtLimite.equals("")){

                    Toast.makeText(CrearEvento.this, "Debe rellenar todos los campos", Toast.LENGTH_LONG).show();


                } else{

                    Toast.makeText(CrearEvento.this, "Campos llenos", Toast.LENGTH_LONG).show();




                }
            }
        });






    }
}
*/