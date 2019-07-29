package com.apps.malpv.resuelve;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //region Inicializacion
    private EditText etNum1, etNum2;
    private TextView tvNum1, tvNum2, tvResultado;
    private Switch swRegistro;
    private RadioGroup rbGroup;
    private RadioButton rbSumar, rbRestar, rbDividir, rbMultiplicar, rbPotencia;
    private Button btnCalcular;
    private ListView lsResutados;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region Referencias
        etNum1 = (EditText) findViewById(R.id.etNum1);
        etNum2 = (EditText) findViewById(R.id.etNum2);
        tvNum1 = (TextView) findViewById(R.id.tvNum1);
        tvNum2 = (TextView) findViewById(R.id.tvNum2);
        swRegistro = (Switch) findViewById(R.id.swRegistro);
        rbGroup = (RadioGroup) findViewById(R.id.rbGroup);
        rbSumar = (RadioButton) findViewById(R.id.rbSumar);
        rbRestar = (RadioButton) findViewById(R.id.rbRestar);
        rbDividir = (RadioButton) findViewById(R.id.rbDividir);
        rbMultiplicar = (RadioButton) findViewById(R.id.rbMultiplicar);
        rbPotencia = (RadioButton) findViewById(R.id.rbPotencia);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        tvResultado = (TextView) findViewById(R.id.tvResultado);
        lsResutados = (ListView) findViewById(R.id.lsResultados);

        final ArrayList<String> registros = new ArrayList<>();
        registros.add("6+10 = 16");

        final ArrayAdapter adResultados =new ArrayAdapter(this,android.R.layout.simple_list_item_1, registros);

        lsResutados.setAdapter(adResultados);
        //endregion

        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int rbId) {

                 switch(rbId){
                     case R.id.rbSumar:
                         tvNum1.setText("Sumando");
                         tvNum2.setText("Sumando");
                         tvResultado.setText("Suma");
                         break;
                     case R.id.rbRestar:
                         tvNum1.setText("Minuendo");
                         tvNum2.setText("Sustraendo");
                         tvResultado.setText("Diferencia");
                         break;
                     case R.id.rbDividir:
                         tvNum1.setText("Dividendo");
                         tvNum2.setText("Divisor");
                         tvResultado.setText("Cociente");
                         break;
                     case R.id.rbMultiplicar:
                         tvNum1.setText("Multiplicando");
                         tvNum2.setText("Multiplicador");
                         tvResultado.setText("Producto");
                         break;
                     case R.id.rbPotencia:
                         tvNum1.setText("Base");
                         tvNum2.setText("Exponente");
                         tvResultado.setText("Potencia");
                         break;
                }
            }
        });

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNum1.getText().toString().isEmpty()){
                    etNum1.setError("Favor ingresar numero");
                }else if (etNum2.getText().toString().isEmpty()){
                    etNum2.setError("Favor ingresar numero");
                }else {
                    resultado();
                }
            }

            private void resultado() {
                final int num1 = Integer.parseInt(etNum1.getText().toString());
                final int num2 = Integer.parseInt(etNum2.getText().toString());
                int result = 0;
                String signo = "";

                //region Calculos
                if (rbSumar.isChecked() == true){
                    result = num1 + num2;
                    tvResultado.setText(""+result);
                    signo = "+";
                }else if (rbRestar.isChecked() == true){
                    result = num1 - num2;
                    tvResultado.setText(""+result);
                    signo = "-";
                }else if (rbDividir.isChecked() == true){
                    double n1 = Integer.parseInt(etNum1.getText().toString());
                    double n2 = Integer.parseInt(etNum2.getText().toString());
                    double res = 0;
                    res = n1 / n2;
                    tvResultado.setText(""+res);
                    signo = "/";
                }else if (rbMultiplicar.isChecked() == true){
                    result = num1 * num2;
                    tvResultado.setText(""+result);
                    signo = "*";
                }else if (rbPotencia.isChecked() == true){
                    result = (int)  Math.pow(num1, num2);
                    tvResultado.setText(""+result);
                    signo = "^";
                }else {
                    Toast.makeText(MainActivity.this, "Favor seleccionar una opción a calcular", Toast.LENGTH_SHORT).show();
                }
                //endregion

                if(swRegistro.isChecked()){
                    registros.add(num1+signo+num2+" = "+result);
                    lsResutados.setAdapter(adResultados);
                }
            }
        });
    }
}
