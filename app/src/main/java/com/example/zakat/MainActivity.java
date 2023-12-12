package com.example.zakat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button calcButton, abtButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        calcButton = findViewById(R.id.calcBtn);
        abtButton = findViewById(R.id.abtBtn);

        calcButton.setOnClickListener(this);
        abtButton.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.share){
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT,"Assalamualaikum, this is MyZakat Application for calculating Zakat. Feel free to use my application :) - https://t.co/app");
            startActivity(Intent.createChooser(shareIntent, null));

            return true;
        } else if (item.getItemId() == R.id.help) {
            Intent helpIntent = new Intent(this, HowToUse.class);
            startActivity(helpIntent);
            return true;
            
        }

        return false;
    }

    @Override
    public void onClick(View view) {
        if(view == calcButton) {
            Intent intent = new Intent(this, Calculator.class);
            startActivity(intent);
        }
        else if (view == abtButton) {
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
        }
    }
}