package com.example.trueledger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class internships_display extends AppCompatActivity {
    TextView intern_title,intern_desc;
    DatabaseReference reference;
    Animation fromsmall,fromnoting,togo;
    Button btn_submit;
    ImageButton btn_close,back_but;
    CardView confirmation;
    LinearLayout overbox;
    Button desc_but,requirement_but,training_but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internships_display);
        fromsmall = AnimationUtils.loadAnimation(this,R.anim.fromsmall);
        //id.setText(getIntent().getStringExtra("internship_id"));
        fromnoting = AnimationUtils.loadAnimation(this,R.anim.fromnothing);
        intern_title = findViewById(R.id.internship_title);
        btn_submit = findViewById(R.id.btn_submit);
        overbox = findViewById(R.id.overbox);
        btn_close = findViewById(R.id.btn_close);
        togo = AnimationUtils.loadAnimation(this,R.anim.togo);
        confirmation = findViewById(R.id.confirmation);
        intern_desc = findViewById(R.id.internship_desc);
        reference = FirebaseDatabase.getInstance().getReference().child("Internships").child(getIntent().getStringExtra("internship_id"));
        overbox.setAlpha(0);
        confirmation.setAlpha(0);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overbox.setAlpha(1);
                overbox.startAnimation(fromnoting);
                confirmation.setAlpha(1);
                confirmation.startAnimation(fromsmall);
            }
        });
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overbox.setAlpha(0);

            }
        });
        //for changinbf in between instruction
        training_but = findViewById(R.id.training_but);
        requirement_but = findViewById(R.id.requirement_but);
        desc_but = findViewById(R.id.desc_but);
        desc_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desc_but.setBackground(getResources().getDrawable(R.drawable.white_circle));
                training_but.setBackground(getResources().getDrawable(R.drawable.grey_circle));
                requirement_but.setBackground(getResources().getDrawable(R.drawable.grey_circle));
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String desc = dataSnapshot.child("description").getValue().toString();
                        intern_desc.setText(desc);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(),"Loading internships failed",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        training_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desc_but.setBackground(getResources().getDrawable(R.drawable.grey_circle));
                training_but.setBackground(getResources().getDrawable(R.drawable.white_circle));
                requirement_but.setBackground(getResources().getDrawable(R.drawable.grey_circle));
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String req = dataSnapshot.child("stipend").getValue().toString();
                        intern_desc.setText(req);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(),"Loading internships failed",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        requirement_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desc_but.setBackground(getResources().getDrawable(R.drawable.grey_circle));
                training_but.setBackground(getResources().getDrawable(R.drawable.grey_circle));
                requirement_but.setBackground(getResources().getDrawable(R.drawable.white_circle));
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String traning = dataSnapshot.child("requirement").getValue().toString();
                        intern_desc.setText(traning);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(),"Loading internships failed",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        //for back menu
        back_but = findViewById(R.id.back_but);
        back_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),dashboard.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getdata();
    }

    public void getdata(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String title = dataSnapshot.child("subtitle").getValue().toString();
                String desc = dataSnapshot.child("description").getValue().toString();
                intern_title.setText(title);
                intern_desc.setText(desc);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Loading internships failed",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),dashboard.class);
        startActivity(intent);
    }
}
