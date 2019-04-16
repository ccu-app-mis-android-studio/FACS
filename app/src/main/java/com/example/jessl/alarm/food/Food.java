package com.example.jessl.alarm.food;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.example.jessl.alarm.R;
import com.example.jessl.alarm.foodmenu;

import java.util.Random;


public class Food extends AppCompatActivity {
    ImageButton menu;
    ImageButton drink1;
    ImageButton Food1;
    Random r, r2;
    Integer[] images = {
            R.drawable.huangmama,
            R.drawable.dizhonghao,
            R.drawable.wangji,
            R.drawable.lamian,

    };


    Integer[] images2 = {
            R.drawable.guozhen,
            R.drawable.qilixiang,
            R.drawable.fresh,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        menu = (ImageButton) findViewById(R.id.menu1);
//        menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(Food.this, foodmenu.class);
//                startActivity(i);
//            }
//        });
        drink1 = (ImageButton) findViewById(R.id.drinkpng);
        Food1 = (ImageButton) findViewById(R.id.foodpng);

        r = new Random();
        r2 = new Random();

        drink1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.setImageResource(images2[r2.nextInt(images2.length)]);
            }
        });
        Food1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.setImageResource(images[r.nextInt(images.length)]);
            }
        });
    }
}

