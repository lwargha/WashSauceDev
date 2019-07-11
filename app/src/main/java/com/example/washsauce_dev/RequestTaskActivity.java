package com.example.washsauce_dev;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.BoringLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class RequestTaskActivity extends AppCompatActivity {

    private EditText loads;
    private RadioButton sSmall;
    private RadioButton sMedium;
    private RadioButton sLarge;
    private RadioButton kCloths;
    private RadioButton kBeddingTowel;
    private RadioButton kOther;
    private RadioButton cNormalDirty;
    private RadioButton cMuddy;
    private RadioButton cStained;
    private EditText notes;
    private String requestorEmail;
    private String userID;
    public  Button clear;
    private Button buttonConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_task);

        // Read in all user inputs
        loads         = findViewById(R.id.numberLoads);
        sSmall        = findViewById(R.id.radio_one);
        sMedium       = findViewById(R.id.radio_two);
        sLarge        = findViewById(R.id.radio_three);
        kCloths       = findViewById(R.id.radio_four);
        kBeddingTowel = findViewById(R.id.radio_five);
        kOther        = findViewById(R.id.radio_six);
        cNormalDirty  = findViewById(R.id.radio_seven);
        cMuddy        = findViewById(R.id.radio_eight);
        cStained      = findViewById(R.id.radio_nine);
        notes         = findViewById(R.id.editText5);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        userID = preferences.getString("USER_ID_KEY", "");
        requestorEmail = preferences.getString("EMAIL_KEY", "");
        clear = findViewById(R.id.clear);

        // Make sure the submit button doesn't work if any required field is empty
        buttonConfirm = findViewById(R.id.request);
        // Call watcher to turn on button again once all required fields are filled


        //clear button
        clear.setOnClickListener(v -> {
            loads.setText("");
            notes.setText("");
            sSmall.setChecked(false);
            sMedium.setChecked(false);
            sLarge.setChecked(false);
            kCloths.setChecked(false);
            kBeddingTowel.setChecked(false);
            kOther.setChecked(false);
            cNormalDirty.setChecked(false);
            cMuddy.setChecked(false);
            cStained.setChecked(false);
        });

    }


    public void submitCustomerRequest(View v) {

        String checkLoads = loads.getText().toString().trim();
        // Convert all checks into a boolean
        Boolean load = !checkLoads.isEmpty();
        Boolean size = (sSmall.isChecked() || sMedium.isChecked() || sLarge.isChecked());
        Boolean kind = (kCloths.isChecked() || kBeddingTowel.isChecked() || kOther.isChecked());
        Boolean conditionCheck = (cNormalDirty.isChecked() || cMuddy.isChecked() || cStained.isChecked());

        if (!load || !size || !kind || !conditionCheck) {
            Toast.makeText(RequestTaskActivity.this, "Please, fill out all fields",
                    Toast.LENGTH_SHORT).show();
        } else {  Integer iLoads = Integer.parseInt(loads.getText().toString());

            Boolean iSmall        = sSmall.isChecked();
            Boolean iMedium       = sMedium.isChecked();
            Boolean iLarge        = sLarge.isChecked();
            Boolean iCloths       = kCloths.isChecked();
            Boolean iBeddingTowel = kBeddingTowel.isChecked();
            Boolean iOther        = kOther.isChecked();
            Boolean iNormalDirty   = cNormalDirty.isChecked();
            Boolean iMuddy        = cMuddy.isChecked();
            Boolean iStained      = cStained.isChecked();

            String iNotes         = notes.getText().toString();

            String loadSize = iSmall ? "Small" : iMedium ? "Medium" : iLarge ? "Large" : "";
            String type = iCloths ? "Cloths" : iBeddingTowel ? "Bedding" : iOther ? "Other" : "";
            String condition = iNormalDirty ? "Normal" : iMuddy ? "Muddy" : iStained ? "Stained" : "";
            java.util.Date date = new java.util.Date();

            Task t = new Task(date.toString(), iNotes, loadSize, this.requestorEmail, iLoads, condition, type);
            DataBaseWriter writeTask = new DataBaseWriter(this);
            writeTask.addNewTask(t);}

        // Use boolean to turn on submit button if all required areas are filled in


        // Send user input to the database


    }



}
