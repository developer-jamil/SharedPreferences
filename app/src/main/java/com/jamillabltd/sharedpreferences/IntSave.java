package com.jamillabltd.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class IntSave extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_int_save);

        //To retrieve an integer value:
        SharedPreferences getPreferences = getSharedPreferences("MyPrefsInt", MODE_PRIVATE);
        int myInt = getPreferences.getInt("myIntKey", 0); // 0 is the default value if "myIntKey" doesn't exist in SharedPreferences
        //set saved value from sharePreferences
        TextView rollView = findViewById(R.id.rollViewId);
        rollView.setText(String.valueOf(myInt));

        //initialize
        EditText roll = findViewById(R.id.rollEditTextId);
        Button save = findViewById(R.id.saveRollButtonId);
        save.setOnClickListener(view -> {

            String getRoll = roll.getText().toString();
            if (getRoll.isEmpty()) {
                roll.setError("Enter your roll");
                roll.requestFocus();
            } else {
                int rollInt = Integer.parseInt(getRoll); //convert string to integer
                rollView.setText(String.valueOf(rollInt));
                Toast.makeText(IntSave.this, "Saved", Toast.LENGTH_SHORT).show();

                //To store an integer value:
                SharedPreferences preferences = getSharedPreferences("MyPrefsInt", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("myIntKey", rollInt);
                editor.apply();
            }

        });

    }
}
