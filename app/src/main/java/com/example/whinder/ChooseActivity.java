package com.example.whinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ChooseActivity extends AppCompatActivity {

    private LinearLayout linearLayoutprev , linearLayoutrather ;
    private TextView prevusername , ratherusername , ratherusername1;
    private FirebaseUser firebaseUser ;
    private DatabaseReference databaseReference ;
    private String userinterest ;
    private int i = 0;
    private String usersex ;
    private int randomNum ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);



        linearLayoutprev = findViewById(R.id.prevuserlay);
        linearLayoutrather = findViewById(R.id.ratheruserlay);

        ratherusername = findViewById(R.id.ratheruser2);
        prevusername = findViewById(R.id.prevuser);
        ratherusername1 = findViewById(R.id.ratheruser);

        FirebaseUser fireUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference dref = FirebaseDatabase.getInstance().getReference("UsersMain").child(fireUser.getUid());
        dref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    UserMain user = dataSnapshot.getValue(UserMain.class);
                    assert user != null;

                    usersex = user.getSex();
                    RandomUserPrev(usersex);
                    RandomUserRat(usersex);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        randomNum = ThreadLocalRandom.current().nextInt(6, 12 + 1);

    }

    private void RandomUserRat(String sx)
    {
        DatabaseReference dref = FirebaseDatabase.getInstance().getReference("Users").child(sx);

        dref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int userCount = (int) dataSnapshot.getChildrenCount();
                Random random = new Random();
                int rand = random.nextInt(userCount);
                Iterator itr = dataSnapshot.getChildren().iterator();


                for(int j = 0; j < rand; j++) {
                    itr.next();
                }
                DataSnapshot childSnapshot = (DataSnapshot) itr.next();
                User user = childSnapshot.getValue(User.class);
                ratherusername.setText(user.getUsername());
                ratherusername1.setText(user.getUsername());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void RandomUserPrev(String sx)
    {
        DatabaseReference dref = FirebaseDatabase.getInstance().getReference("Users").child(sx);

        dref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int userCount = (int) dataSnapshot.getChildrenCount();
                Random random = new Random();
                int rand = random.nextInt(userCount);
                Iterator itr = dataSnapshot.getChildren().iterator();


                for(int j = 0; j < rand; j++) {
                    itr.next();
                }
                DataSnapshot childSnapshot = (DataSnapshot) itr.next();
                User user = childSnapshot.getValue(User.class);
                prevusername.setText(user.getUsername());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void Counter1(View view)
    {

        if (i==randomNum)
        {
            i=0;
            Intent intent = new Intent(ChooseActivity.this,WhinderActivity.class);
            intent.putExtra("Whinder",ratherusername.getText().toString());
            startActivity(intent);
            finish();
        }



        try {
            DatabaseReference dref = FirebaseDatabase.getInstance().getReference("Users").child(UserSex());

            dref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int userCount = (int) dataSnapshot.getChildrenCount();
                    Random random = new Random();
                    int rand = random.nextInt(userCount);
                    Iterator itr = dataSnapshot.getChildren().iterator();


                    for(int j = 0; j < rand; j++) {
                        itr.next();
                    }
                    DataSnapshot childSnapshot = (DataSnapshot) itr.next();
                    User user = childSnapshot.getValue(User.class);
                    ratherusername.setText(user.getUsername());
                    ratherusername1.setText(user.getUsername());
                    i++;
//                ratherusername1.setText(Integer.toString(i));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }catch (Exception e)
        {

        }
    }

    public void Counter2(View view)
    {

        if (i==randomNum)
        {
            i=0;
            Intent intent = new Intent(ChooseActivity.this,WhinderActivity.class);
            intent.putExtra("Whinder",prevusername.getText().toString());
            startActivity(intent);
            finish();
        }



        try {
            DatabaseReference dref = FirebaseDatabase.getInstance().getReference("Users").child(UserSex());

            dref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int userCount = (int) dataSnapshot.getChildrenCount();
                    Random random = new Random();
                    int rand = random.nextInt(userCount);
                    Iterator itr = dataSnapshot.getChildren().iterator();


                    for(int j = 0; j < rand; j++) {
                        itr.next();
                    }
                    DataSnapshot childSnapshot = (DataSnapshot) itr.next();
                    User user = childSnapshot.getValue(User.class);
                    prevusername.setText(user.getUsername());
                    ratherusername1.setText(user.getUsername());
                    i++;
//                ratherusername1.setText(Integer.toString(i));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }catch (Exception e){

        }
    }


    private String sex;
    private String UserSex()
    {
        final FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference dbmale = FirebaseDatabase.getInstance().getReference("Users").child("Male");


        dbmale.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    User user2 = dataSnapshot.getValue(User.class);
                    if (user2.getId().equals(fuser.getUid()))
                        sex = "Female";
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference dbfemale = FirebaseDatabase.getInstance().getReference("Users").child("Female");
        dbfemale.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    User user2 = dataSnapshot.getValue(User.class);
                    if (user2.getId().equals(fuser.getUid()))
                        sex = "Male";
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return sex;
    }


}