package com.example.trueledger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo = findViewById(R.id.logo);
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.app_opening_animation);
        logo.setAnimation(anim);
        final Intent intent = new Intent(this, loginpage.class);
        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(3000);

                }catch (InterruptedException e){ e.printStackTrace();}
                finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }
}
