package com.example.appbussola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {

    private Sensor bussola;
    private Sensor acelerometro;
    private SensorManager sensorManager;
    private SensorEventListener listener;

    private float ultimoGrau = 0f;
    private float valoresBussola[] = new float[3];
    private float valoresGravidade[] = new float[3];
    private float angulosDeOrientacao[] = new float[3];
    private float matrizRotacao[] = new float[9];

    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        bussola = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                switch (sensorEvent.sensor.getType()){ //Colocar os dados do sensor nas variaveis valoresGravidade e valoresBussola
                    case Sensor.TYPE_ACCELEROMETER:
                        valoresGravidade = sensorEvent.values.clone();
                        break;
                    case Sensor.TYPE_MAGNETIC_FIELD:
                        valoresBussola = sensorEvent.values.clone();
                        break;
                }
                SensorManager.getRotationMatrix(matrizRotacao, null, valoresGravidade, valoresBussola);
                SensorManager.getOrientation(matrizRotacao, angulosDeOrientacao);
                double radiano = angulosDeOrientacao[0];
                float grauAtual = (float) (Math.toDegrees(radiano) + 360) % 360;
                RotateAnimation rotateAnimation = new RotateAnimation(ultimoGrau,-grauAtual,Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setDuration(250);
                rotateAnimation.setFillAfter(true);
                imageView.startAnimation(rotateAnimation);
                ultimoGrau = -grauAtual;
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }


        };


    }
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(listener,acelerometro,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(listener,bussola,SensorManager.SENSOR_DELAY_NORMAL);

    }
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listener);

    }
}