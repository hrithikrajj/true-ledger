package com.example.trueledger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class dashboard extends AppCompatActivity {
    private FirebaseAuth fauth;
    ImageView mainbac ;
    ImageButton sidemenu_but,sidemenu_close;
    View redball;
    private long backPressedtime;
    LinearLayout sidemmenu;
    TextView alltask , taskcomp , bal,btn_home,btn_profile,btn_wallet,btn_signout;
    DatabaseReference reference;
    ArrayList<Internships> list;
    RecyclerView recyclerView;
    FirebaseRecyclerAdapter<Internships, BlogViewHolder> adapter;
    private float x1,y1,x2,y2;
    private FirebaseFirestore fstore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        recyclerView = findViewById(R.id.myRecycler);
        reference = FirebaseDatabase.getInstance().getReference("Internships");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sidemenu_close = findViewById(R.id.sidemenu_close_but);
        sidemmenu = findViewById(R.id.sidemenu);
        sidemenu_but = findViewById(R.id.sidemenu_but);
        list = new ArrayList<Internships>();
        redball = findViewById(R.id.red_circle);
        mainbac = findViewById(R.id.main_bac);
        alltask = findViewById(R.id.internships);
        taskcomp = findViewById(R.id.task_completed);
        bal = findViewById(R.id.wallet);
        // click on listener on task completed
        taskcomp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),task_completed.class);

                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View,String>(redball,"image2Transition");
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(dashboard.this,pairs);
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
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(dashboard.this,pairs);
                startActivity(intent,activityOptions.toBundle());
            }
        });
        // click to open side menu
        sidemenu_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sidemmenu.animate().translationX(0);
                recyclerView.setVisibility(recyclerView.GONE);
            }
        });
        //click to close side menu
        sidemenu_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sidemmenu.animate().translationX(-1100);
                recyclerView.setVisibility(recyclerView.VISIBLE);
            }
        });
        //side menu click to ome page
        btn_home = findViewById(R.id.btn_home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sidemmenu.animate().translationX(-1100);
                recyclerView.setVisibility(recyclerView.VISIBLE);
            }
        });
        //side menu click to go to profile page
        btn_profile = findViewById(R.id.btn_profile);
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),profile.class);
                startActivity(intent1);
            }
        });
        //click to o on wallet
        btn_wallet = findViewById(R.id.btn_wallet);
        btn_wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),wallet.class);
                startActivity(intent);
            }
        });
        //btton to sign out
        btn_signout = findViewById(R.id.btn_signout);
        btn_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance()
                        .signOut(getApplicationContext())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                startActivity(new Intent(getApplicationContext(),loginpage.class));
                            }
                        });
            }
        });
    }

    @Override
    protected void onStart() {

        super.onStart();
        toDatabase();
        final FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Internships>().setQuery(reference,Internships.class).build();
         adapter = new FirebaseRecyclerAdapter<Internships , BlogViewHolder>(options){

             @NonNull
             @Override
             public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                 View view = LayoutInflater.from(parent.getContext())
                         .inflate(R.layout.cardview,parent,false);
                 return new BlogViewHolder(view);
             }

             @Override
             protected void onBindViewHolder(@NonNull BlogViewHolder holder, int position, @NonNull final Internships model) {
                    holder.txttitle.setText(model.getTitle());
                     holder.txtsubtitle.setText(model.getSubtitle());
                     Picasso.get().load(model.getImage()).into(holder.image);
                     holder.priceinfo.setText(model.getPriceinfo());
                     holder.card.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             Intent intent = new Intent(getApplicationContext(), internships_display.class);
                             intent = intent.putExtra("internship_id", model.getId());
                             startActivity(intent);
                         }
                     });
                 }


             @Override
             public int getItemCount() {
                 return super.getItemCount();
             }
         };
         adapter.startListening();
         adapter.notifyDataSetChanged();
         recyclerView.setAdapter(adapter);
    }
    public static class BlogViewHolder extends RecyclerView.ViewHolder{
            public  TextView txttitle;
            public TextView txtsubtitle;
            public CardView card;

            public CircleImageView image;
            public TextView priceinfo;
        public BlogViewHolder(@NonNull View itemView) {
            super(itemView);
            txtsubtitle = itemView.findViewById(R.id.subtitle);
            txttitle = itemView.findViewById(R.id.title);
            image =itemView.findViewById(R.id.image);
            priceinfo = itemView.findViewById(R.id.priceinfo);
            card = itemView.findViewById(R.id.card);
        }
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
                if(x1>x2){
                    Intent intent = new Intent(getApplicationContext(),task_completed.class);
                    Pair[] pairs = new Pair[1];
                    pairs[0] = new Pair<View,String>(redball,"image2Transition");
                    ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(dashboard.this,pairs);
                    startActivity(intent,activityOptions.toBundle());
            }
            break;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if(backPressedtime + 2000 > System.currentTimeMillis()){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            finish();
            System.exit(0);
        }else{
            Toast.makeText(getApplicationContext(),"Press again to Exit",Toast.LENGTH_LONG).show();
        }
        backPressedtime = System.currentTimeMillis();
    }

    private void toDatabase(){
        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                // Id of the provider (ex: google.com)
                String providerId = user.getProviderId();

                // UID specific to the provider
                String uid = user.getUid();

                // Name, email address, and profile photo Url
                String name = user.getDisplayName();
                String email = user.getEmail();
                Uri photoUrl = user.getPhotoUrl();
                String photo = photoUrl.toString().trim();
        Intent intent = new Intent(getApplicationContext(), profile.class);
        intent = intent.putExtra("id", uid);
        DocumentReference documentReference = fstore.collection("users").document(uid);
        final Map<String,Object> users_info = new HashMap<>();
        users_info.put("Name",name);
        users_info.put("Email",email);

        documentReference.set(users_info);

    }
}


