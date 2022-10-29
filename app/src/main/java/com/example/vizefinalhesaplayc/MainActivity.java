package com.example.vizefinalhesaplayc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private EditText enteredFirstGrade, enteredSecondGrade, barrage;
    private TextView firstResult, secondResult, firstGradeRatio, secondGradeRatio, calculateResult;
    private SeekBar firstGradeRatioSeekBar, secondGradeRatioSeekBar;
    private Button calculate;

    private int seekPerVize;
    private int seekPerFinal;
    private Double enteredValue;
    private Double firstGradeResult;
    private Double secondGradeResult;
    private Double sumResult;
    private Double barrageResult;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customVisual();
        enteredFirstGrade = (EditText) findViewById(R.id.enteredFirstGrade);
        enteredSecondGrade = (EditText) findViewById(R.id.enteredSecondGrade);
        barrage = (EditText) findViewById(R.id.barrage);
        firstResult = (TextView) findViewById(R.id.firstResult);
        secondResult = (TextView) findViewById(R.id.secondResult);
        firstGradeRatio = (TextView) findViewById(R.id.firstGradeRatio);
        secondGradeRatio = (TextView) findViewById(R.id.secondGradeRatio);
        calculateResult = (TextView) findViewById(R.id.calculateResult);
        firstGradeRatioSeekBar = (SeekBar) findViewById(R.id.firstGradeRatioSeekBar);
        secondGradeRatioSeekBar = (SeekBar) findViewById(R.id.secondGradeRatioSeekBar);
        calculate = (Button) findViewById(R.id.calculate);

        firstGradeRatioSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                firstGradeRatio.setText(String.valueOf(firstGradeRatioSeekBar.getProgress()) + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekPerVize = seekBar.getProgress();
            }
        });

        secondGradeRatioSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                secondGradeRatio.setText(String.valueOf(secondGradeRatioSeekBar.getProgress() + "%"));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekPerFinal = seekBar.getProgress();
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                DecimalFormat decimalFormat = new DecimalFormat("#.0");
                if (!enteredFirstGrade.getText().toString().equals("")) {
                    enteredValue = Double.parseDouble(enteredFirstGrade.getText().toString());
                    firstGradeResult = (enteredValue / 100) * seekPerVize;
                    firstResult.setText("İlk sınav oranın " + String.valueOf(decimalFormat.format(firstGradeResult)));
                }if (!enteredSecondGrade.getText().toString().equals("")) {
                    enteredValue = Double.parseDouble(enteredSecondGrade.getText().toString());
                    secondGradeResult = (enteredValue / 100) * seekPerFinal;

                    secondResult.setText("İkinci sınav oranın " + String.valueOf(decimalFormat.format(secondGradeResult)));

                } else {
                    Toast.makeText(MainActivity.this, "Sayı giriniz", Toast.LENGTH_SHORT).show();
                }if (!enteredFirstGrade.getText().toString().equals("") || !enteredSecondGrade.getText().toString().equals("")) {
                    sumResult = firstGradeResult + secondGradeResult;
                    if (barrage.getText().toString().equals("")) {
                        Toast.makeText(MainActivity.this, "Baraj puanı girilmeden başarı hesaplanmıyor.", Toast.LENGTH_SHORT).show();
                    } else {
                        calculateResult.setText("Sınav sonuçlarının toplamı: " + String.valueOf(decimalFormat.format(sumResult)));
                        barrageResult = Double.parseDouble(barrage.getText().toString());
                        if (sumResult >= barrageResult) {
                            calculateResult.setText(String.valueOf(decimalFormat.format(sumResult)) + " Dersten geçtin. TEBRİKLER!");
                        } else {
                            calculateResult.setText(String.valueOf(decimalFormat.format(sumResult)) + " Maalesef geçme notunun altında.");
                        }
                    }
                }else{
                    sumResult=0.0;
                    secondGradeResult=0.0;
                    firstResult.setText("İlk sınav oranın " + String.valueOf(decimalFormat.format(firstGradeResult)));
                }
            }
        });

    }

    public void customVisual() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {

            final int layoutFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            window.getDecorView().setSystemUiVisibility(layoutFlags);

            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(0x00000000);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                window.setDecorFitsSystemWindows(false);
            }
        }
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
    }
}