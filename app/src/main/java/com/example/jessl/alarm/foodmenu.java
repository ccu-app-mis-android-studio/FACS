package com.example.jessl.alarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.github.clans.fab.FloatingActionButton;

public class foodmenu extends AppCompatActivity {

    private FloatingActionButton icon1;
    private FloatingActionButton icon2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodmenu);

        icon1 = (FloatingActionButton) findViewById(R.id.iconmenu1);
        icon2 = (FloatingActionButton) findViewById(R.id.iconmenu2);

        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(foodmenu.this, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(foodmenu.this, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

