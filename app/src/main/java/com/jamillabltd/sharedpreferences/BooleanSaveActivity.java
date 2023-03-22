package com.jamillabltd.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class BooleanSaveActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boolean_save);


        //TODO: keep me login - SharedPreferences
        CheckBox checkBox = findViewById(R.id.checkBoxId);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Save the state of the CheckBox
            SharedPreferences sharedPreferences = getSharedPreferences("myPrefsCheckBox", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isChecked", isChecked);
            editor.apply();

            if (checkBox.isChecked()) {
                // CheckBox is checked
                Toast.makeText(BooleanSaveActivity.this, "checked", Toast.LENGTH_SHORT).show();
            } else {
                // CheckBox is not checked
                Toast.makeText(BooleanSaveActivity.this, "unchecked", Toast.LENGTH_SHORT).show();
            }
        });

        // Load the saved state of the CheckBox
        SharedPreferences getCheckOrNo = getSharedPreferences("myPrefsCheckBox", MODE_PRIVATE);
        boolean isChecked = getCheckOrNo.getBoolean("isChecked", false);
        checkBox.setChecked(isChecked);



        //TODO: select gender - SharedPreferences
        //radio group - select gender
        RadioGroup radioGroup = findViewById(R.id.radioGroupId);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = radioGroup.findViewById(checkedId);
            String selectedGender = radioButton.getText().toString();
            Toast.makeText(this, "Gender:"+selectedGender, Toast.LENGTH_SHORT).show();
        });


       Button saveGender = findViewById(R.id.saveGenderId);
       saveGender.setOnClickListener(v -> {
           // get the selected gender value from the radio group
           //RadioGroup radioGroup = findViewById(R.id.radioGroupId);
           int selectedGenderId = radioGroup.getCheckedRadioButtonId();
           if (selectedGenderId == -1) {
               // no RadioButton is selected, display an error message
               Toast.makeText(getApplicationContext(), "Please select a gender", Toast.LENGTH_SHORT).show();
               return;
           }

           RadioButton selectedGenderRadioButton = findViewById(selectedGenderId);
           String selectedGender = selectedGenderRadioButton.getText().toString();


           // Save the selected gender value to SharedPreferences
           SharedPreferences sharedPreferencesGender = getSharedPreferences("MyPrefsGender", MODE_PRIVATE);
           SharedPreferences.Editor editor = sharedPreferencesGender.edit();
           editor.putString("gender", selectedGender);
           editor.apply();

           Toast.makeText(BooleanSaveActivity.this, "Saved - "+selectedGender, Toast.LENGTH_SHORT).show();

       });

       //retrieve the value of the gender
        SharedPreferences getSharedPreferencesGender = getSharedPreferences("MyPrefsGender", MODE_PRIVATE);
        String savedGender = getSharedPreferencesGender.getString("gender", null);
        if (savedGender != null) {
            // A gender value has been saved
            RadioButton savedGenderRadioButton = null;
            if (savedGender.equals("Male")) {
                savedGenderRadioButton = findViewById(R.id.maleRadioButton);
            } else if (savedGender.equals("Female")) {
                savedGenderRadioButton = findViewById(R.id.femaleRadioButton);
            }
            if (savedGenderRadioButton != null) {
                savedGenderRadioButton.setChecked(true);
            }
        }






        //TODO: switch on of - SharedPreferences
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch booleanSwitch = findViewById(R.id.boolean_switch_id);

        Button save_switch = findViewById(R.id.save_switch_id);
        save_switch.setOnClickListener(v -> {

            boolean switchState = booleanSwitch.isChecked();
            // Save the switch state to SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsSwitch", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("switch_state", switchState);
            editor.apply();
            Toast.makeText(getApplicationContext(), "Switch state saved: " + switchState, Toast.LENGTH_SHORT).show();
        });

        //retrieve the value of the switch
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsSwitch", MODE_PRIVATE);
        boolean savedSwitchState = sharedPreferences.getBoolean("switch_state", false);

        //Switch booleanSwitch = findViewById(R.id.boolean_switch);
        booleanSwitch.setChecked(savedSwitchState);
        Toast.makeText(this, ""+savedSwitchState, Toast.LENGTH_SHORT).show();




        //TODO: textColor - SharedPreferences
        //radio group - select gender
        RadioGroup radioGroupColor = findViewById(R.id.radioColorGroupId);
        radioGroupColor.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButtonColor = radioGroupColor.findViewById(checkedId);
            String selectedColor = radioButtonColor.getText().toString();

            //SharedPreferences
            // Save the selected gender value to SharedPreferences
            SharedPreferences sharedPreferencesColor = getSharedPreferences("myPrefsColor", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferencesColor.edit();

            TextView colorText = findViewById(R.id.colorTextId);
            if (selectedColor.equals("Red")){
                colorText.setTextColor(Color.RED);
                editor.putInt("color", Color.RED);
            }else if (selectedColor.equals("Blue")){
                colorText.setTextColor(Color.BLUE);
                editor.putInt("color", Color.BLUE);
            }else{
                colorText.setTextColor(Color.BLACK);
                editor.putInt("color", Color.BLACK);
            }
            editor.apply();

            Toast.makeText(this, "Color:"+selectedColor, Toast.LENGTH_SHORT).show();
        });

        //retrieve the value of the gender
        SharedPreferences sharedPreferencesColor = getSharedPreferences("myPrefsColor", MODE_PRIVATE);
        int color = sharedPreferencesColor.getInt("color", Color.BLACK);

        TextView colorText = findViewById(R.id.colorTextId);
        colorText.setTextColor(color);

        //RadioGroup radioGroupColor = findViewById(R.id.radioColorGroupId);
        if (color == Color.RED) {
            radioGroupColor.check(R.id.redColorId);
        } else if (color == Color.BLUE) {
            radioGroupColor.check(R.id.blueColorId);
        } else {
            radioGroupColor.check(R.id.blackColorId);
            radioGroupColor.check(R.id.blackColorId); // Set the default RadioButton to black

        }


        //TODO: Text Size - SharedPreferences
        Spinner textSizeSpinner = findViewById(R.id.textSizeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.text_sizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        textSizeSpinner.setAdapter(adapter);

        SharedPreferences sharedPreferencesTextSize = getSharedPreferences("myPrefsTextSize", MODE_PRIVATE);
        int textSize = sharedPreferencesTextSize.getInt("textSize", 16); // Default text size is 16

        textSizeSpinner.setSelection(adapter.getPosition(Integer.toString(textSize)));

        textSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int selectedTextSize = Integer.parseInt(parent.getItemAtPosition(position).toString());

                SharedPreferences.Editor editor = sharedPreferencesTextSize.edit();
                editor.putInt("textSize", selectedTextSize);
                editor.apply();

                TextView textView = findViewById(R.id.sizeTextId);
                textView.setTextSize(selectedTextSize);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });







    }
}