package com.example.jessl.alarm.food;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.jessl.alarm.R;
import com.github.clans.fab.FloatingActionButton;

import android.view.ScaleGestureDetector;
import android.view.MotionEvent;

public class foodmenu extends AppCompatActivity {

    private FloatingActionButton icon1;
    private FloatingActionButton icon2;
    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;
    private ImageView menupicture;
    private float mPosX;
    private float mPosY;

    private float mLastTouchX;
    private float mLastTouchY;
    private int mActivePointerId = INVALID_POINTER_ID;
    private static final int INVALID_POINTER_ID = -1;

    private FloatingActionButton dial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodmenu);
        menupicture = (ImageView) findViewById(R.id.foodmenupic);
        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
        dial = (FloatingActionButton) findViewById(R.id.iconmenu1);

        //get intent
        Bundle bundle = this.getIntent().getExtras();
        int menupicfood = bundle.getInt("foodmenupic");
        String foodmenucall = bundle.getString("foodmenucall");
        menupicture.setImageResource(menupicfood);

        dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                dialContactPhone(foodmenucall);

            }
        });

//        icon1 = (FloatingActionButton) findViewById(R.id.iconmenu1);
//        icon2 = (FloatingActionButton) findViewById(R.id.iconmenu2);


//        icon1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(foodmenu.this, "Clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        icon2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(foodmenu.this, "Clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    // this redirects all touch events in the activity to the gesture detector
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mScaleGestureDetector.onTouchEvent(event);

        final int action = event.getAction();
        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {
                final float x = event.getX();
                final float y = event.getY();

                mLastTouchX = x;
                mLastTouchY = y;
                mActivePointerId = event.getPointerId(0);
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                final int pointerIndex = event.findPointerIndex(mActivePointerId);
                final float x = event.getX(pointerIndex);
                final float y = event.getY(pointerIndex);

                // Only move if the ScaleGestureDetector isn't processing a gesture.
                if (!mScaleGestureDetector.isInProgress()) {
                    final float dx = x - mLastTouchX;
                    final float dy = y - mLastTouchY;

                    mPosX += dx;
                    mPosY += dy;
                }

                mLastTouchX = x;
                mLastTouchY = y;

                break;
            }

            case MotionEvent.ACTION_UP: {
                mActivePointerId = INVALID_POINTER_ID;
                break;
            }

            case MotionEvent.ACTION_CANCEL: {
                mActivePointerId = INVALID_POINTER_ID;
                break;
            }

            case MotionEvent.ACTION_POINTER_UP: {
                final int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK)
                        >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                final int pointerId = event.getPointerId(pointerIndex);
                if (pointerId == mActivePointerId) {
                    // This was our active pointer going up. Choose a new
                    // active pointer and adjust accordingly.
                    final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                    mLastTouchX = event.getX(newPointerIndex);
                    mLastTouchY = event.getY(newPointerIndex);
                    mActivePointerId = event.getPointerId(newPointerIndex);
                }
                break;
            }
        }

        return true;
    }


    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        // when a scale gesture is detected, use it to resize the image
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector){
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            menupicture.setScaleX(mScaleFactor);
            menupicture.setScaleY(mScaleFactor);
            return true;
        }
    }


    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }
}