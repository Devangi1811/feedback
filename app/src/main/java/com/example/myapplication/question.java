package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hsalf.smilerating.SmileRating;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class question extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference db,db1,db2;
    private Button btn;
    SmileRating smileRating;
    SmileRating smileRating1;
    SmileRating smileRating2;
    SmileRating smileRating3;
    SmileRating smileRating4;
    SmileRating smileRating5;
    SmileRating smileRating6;
    SmileRating smileRating7;
    SmileRating smileRating8;
    SmileRating smileRating9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Intent i=getIntent();
        String sun=i.getStringExtra("subjectname");
        final String rate=i.getStringExtra("averagerating");
        mAuth=FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance().getReference().child("subjectname").child(mAuth.getCurrentUser().getUid()).child("ai");
        db1=FirebaseDatabase.getInstance().getReference().child("subjectname").child(mAuth.getCurrentUser().getUid()).child(sun);
        db2 =FirebaseDatabase.getInstance().getReference().child("averagerating").child(rate);
        btn=findViewById(R.id.abhi);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
        smileRating=(findViewById(R.id.smile_rating));
        smileRating1=(findViewById(R.id.smile_rating1));
        smileRating2=(findViewById(R.id.smile_rating2));
        smileRating3=(findViewById(R.id.smile_rating3));
        smileRating4=(findViewById(R.id.smile_rating4));
        smileRating5=(findViewById(R.id.smile_rating5));
        smileRating6=(findViewById(R.id.smile_rating6));
        smileRating7=(findViewById(R.id.smile_rating7));
        smileRating8=(findViewById(R.id.smile_rating8));
        smileRating9=(findViewById(R.id.smile_rating9));
        smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                switch (smiley) {
                    case SmileRating.BAD:
                        Toast.makeText(question.this,"BAD",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GOOD:
                        Toast.makeText(question.this, "Good",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GREAT:
                        Toast.makeText(question.this, "Great",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.OKAY:
                        Toast.makeText(question.this, "Okay",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.TERRIBLE:
                        Toast.makeText(question.this, "Terrible",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        smileRating1.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                switch (smiley) {
                    case SmileRating.BAD:
                        Toast.makeText(question.this,"BAD",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GOOD:
                        Toast.makeText(question.this, "Good",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GREAT:
                        Toast.makeText(question.this, "Great",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.OKAY:
                        Toast.makeText(question.this, "Okay",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.TERRIBLE:
                        Toast.makeText(question.this, "Terrible",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        smileRating2.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                switch (smiley) {
                    case SmileRating.BAD:
                        Toast.makeText(question.this,"BAD",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GOOD:
                        Toast.makeText(question.this, "Good",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GREAT:
                        Toast.makeText(question.this, "Great",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.OKAY:
                        Toast.makeText(question.this, "Okay",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.TERRIBLE:
                        Toast.makeText(question.this, "Terrible",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        smileRating3.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                switch (smiley) {
                    case SmileRating.BAD:
                        Toast.makeText(question.this,"BAD",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GOOD:
                        Toast.makeText(question.this, "Good",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GREAT:
                        Toast.makeText(question.this, "Great",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.OKAY:
                        Toast.makeText(question.this, "Okay",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.TERRIBLE:
                        Toast.makeText(question.this, "Terrible",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        smileRating4.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                switch (smiley) {
                    case SmileRating.BAD:
                        Toast.makeText(question.this,"BAD",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GOOD:
                        Toast.makeText(question.this, "Good",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GREAT:
                        Toast.makeText(question.this, "Great",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.OKAY:
                        Toast.makeText(question.this, "Okay",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.TERRIBLE:
                        Toast.makeText(question.this, "Terrible",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        smileRating5.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                switch (smiley) {
                    case SmileRating.BAD:
                        Toast.makeText(question.this,"BAD",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GOOD:
                        Toast.makeText(question.this, "Good",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GREAT:
                        Toast.makeText(question.this, "Great",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.OKAY:
                        Toast.makeText(question.this, "Okay",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.TERRIBLE:
                        Toast.makeText(question.this, "Terrible",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        smileRating6.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                switch (smiley) {
                    case SmileRating.BAD:
                        Toast.makeText(question.this,"BAD",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GOOD:
                        Toast.makeText(question.this, "Good",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GREAT:
                        Toast.makeText(question.this, "Great",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.OKAY:
                        Toast.makeText(question.this, "Okay",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.TERRIBLE:
                        Toast.makeText(question.this, "Terrible",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        smileRating7.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                switch (smiley) {
                    case SmileRating.BAD:
                        Toast.makeText(question.this,"BAD",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GOOD:
                        Toast.makeText(question.this, "Good",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GREAT:
                        Toast.makeText(question.this, "Great",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.OKAY:
                        Toast.makeText(question.this, "Okay",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.TERRIBLE:
                        Toast.makeText(question.this, "Terrible",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        smileRating8.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                switch (smiley) {
                    case SmileRating.BAD:
                        Toast.makeText(question.this,"BAD",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GOOD:
                        Toast.makeText(question.this, "Good",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GREAT:
                        Toast.makeText(question.this, "Great",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.OKAY:
                        Toast.makeText(question.this, "Okay",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.TERRIBLE:
                        Toast.makeText(question.this, "Terrible",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        smileRating9.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                switch (smiley) {
                    case SmileRating.BAD:
                        Toast.makeText(question.this,"BAD",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GOOD:
                        Toast.makeText(question.this, "Good",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GREAT:
                        Toast.makeText(question.this, "Great",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.OKAY:
                        Toast.makeText(question.this, "Okay",Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.TERRIBLE:
                        Toast.makeText(question.this, "Terrible",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        smileRating.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
            @Override
            public void onRatingSelected(int level, boolean reselected) {
                Toast.makeText(question.this,"Selected rating"+level,Toast.LENGTH_SHORT).show();


            }
        });
        smileRating1.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
            @Override
            public void onRatingSelected(int level, boolean reselected) {
                Toast.makeText(question.this,"Selected rating"+level,Toast.LENGTH_SHORT).show();


            }
        });
        smileRating2.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
            @Override
            public void onRatingSelected(int level, boolean reselected) {
                Toast.makeText(question.this,"Selected rating"+level,Toast.LENGTH_SHORT).show();


            }
        });
        smileRating3.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
            @Override
            public void onRatingSelected(int level, boolean reselected) {
                Toast.makeText(question.this,"Selected rating"+level,Toast.LENGTH_SHORT).show();


            }
        });
        smileRating4.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
            @Override
            public void onRatingSelected(int level, boolean reselected) {
                Toast.makeText(question.this,"Selected rating"+level,Toast.LENGTH_SHORT).show();


            }
        });
        smileRating5.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
            @Override
            public void onRatingSelected(int level, boolean reselected) {
                Toast.makeText(question.this,"Selected rating"+level,Toast.LENGTH_SHORT).show();


            }
        });
        smileRating6.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
            @Override
            public void onRatingSelected(int level, boolean reselected) {
                Toast.makeText(question.this,"Selected rating"+level,Toast.LENGTH_SHORT).show();


            }
        });
        smileRating7.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
            @Override
            public void onRatingSelected(int level, boolean reselected) {
                Toast.makeText(question.this,"Selected rating"+level,Toast.LENGTH_SHORT).show();


            }
        });
        smileRating8.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
            @Override
            public void onRatingSelected(int level, boolean reselected) {
                Toast.makeText(question.this,"Selected rating"+level,Toast.LENGTH_SHORT).show();


            }
        });
        smileRating9.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
            @Override
            public void onRatingSelected(int level, boolean reselected) {
                Toast.makeText(question.this,"Selected rating"+level,Toast.LENGTH_SHORT).show();


            }
        });
    }
    public void validate(){
        int level = smileRating.getRating();
        if(level == 0){
            return;
        }
        int level1 = smileRating1.getRating();
        if(level1 == 0){
            return;
        }
        int level2 = smileRating2.getRating();
        if(level2 == 0){
            return;
        }
        int level3 = smileRating3.getRating();
        if(level3 == 0){
            return;
        }
        int level4 = smileRating4.getRating();
        if(level4 == 0){
            return;
        }
        int level5 = smileRating5.getRating();
        if(level4 == 0){
            return;
        }
        int level6 = smileRating6.getRating();
        if(level4 == 0){
            return;
        }
        int level7 = smileRating7.getRating();
        if(level4 == 0){
            return;
        }
        int level8 = smileRating8.getRating();
        if(level4 == 0){
            return;
        }
        int level9 = smileRating9.getRating();
        if(level4 == 0){
            return;
        }
        Map<String,Integer> sub=new HashMap<>();
        sub.put("1",level);
        sub.put("2",level1);
        sub.put("3",level2);
        sub.put("4",level3);
        sub.put("5",level4);
        sub.put("6",level5);
        sub.put("7",level6);
        sub.put("8",level7);
        sub.put("9",level8);
        sub.put("10",level9);
        db.setValue(sub);
        rating();

    }
    public void rating(){
        db2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                double total = 0.0;
                double count = 0.0;
                double average = 0.0;
                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    double rating = Double.parseDouble(ds.child("averagerating").getValue().toString());
                    total = total + rating;
                    count = count + 1;
                    average = total / count;
                }
                final DatabaseReference newRef = db.child("averageRating");
                newRef.child("current").setValue(average);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();

            }
        });
    }

    public void subject2(View view) {
        startActivity(new Intent(getApplicationContext(),subject2.class));
    }
}
