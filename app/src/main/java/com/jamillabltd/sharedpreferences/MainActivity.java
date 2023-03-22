package com.jamillabltd.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button stringSave = findViewById(R.id.stringSaveId);
        Button intSave = findViewById(R.id.intSaveId);
        Button booleanSave = findViewById(R.id.booleanSaveId);

        stringSave.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, StringSave.class)));
        intSave.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, IntSave.class)));
        booleanSave.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, BooleanSaveActivity.class)));



    }
}