package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
//import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private Retrofit retrofit;
    private Button button;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.txtView);
        button = findViewById(R.id.btnRecuperar);

        String urlCep = "https://viacep.com.br/ws/";
        retrofit = new Retrofit.Builder().baseUrl(urlCep).addConverterFactory(GsonConverterFactory.create()).build();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recuperarCep();
            }
        });
    }
    public void recuperarCep(){
        CEPService cepService = retrofit.create(CEPService.class);
        Call<CEP> cepCall = cepService.recuperarCEP("76804340");
        cepCall.enqueue(new Callback<CEP>() {
            //@Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                if (response.isSuccessful()) {
                    CEP cep = response.body();
                    textView.setText(cep.getBairro() + "/" + cep.getLogradouro() + "/" + cep.getLocalidade());
                }
            }
            public void onFailure(Call<CEP> call, Throwable t) {

            }
        });
    }
}