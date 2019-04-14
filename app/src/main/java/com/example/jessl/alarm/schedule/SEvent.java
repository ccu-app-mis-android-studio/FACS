package com.example.jessl.alarm.schedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jessl.alarm.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class SEvent extends AppCompatActivity {

    TextView titlepage, addid, adddesc, adddate;
    EditText iddoes, descdoes, datedoes;
    Button btnSaveTask, btnCancel;
    DatabaseReference reference;
    Integer doesNum = new Random().nextInt();
    String keydoes = Integer.toString(doesNum);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sevent);

        titlepage = findViewById(R.id.titlepage);

        addid = findViewById(R.id.addid);
        adddesc = findViewById(R.id.adddesc);
        adddate = findViewById(R.id.adddate);

        iddoes = findViewById(R.id.iddoes);
        descdoes = findViewById(R.id.descdoes);
        datedoes = findViewById(R.id.datedoes);

        btnSaveTask = findViewById(R.id.btnSaveTask);
        btnCancel = findViewById(R.id.btnCancel);

        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                // insert data to database
//                reference = FirebaseDatabase.getInstance().getReference().child("FACS_Events").
//                        child("s" + doesNum);
//                reference.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//
//                        dataSnapshot.getRef().child("iddoes").setValue(iddoes.getText().toString());
//                        dataSnapshot.getRef().child("descdoes").setValue(descdoes.getText().toString());
//                        dataSnapshot.getRef().child("datedoes").setValue(datedoes.getText().toString());
//                        dataSnapshot.getRef().child("keydoes").setValue(keydoes);
//
//                        Intent a = new Intent(SEvent.this,Schedule.class);
//                        startActivity(a);
//                    }
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
            }
        });
    }
}
