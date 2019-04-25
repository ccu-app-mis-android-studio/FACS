package com.example.jessl.alarm.food;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.jessl.alarm.R;
import com.example.jessl.alarm.food.foodmenu;
import java.util.Arrays;
import java.util.Collections;


public class Food extends AppCompatActivity {
   private ImageButton menu;
   private ImageButton drink1;
   private ImageButton Food1;
   private TextView ResName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        menu = (ImageButton) findViewById(R.id.menu1);
        drink1 = (ImageButton) findViewById(R.id.drinkpng);
        Food1 = (ImageButton) findViewById(R.id.foodpng);
        ResName = (TextView) findViewById(R.id.ResName);

        showRandomFact();
        showRandomFact2();

        Food1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRandomFact();
            }
        });

        drink1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRandomFact2();
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Food.this, foodmenu.class);
                startActivity(i);
            }
        });



    }

        public void showRandomFact() {
            shuffleFacts();
            menu.setImageResource(factArray[0].getmImage());
            ResName.setText(factArray[0].getmFact());
        }

        Facts f01 = new Facts(R.drawable.huangmama, "黃媽媽");
        Facts f02 = new Facts(R.drawable.test1, "地中海炒飯");
        Facts f03 = new Facts(R.drawable.wangji, "旺記");
        Facts f04 = new Facts(R.drawable.lamian, "富成屋拉麵");

        Facts[] factArray = new Facts[]{
            f01,f02,f03,f04
        };

        public void shuffleFacts(){
            Collections.shuffle(Arrays.asList(factArray));
        }


        public void showRandomFact2() {
        shuffleFacts2();
        menu.setImageResource(factArray2[0].getmImage2());
        ResName.setText(factArray2[0].getmFact2());
    }

        Facts2 d01 = new Facts2(R.drawable.guozhen, "果真");
        Facts2 d02 = new Facts2(R.drawable.qilixiang, "七里香");
        Facts2 d03 = new Facts2(R.drawable.fresh, "鮮茶道");

        Facts2[] factArray2 = new Facts2[]{
            d01,d02,d03,
        };

        public void shuffleFacts2(){
            Collections.shuffle(Arrays.asList(factArray2));
        }
    }








