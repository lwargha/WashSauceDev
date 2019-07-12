package com.example.washsauce_dev;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/*******************************************
 * This activity is the homepage for the washer user. Here, the washer is able
 * to review the customer request they are currently working on, or select a
 * request if they don't have one yet. These requests are coming from the database.
 **/




public class WasherHomeActivity extends AppCompatActivity implements INotify3TasksReceived {
    private TextView name;
    private TextView request1;
    private TextView request2;
    private TextView request3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_washer_home);

        RadioButton b1;
        RadioButton b2;
        RadioButton b3;

        //Button buttonConfirm;
        //Button clear;

        b1 = findViewById(R.id.radioRequest1);
        b2 = findViewById(R.id.radioRequest2);
        b3 = findViewById(R.id.radioRequest3);
        name = findViewById(R.id.name);
        request1 = findViewById(R.id.request1);
        request2 = findViewById(R.id.request2);
        request3 = findViewById(R.id.request3);
        updateUI();
        DataBaseReader d = new DataBaseReader(this);
        d.setTasksReceived(this);
        d.readTasksByLocation("Rexburg");
    }

    private void updateUI() {
        Intent i = getIntent();
        String nameUpdate = i.getStringExtra("NAME_KEY");
        runOnUiThread(() -> name.setText("Welcome " + nameUpdate + "!"));
    }

    public void customerRequests(View view) {

    }

    public void notifyTasksResult(List<Task> taskList) {
        int size = taskList.size();

        if (size > 0) {
            if (taskList.get(0) != null) {
                Task task1 = taskList.get(0);
                request1.setText(task1.numberOfLoads + " " + task1.loadType + " loads requested by " + task1.requestorEmail + " in Rexburg ID");
            }

            if (taskList.get(1) != null) {
                Task task1 = taskList.get(1);
                request2.setText(task1.numberOfLoads + " " + task1.loadType + " loads requested by " + task1.requestorEmail + " in Rexburg ID");
            }

            if (taskList.get(2) != null) {
                Task task1 = taskList.get(1);
                request3.setText(task1.numberOfLoads + " " + task1.loadType + " loads requested by " + task1.requestorEmail + " in Rexburg ID");
            }

        } else {
            Toast.makeText(this, "It didn't work",
                    Toast.LENGTH_LONG).show();
            }

        }
    }

