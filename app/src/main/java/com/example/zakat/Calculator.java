package com.example.zakat;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class Calculator extends AppCompatActivity {

    private EditText GoldWeight;
    private EditText GoldPrice;
    private TextView GWU;
    private TextView ZP;
    private TextView Zakat;
    private RadioGroup gType;
    private RadioButton goldWear;
    private RadioButton goldKeep;


    private double goldWeight;
    private double goldValue;
    private double GoldWeightUruf;
    private double zakatPayable;
    private double totalZakat;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Zakat Calculator");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        GoldWeight = findViewById(R.id.gram);
        GoldPrice = findViewById(R.id.RMg);
        gType = findViewById(R.id.gTy);
        goldWear = findViewById(R.id.gWear);
        goldKeep = findViewById(R.id.gKeep);
        GWU = findViewById(R.id.totGWU);
        ZP = findViewById(R.id.totZP);
        Zakat = findViewById(R.id.totZ);

        Button calculateButton = findViewById(R.id.kiraBtn);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateZakat();
            }
        });

        Button resetButton = findViewById(R.id.resetBtn);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetForm();
            }
        });
    }


    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void calculateZakat() {

        String goldWeightStr = GoldWeight.getText().toString();
        String goldPriceStr = GoldPrice.getText().toString();

        if (goldWeightStr.isEmpty()) {
            Toast.makeText(this, "Please enter the Gold Weight!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (goldPriceStr.isEmpty()) {
            Toast.makeText(this, "Please enter the Gold Price!", Toast.LENGTH_SHORT).show();
            return;
        }

        goldWeight = Double.parseDouble(goldWeightStr);
        goldValue = Double.parseDouble(goldPriceStr);

        int selectedRadioButtonId = gType.getCheckedRadioButtonId();
        if (selectedRadioButtonId == -1) {
            Toast.makeText(this, "Please select a gold type!", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton radioButton = findViewById(selectedRadioButtonId);
        double goldTypeValue = 0;

        if (radioButton.getId() == R.id.gWear) {
            goldTypeValue = 200;
        }
        else if (radioButton.getId() == R.id.gKeep) {
            goldTypeValue = 85;
        }

        double goldWeightMinusX = goldWeight - goldTypeValue;


        if (goldWeightMinusX >= 0) {
            zakatPayable = goldWeightMinusX * goldValue;
        }
        else {
            zakatPayable = 0;
            totalZakat = 0;
        }

        double totalZakat = zakatPayable * 0.025;

        GWU.setText(String.format("RM %.2f", goldWeightMinusX));
        ZP.setText(String.format("RM %.2f", zakatPayable));
        Zakat.setText(String.format("RM %.2f",totalZakat));
    }

    private void resetForm() {
        // Reset all input fields and text views
        GoldWeight.setText("");
        GoldPrice.setText("");
        GWU.setText("");
        ZP.setText("");
        Zakat.setText("");

        // Reset the radio buttons to default
        gType.clearCheck();
    }

}
