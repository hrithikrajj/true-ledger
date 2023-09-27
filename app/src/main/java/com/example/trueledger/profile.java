package com.example.trueledger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile extends AppCompatActivity {
    private FirebaseAuth fauth;
    private TextView account_name,account_email,task_comp,task_applied;
    private CircleImageView account_photo;
    CardView edu_dropdown_layout,skills_dropdown_layout,achievements_dropdown_layout,address_dropdown_layout;
    ImageButton edu_dropdown_but,skills_dropdown_but,achievements_dropdown_but,address_dropdown_but,back_but;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference noteref ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        account_email= findViewById(R.id.account_email);
        account_name = findViewById(R.id.account_name);
        // for click education drop down layout
        edu_dropdown_layout = findViewById(R.id.education_drop_down_layout);
        edu_dropdown_but = findViewById(R.id.education_drop_down_button);
        edu_dropdown_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edu_dropdown_layout.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(edu_dropdown_layout,new AutoTransition());
                    edu_dropdown_layout.setVisibility(View.VISIBLE);

                }else{
                    TransitionManager.beginDelayedTransition(edu_dropdown_layout,new AutoTransition());
                    edu_dropdown_layout.setVisibility(View.GONE);
                }
            }
        });
        // for click  address drop down layout

        address_dropdown_layout = findViewById(R.id.address_drop_down_layout);
        address_dropdown_but = findViewById(R.id.address_drop_down_button);
        address_dropdown_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(address_dropdown_layout.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(address_dropdown_layout,new AutoTransition());
                    address_dropdown_layout.setVisibility(View.VISIBLE);

                }else{
                    TransitionManager.beginDelayedTransition(address_dropdown_layout,new AutoTransition());
                    address_dropdown_layout.setVisibility(View.GONE);
                }
            }
        });
        // for click skills drop down layout
        skills_dropdown_layout = findViewById(R.id.skills_drop_down_layout);
        skills_dropdown_but = findViewById(R.id.skills_drop_down_button);
        skills_dropdown_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(skills_dropdown_layout.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(skills_dropdown_layout,new AutoTransition());
                    skills_dropdown_layout.setVisibility(View.VISIBLE);

                }else{
                    TransitionManager.beginDelayedTransition(skills_dropdown_layout,new AutoTransition());
                    skills_dropdown_layout.setVisibility(View.GONE);
                }
            }
        });
        // for click achievements drop down layout
        achievements_dropdown_layout = findViewById(R.id.achievements_drop_down_layout);
        achievements_dropdown_but = findViewById(R.id.achievements_drop_down_button);
        achievements_dropdown_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(achievements_dropdown_layout.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(achievements_dropdown_layout,new AutoTransition());
                    achievements_dropdown_layout.setVisibility(View.VISIBLE);

                }else{
                    TransitionManager.beginDelayedTransition(achievements_dropdown_layout,new AutoTransition());
                    achievements_dropdown_layout.setVisibility(View.GONE);
                }
            }
        });
        // for pressing bach and going back to home page
        back_but = findViewById(R.id.back_but);
        back_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back_to_home();
            }
        });
    }

    @Override
    public void onBackPressed() {
        back_to_home();
    }

    private void back_to_home(){
        Intent intent = new Intent(getApplicationContext(),dashboard.class);
        startActivity(intent);
     }

    @Override
    protected void onStart() {
        super.onStart();
        profile_load();
    }

    private void profile_load(){
        fauth = FirebaseAuth.getInstance();
        final String userid = fauth.getCurrentUser().getUid();
        final Uri photoUrl = fauth.getCurrentUser().getPhotoUrl();
        noteref = db.collection("users").document(userid);
        noteref.get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if(documentSnapshot.exists()){
                                account_photo = findViewById(R.id.account_photo);

                                Picasso.get().load(photoUrl).into(account_photo);
                                String name = documentSnapshot.getString("Name");
                                String email = documentSnapshot.getString("Email");
                                account_email.setText(email);
                                account_name.setText(name);
                                task_comp = findViewById(R.id.task_completed_number);
                                task_applied = findViewById(R.id.task_applied_number);
                                String tsk_comp = documentSnapshot.getString("Task Completed");
                                String tsk_applied = documentSnapshot.getString("Task Applied");
                                task_comp.setText("0");
                                task_applied.setText("0");
                            }else{
                                Toast.makeText(getApplicationContext(),"Profile not found",Toast.LENGTH_LONG).show();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Sign in failed",Toast.LENGTH_LONG).show();
                        }
                    });
     }
}
