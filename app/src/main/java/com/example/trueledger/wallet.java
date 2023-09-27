package com.example.trueledger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class wallet extends AppCompatActivity {
    private ImageButton back_but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        //to go to home page
        back_but = findViewById(R.id.back_but);
        back_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to_go_homepage();
            }
        });
    }

    private void to_go_homepage(){
        Intent intent = new Intent(getApplicationContext(),dashboard.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        to_go_homepage();
    }
}
