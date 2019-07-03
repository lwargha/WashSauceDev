package com.example.washsauce_dev;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class DataBaseWriter {
    private static final String TAG = "MainActivity";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    Activity activity;

    public DataBaseWriter(Activity activity) {
        this.activity = activity;
    }

    public void addNewUser(User data) {
            db.collection("users")
                    .add(data)
                    .addOnSuccessListener(documentReference -> Log.w(TAG, "Document added with success!"))
                    .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));
    }

    public void addNewTask(Task data) {
        db.collection("tasks")
                .add(data)
                .addOnSuccessListener(documentReference -> Log.w(TAG, "Document added with success!"))
                .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));
    }
}
