package com.example.trueledger;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class task_approved extends AppCompatActivity {
    ImageView mainbac ;
    View redball;
    TextView alltask , taskcomp , bal;
    private float x1,x2,y1,y2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_approved);
        mainbac = findViewById(R.id.main_bac);
        redball = findViewById(R.id.red_circle);
        alltask = findViewById(R.id.internships);
        taskcomp = findViewById(R.id.task_completed);
        bal = findViewById(R.id.wallet);
        // click on listener on task completed
        alltask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),dashboard.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View,String>(redball,"image2Transition");
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(task_approved.this,pairs);
                startActivity(intent,activityOptions.toBundle());
            }
        });
        // click on listener on bal
        taskcomp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),task_completed.class);

                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View,String>(redball,"image2Transition");
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(task_approved.this,pairs);
                startActivity(intent,activityOptions.toBundle());
            }
        });
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(),dashboard.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View,String>(redball,"image2Transition");
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(task_approved.this,pairs);
        startActivity(intent,activityOptions.toBundle());
        startActivity(intent);

    }

    public boolean onTouchEvent(MotionEvent touchEvent) {
        switch (touchEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if (x1 < x2) {
                    Intent intent = new Intent(getApplicationContext(), task_completed.class);
                    Pair[] pairs = new Pair[1];
                    pairs[0] = new Pair<View, String>(redball, "image2Transition");
                    ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(task_approved.this, pairs);
                    startActivity(intent, activityOptions.toBundle());
                }
                break;
        }
        return false;
    }
}
