package com.jamillabltd.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StringSave extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_save);



        //To retrieve a string value:
        SharedPreferences getPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String myString = getPreferences.getString("myStringKey", "Your Saved String will show here!");
        //set saved value from sharePreferences
        TextView nameView = findViewById(R.id.nameViewId);
        nameView.setText(myString);

        //initialize
        EditText name = findViewById(R.id.nameEditTextId);
        Button save = findViewById(R.id.saveButtonId);
        save.setOnClickListener(view -> {

            String getName = name.getText().toString();
            if (getName.isEmpty()) {
                name.setError("Enter your name");
                name.requestFocus();
            }else{
                nameView.setText(getName);
                Toast.makeText(StringSave.this, "Saved", Toast.LENGTH_SHORT).show();

                //To store a string value:
                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("myStringKey", getName);
                editor.apply();
            }

        });


    }
}