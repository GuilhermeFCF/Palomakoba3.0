package com.example.pagamentodecompras;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CheckBox cbArroz,cbCarne,cbPao,cbLeite,cbOvos;
    Button btnTotal, btnPagar;
    RadioButton rbSemDesc, rb5desc,rb10desc,rb15desc;
    TextView txtTotal;
    EditText edtPagamento;
    double total,arroz,carne,pao,leite,ovos,pago,troco;

    String desconto = "Sem desconto";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cbArroz = findViewById(R.id.cbArroz);
        cbCarne = findViewById(R.id.cbCarne);
        cbPao = findViewById(R.id.cbPao);
        cbLeite = findViewById(R.id.cbLeite);
        cbOvos = findViewById(R.id.cbOvos);
        btnTotal = findViewById(R.id.btnTotal);
        btnPagar = findViewById(R.id.btnPagar);
        rbSemDesc = findViewById(R.id.rbSemdesc);
        rb5desc = findViewById(R.id.rb5desc);
        rb10desc = findViewById(R.id.rb10desc);
        rb15desc = findViewById(R.id.rb15desc);
        txtTotal = findViewById(R.id.txtTotal);
        edtPagamento = findViewById(R.id.edtPagamento);

        btnTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cbArroz.isChecked()){
                    arroz = 3.5;
                }else{ arroz = 0;}
                if(cbCarne.isChecked()){
                    carne = 12.30;
                }else { carne = 0;}
                if(cbPao.isChecked()){
                    pao = 2.20;
                } else { pao = 0;}
                if(cbLeite.isChecked()) {
                    leite = 5.50;
                } else { leite = 0; }
                if (cbOvos.isChecked()) {
                    ovos = 7.50;
                } else { ovos =0;}
                total = arroz + carne + pao + leite + ovos;
                txtTotal.setText(String.format("R$%.2f", total));
            }

        }); // Fim do btnTotal

        btnPagar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                double comdesc = total;
                if(edtPagamento.getText().toString().equals(""))  //Chegar se o campo de pagamento está vazio.
                {
                    Toast.makeText(MainActivity.this, String.format("Impossível realizar a compra! Campo de pagamento vazio!"), Toast.LENGTH_SHORT).show();
                    Log.d("PAGAMENTO_DE_COMPRAS", "Impossível realizar a compra");
                }
                else
                {
                    try //tentar receber o valor de edtpagamento como um double
                    {
                        pago = Double.parseDouble(edtPagamento.getText().toString());
                    } catch(NumberFormatException e)
                    {
                        Toast.makeText(MainActivity.this, "Valor inválido!", Toast.LENGTH_SHORT).show();
                    }

                    if (total > 0)
                    {

                        if (rbSemDesc.isChecked())
                        {
                            desconto = "Sem desconto";
                        }


                        if (rb5desc.isChecked())
                        {
                            desconto = "5% de desconto";
                            comdesc = total - total * 0.05;
                        }
                        if (rb10desc.isChecked())
                        {
                            desconto = "10% de desconto";
                            comdesc = total - total * 0.1;
                        }
                        if (rb15desc.isChecked())
                        {
                            desconto = "15% de desconto";
                            comdesc = total - total * 0.15;
                        }


                    if (pago >= comdesc)
                    {
                        troco = pago - comdesc;
                        //Toast.makeText(MainActivity.this, String.format("%.2f \n %.2f \n %s \n %.2f \n %.2f",total,comdesc,desconto,pago,troco), Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                        alerta.setTitle("Informações");
                        String textao = String.format("--INFORMAÇÕES--\n Valor: R$%.2f \n Desconto: %s \n Valor com desconto: R$%.2f \n Valor pago: R$%.2f \nTroco: R$%.2f", total, desconto, comdesc, pago, troco);
                        alerta.setMessage(textao);
                        alerta.setNeutralButton("OK", null);
                        alerta.show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, String.format("Impossível realizar a compra! Valor inserido menor do que o total!"), Toast.LENGTH_SHORT).show();
                    }
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, String.format("Escolha seu produto!"), Toast.LENGTH_SHORT).show();
                    }

                    }
                }
        }); //Fim do btn Pagar
        }
    }