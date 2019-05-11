package com.example.jessl.alarm.food;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.jessl.alarm.R;
import com.github.clans.fab.FloatingActionButton;

public class foodmenu extends AppCompatActivity {

    private FloatingActionButton icon1;
    private FloatingActionButton icon2;
//    private Integer food;
//    private ImageView menupicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodmenu);
        ImageView menupicture = (ImageView) findViewById(R.id.foodmenupic);

        //get intent

        Bundle bundle = this.getIntent().getExtras();
        int menupicfood = bundle.getInt("foodmenupic");
//        int menupicdrink = bundle.getInt("drinkmenupic");
//        int menupicdrink = bundle1.getInt("drinkmenupic");


//        Facts f01 = new Facts(R.drawable.huangmama, "黃媽媽", R.drawable.huangmenu,"0946548546");
//        Facts f02 = new Facts(R.drawable.test1, "地中海炒飯", R.drawable.dizonghai,"46546489546 ");
//        Facts f03 = new Facts(R.drawable.wangji, "旺記", R.drawable.test1,"5465465165454");
//        Facts f04 = new Facts(R.drawable.lamian, "富成屋拉麵", R.drawable.lamianmenu,"4646546546546");
//        Facts f05 = new Facts(R.drawable.aka, "a咖平價鐵板", R.drawable.akamenu,"4654659865465");
//        //Facts f06 = new Facts(R.drawable.hong,"紅樓極麺",R.drawable.hongmenu);
//
//        Facts[] factArray = new Facts[]{
//                f01, f02, f03, f04, f05
//        };
//
//        for (int foodmenu = 0 ; foodmenu<5;foodmenu++)
//        {
//
//          if(factArray[foodmenu].equals(menupicfood))
//          {
//              int cc =foodmenu;
//          }
//        }


        menupicture.setImageResource(menupicfood);
//        menupicture.setImageResource(menupicdrink);

      //  if food.menu is showRandomFact() then  int menupic = bundle1.getInt("foodmenupic"); , menupicture.setImageResource(menupic);
       // else food.menu is showRandomFact2() then    int drinkpic = bundle1.getInt("drinkmenupic"); , menupicture.setImageResource(drinkpic);

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

//    public void setMenupicture(ImageView menupicture) {
//        this.menupicture = menupicture;
//    }
}

