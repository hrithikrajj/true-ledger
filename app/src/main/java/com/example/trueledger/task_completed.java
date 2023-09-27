package com.example.trueledger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import at.grabner.circleprogress.CircleProgressView;
import at.grabner.circleprogress.Direction;


public class task_completed extends AppCompatActivity {
    View redball;
    private float x1,x2,y1,y2;
    ImageView mainbac;
    Button close_but1,close_but2,close_but3;
    RelativeLayout overbox;
    TextView alltask , taskcomp , bal;
    CircleProgressView circleView,circleView2,circleView3;
    CardView task_completed_tab,task_applied_tab,task_failed_tab;
    Animation fromsmall,fromnoting;
    LinearLayout cardView1,cardView2,cardView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_completed);
        mainbac = findViewById(R.id.main_bac);
        alltask = findViewById(R.id.internships);
        redball = findViewById(R.id.red_circle);
        taskcomp = findViewById(R.id.task_completed);
        bal = findViewById(R.id.wallet);
        // click on listener on task completed
        alltask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),dashboard.class);

                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View,String>(redball,"image2Transition");
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(task_completed.this,pairs);
                startActivity(intent,activityOptions.toBundle());
            }
        });
        // click on listener on bal
        bal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), task_approved.class);

                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View,String>(redball,"image2Transition");
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(task_completed.this,pairs);
                startActivity(intent,activityOptions.toBundle());
            }
        });
        //circle progress bar
        circleView = findViewById(R.id.circleView);
        circleViewattr(100,32);
        circleView2 = findViewById(R.id.circleView2);
        circleViewattr2(100,49);
        circleView3 = findViewById(R.id.circleView3);
        circleViewattr3(100,0);
        //card view clickable for task completed
        cardView1 = findViewById(R.id.cardView1);
        overbox = findViewById(R.id.overbox1);
        overbox.setAlpha(0);
        task_completed_tab = findViewById(R.id.task_completed_tab);
        task_completed_tab.setAlpha(0);
        fromsmall = AnimationUtils.loadAnimation(this,R.anim.fromsmall);
        fromnoting = AnimationUtils.loadAnimation(this,R.anim.fromnothing);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task_applied_tab.setVisibility(View.GONE);
                task_failed_tab.setVisibility(View.GONE);
                overbox.setAlpha(1);
                overbox.startAnimation(fromnoting);
                task_completed_tab.setAlpha(1);
                task_completed_tab.startAnimation(fromsmall);
            }
        });
        close_but1 = findViewById(R.id.close_but1);
        close_but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overbox.setAlpha(0);
            }
        });
        //card view clickable for task applied
        cardView3 = findViewById(R.id.cardView3);
        task_applied_tab = findViewById(R.id.task_applied_tab);
        task_applied_tab.setAlpha(0);
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                task_completed_tab.setVisibility(View.GONE);
                task_failed_tab.setVisibility(View.GONE);
                overbox.setAlpha(1);
                overbox.startAnimation(fromnoting);
                task_applied_tab.setAlpha(1);
                task_applied_tab.startAnimation(fromsmall);
            }
        });
        close_but3 = findViewById(R.id.close_but3);
        close_but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overbox.setAlpha(0);
            }
        });
        // card view clickable for task failed
        cardView2 = findViewById(R.id.cardView2);
        task_failed_tab = findViewById(R.id.task_failed_tab);
        task_failed_tab.setAlpha(0);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                task_applied_tab.setVisibility(View.GONE);
                task_completed_tab.setVisibility(View.GONE);
                overbox.setAlpha(1);
                overbox.startAnimation(fromnoting);
                task_failed_tab.setAlpha(1);
                task_failed_tab.startAnimation(fromsmall);
            }
        });
        close_but2 = findViewById(R.id.close_but2);
        close_but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overbox.setAlpha(0);

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),dashboard.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View,String>(redball,"image2Transition");
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(task_completed.this,pairs);
        startActivity(intent,activityOptions.toBundle());
        startActivity(intent);

    }
    private void circleViewattr(int total_value, int value){
        circleView.setMaxValue(total_value);
        circleView.setValue(0);
        circleView.setValueAnimated(value);
        circleView.setDirection(Direction.CW);
        circleView.setUnit("%");
        circleView.setUnitVisible(true);
        circleView.setTextSize(30);
        circleView.setUnitSize(25);
        circleView.setBarColor(getResources().getColor(R.color.black));
        circleView.setRimColor(getResources().getColor(R.color.grey));
        circleView.setBarWidth(4);
        circleView.setRimWidth(2);
        circleView.setInnerContourColor(getResources().getColor(R.color.white));
        circleView.setOuterContourColor(getResources().getColor(R.color.white));
        circleView.setInnerContourSize(0);
        circleView.setInnerContourSize(0);
    }
    private void circleViewattr2(int total_value, int value){
        circleView2.setMaxValue(total_value);
        circleView2.setValue(0);
        circleView2.setValueAnimated(value);
        circleView2.setDirection(Direction.CW);
        circleView2.setUnit("%");
        circleView2.setUnitVisible(true);
        circleView2.setTextSize(30);
        circleView2.setUnitSize(25);
        circleView2.setBarColor(getResources().getColor(R.color.black));
        circleView2.setRimColor(getResources().getColor(R.color.grey));
        circleView2.setBarWidth(4);
        circleView2.setRimWidth(2);
        circleView2.setInnerContourColor(getResources().getColor(R.color.white));
        circleView2.setOuterContourColor(getResources().getColor(R.color.white));
        circleView2.setInnerContourSize(0);
        circleView2.setInnerContourSize(0);
    }
    private void circleViewattr3(int total_value, int value){
        circleView3.setMaxValue(total_value);
        circleView3.setValue(0);
        circleView3.setValueAnimated(value);
        circleView3.setDirection(Direction.CW);
        circleView3.setUnit("%");
        circleView3.setUnitVisible(true);
        circleView3.setTextSize(30);
        circleView3.setUnitSize(25);
        circleView3.setBarColor(getResources().getColor(R.color.black));
        circleView3.setRimColor(getResources().getColor(R.color.grey));
        circleView3.setBarWidth(4);
        circleView3.setRimWidth(2);
        circleView.setInnerContourColor(getResources().getColor(R.color.white));
        circleView3.setOuterContourColor(getResources().getColor(R.color.white));
        circleView3.setInnerContourSize(0);
        circleView3.setInnerContourSize(0);
    }
    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1 < x2){
                    Intent intent = new Intent(getApplicationContext(),dashboard.class);
                    Pair[] pairs = new Pair[1];
                    pairs[0] = new Pair<View,String>(redball,"image2Transition");
                    ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(task_completed.this,pairs);
                    startActivity(intent,activityOptions.toBundle());
            }else if(x1 > x2){
                    Intent intent = new Intent(getApplicationContext(),task_approved.class);
                    Pair[] pairs = new Pair[1];
                    pairs[0] = new Pair<View,String>(redball,"image2Transition");
                    ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(task_completed.this,pairs);
                    startActivity(intent,activityOptions.toBundle());
            }
            break;
        }
        return false;
    }
}
