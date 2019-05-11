package com.example.jessl.alarm.food;

import android.content.Intent;
import android.media.Image;
import android.media.ImageWriter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.jessl.alarm.R;
import com.example.jessl.alarm.food.foodmenu;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Food extends AppCompatActivity {
    private ImageButton menu;
    private ImageButton drink1;
    private ImageButton Food1;
    private TextView ResName;
    public ImageView menupic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        menu = (ImageButton) findViewById(R.id.menu1);
        drink1 = (ImageButton) findViewById(R.id.drinkpng);
        Food1 = (ImageButton) findViewById(R.id.foodpng);
        ResName = (TextView) findViewById(R.id.ResName);
        menupic = (ImageView) findViewById(R.id.menu1);

        showRandomFact();
        showRandomFact2();

        Food1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRandomFact();
                menu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(Food.this, foodmenu.class);
                        Bundle bundle = new Bundle();
                        int x = (factArray[0].getmImage1());
                        bundle.putInt("foodmenupic", x);

                        intent.putExtras(bundle);
                        // Start the SecondActivity
                        startActivity(intent);
                    }
                });
            }

        });

        drink1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRandomFact2();
                menu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(Food.this, foodmenu.class);
                        Bundle bundle = new Bundle();
                        int x = (factArray2[0].getmImage3());
                        bundle.putInt("foodmenupic", x);

                        intent.putExtras(bundle);
                        // Start the SecondActivity
                        startActivity(intent);
                    }
                });
            }
        });
    }


//        menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(Food.this, foodmenu.class);
//                Bundle bundle = new Bundle();
//                int x = (factArray[0].getmImage1());
//                bundle.putInt("foodmenupic", x );
//
//                intent.putExtras(bundle);
//                // Start the SecondActivity
//                startActivity(intent);
//            }
//        });
//    }

        /////食物隨機
        public void showRandomFact(){
            shuffleFacts();
            menu.setImageResource(factArray[0].getmImage());
            ResName.setText(factArray[0].getmFact());
        }

//        Facts f01 = new Facts(R.drawable.huangmama, "黃媽媽", R.drawable.huangmenu,"0946548546");
        Facts f02 = new Facts(R.drawable.test1, "地中海炒飯", R.drawable.dizonghai,"46546489546 ");
        Facts f03 = new Facts(R.drawable.wangji, "旺記", R.drawable.test1,"5465465165454");
        Facts f04 = new Facts(R.drawable.lamian, "富成屋拉麵", R.drawable.lamianmenu,"4646546546546");
        Facts f05 = new Facts(R.drawable.aka, "a咖平價鐵板", R.drawable.akamenu,"4654659865465");
        Facts f06 = new Facts(R.drawable.hong,"紅樓極麺",R.drawable.hongmenu,"6549546544");

        Facts[] factArray = new Facts[]{
                f06, f02, f03, f04, f05
        };

        public void shuffleFacts () {
            Collections.shuffle(Arrays.asList(factArray));
        }
/////飲料隨機

        public void showRandomFact2 () {
            shuffleFacts2();
            menu.setImageResource(factArray2[0].getmImage2());
            ResName.setText(factArray2[0].getmFact2());
        }

        Facts2 d01 = new Facts2(R.drawable.guozhen, "果真", R.drawable.guozhenmenu);
        Facts2 d02 = new Facts2(R.drawable.qilixiang, "七里香", R.drawable.aka);
        Facts2 d03 = new Facts2(R.drawable.fresh, "鮮茶道", R.drawable.aka);

        Facts2[] factArray2 = new Facts2[]{
                d01, d02, d03,
        };

        public void shuffleFacts2 () {
            Collections.shuffle(Arrays.asList(factArray2));
        }
    }
