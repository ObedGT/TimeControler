package com.parcial.parcialito;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Calculadora extends AppCompatActivity implements View.OnClickListener {

    private TextView lblOp;
    private String contenido;
    private int signo=0; //Determina que operacion se esta usando

    private ImageButton btnSuma;
    private ImageButton btnResta;
    private ImageButton btnMulti;
    private ImageButton btnDiv;
    private ImageButton btnIgual;
    private ImageButton btnBorrar;

    private ImageButton cero;
    private ImageButton uno;
    private ImageButton dos;
    private ImageButton tres;
    private ImageButton cuatro;
    private ImageButton cinco;
    private ImageButton seis;
    private ImageButton siete;
    private ImageButton ocho;
    private ImageButton nueve;
    private ImageButton punto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

//Conversion de variables
        lblOp = (TextView) findViewById(R.id.lblOperacion);
        btnSuma = (ImageButton) findViewById (R.id.btnSuma);
        btnResta = (ImageButton) findViewById (R.id.btnResta);
        btnMulti = (ImageButton) findViewById (R.id.btnMulti);
        btnDiv = (ImageButton) findViewById (R.id.btnDiv);
        btnIgual = (ImageButton) findViewById (R.id.btnIgual);
        btnBorrar = (ImageButton) findViewById (R.id.btnBorrar);

        cero= (ImageButton) findViewById (R.id.cero);
        uno= (ImageButton) findViewById (R.id.uno);
        dos= (ImageButton) findViewById (R.id.dos);
        tres= (ImageButton) findViewById (R.id.tres);
        cuatro= (ImageButton) findViewById (R.id.cuatro);
        cinco= (ImageButton) findViewById (R.id.cinco);
        seis= (ImageButton) findViewById (R.id.seis);
        siete= (ImageButton) findViewById (R.id.siete);
        ocho= (ImageButton) findViewById (R.id.ocho);
        nueve= (ImageButton) findViewById (R.id.nueve);
        punto= (ImageButton) findViewById (R.id.punto);

//Definiendoles action listener, accion al tocarlas
        btnSuma.setOnClickListener(this);
        btnResta.setOnClickListener(this);
        btnMulti.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnIgual.setOnClickListener(this);
        btnBorrar.setOnClickListener(this);


        cero.setOnClickListener(this);
        uno.setOnClickListener(this);
        dos.setOnClickListener(this);
        tres.setOnClickListener(this);
        cuatro.setOnClickListener(this);
        cinco.setOnClickListener(this);
        seis.setOnClickListener(this);
        siete.setOnClickListener(this);
        ocho.setOnClickListener(this);
        nueve.setOnClickListener(this);
        punto.setOnClickListener(this);

    }
    //Action listener
    @Override
    public void onClick(View v) {
        int id = v.getId();
        int antes = 0;

        if(id == R.id.btnSuma){
            antes=signo;
            signo=1;
            contenido= (String) lblOp.getText();
            if (antes!=0) lblOp.setText(renombrar(antes));
            else lblOp.setText(lblOp.getText()+"+");
        }

        if(id == R.id.btnResta){
            antes=signo;
            signo=2;
            contenido= (String) lblOp.getText();
            if (antes!=0) lblOp.setText(renombrar(antes));
            else lblOp.setText(lblOp.getText()+"-");
        }

        if(id == R.id.btnMulti){
            antes=signo;
            signo=3;
            contenido= (String) lblOp.getText();
            if (antes!=0) lblOp.setText(renombrar(antes));
            else lblOp.setText(lblOp.getText()+"X");
        }

        if(id == R.id.btnDiv){
            antes=signo;
            signo=4;
            contenido= (String) lblOp.getText();
            if (antes!=0) lblOp.setText(renombrar(antes));
            else lblOp.setText(lblOp.getText()+"/");
        }

        if(id == R.id.btnBorrar){
            contenido = (String) lblOp.getText();
            if (contenido.length()!=0){
                contenido=contenido.substring(0, contenido.length() - 1);
                lblOp.setText(contenido);
                try{
                    int prueba = Integer.valueOf(contenido); //si se borró un signo, el numero se podra convertir
                    signo =0;                                //en entero, entonces signo = 0 porque no hay signo
                }
                catch(NumberFormatException Error){
                }
            }
        }
//es para escribir los numeros y el punto
        if(id == R.id.cero)lblOp.setText(lblOp.getText()+"0");
        if(id == R.id.uno)lblOp.setText(lblOp.getText()+"1");
        if(id == R.id.dos)lblOp.setText(lblOp.getText()+"2");
        if(id == R.id.tres)lblOp.setText(lblOp.getText()+"3");
        if(id == R.id.cuatro)lblOp.setText(lblOp.getText()+"4");
        if(id == R.id.cinco)lblOp.setText(lblOp.getText()+"5");
        if(id == R.id.seis)lblOp.setText(lblOp.getText()+"6");
        if(id == R.id.siete)lblOp.setText(lblOp.getText()+"7");
        if(id == R.id.ocho)lblOp.setText(lblOp.getText()+"8");
        if(id == R.id.nueve)lblOp.setText(lblOp.getText()+"9");
        if(id == R.id.punto)lblOp.setText(lblOp.getText()+".");



        if(id == R.id.btnIgual){
            double respuesta=0;
            contenido = (String) lblOp.getText();
            try {
                if (signo == 1) {
                    String[] cadena = contenido.split("\\+");
                    respuesta = Double.parseDouble(cadena[0]) + Double.parseDouble(cadena[1]);
                } else if (signo == 2) {
                    String[] cadena = contenido.split("-");
                    respuesta = Double.parseDouble(cadena[0]) - Double.parseDouble(cadena[1]);
                } else if (signo == 3) {
                    String[] cadena = contenido.split("X");
                    respuesta = Double.parseDouble(cadena[0]) * Double.parseDouble(cadena[1]);
                } else if (signo == 4) {
                    String[] cadena = contenido.split("/");
                    respuesta = Double.parseDouble(cadena[0]) / Double.parseDouble(cadena[1]);
                }
                lblOp.setText(Double.toString(respuesta));
                signo=0;
            }
            catch(Exception e){
                lblOp.setText("");
                Toast msjError = Toast.makeText(getApplicationContext(), "Syntax Error", Toast.LENGTH_SHORT);
                msjError.setGravity(Gravity.CENTER, 0, 0);
                msjError.show();
                signo =0;
            }

            contenido = (String) lblOp.getText();
            int pasatiempo = 1+1;
        }


    }

    private String renombrar(int antes) {
        String nuevo="";
        if (signo==1){
            if (antes==1) nuevo=contenido;
            else if (antes==2) nuevo=contenido.replace("-", "+");
            else if (antes==3) nuevo=contenido.replace("X", "+");
            else if (antes==4) nuevo=contenido.replace("/", "+");
        }
        else if (signo==2){
            if (antes==1) nuevo=contenido.replace("+", "-");
            else if (antes==2) nuevo=contenido;
            else if (antes==3) nuevo=contenido.replace("X", "-");
            else if (antes==4) nuevo=contenido.replace("/", "-");
        }
        else if (signo==3){
            if (antes==1) nuevo=contenido.replace("+", "X");
            else if (antes==2) nuevo=contenido.replace("-", "X");
            else if (antes==3) nuevo=contenido;
            else if (antes==4) nuevo=contenido.replace("/", "X");
        }
        else if (signo==4){
            if (antes==1) nuevo=contenido.replace("+", "/");
            else if (antes==2) nuevo=contenido.replace("-", "/");
            else if (antes==3) nuevo=contenido.replace("X", "/");
            else if (antes==4) nuevo=contenido;
        }
        return nuevo;
    }
}
