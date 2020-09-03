package com.example.whinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActvity extends AppCompatActivity {

    private EditText user_nameedittext, emailedittext, passwordedittext;
    private Button loginButton;
    private FirebaseAuth mAuth;
    private TextView textView;
    private DatabaseReference databaseReference ;
    private RadioGroup radioGroup ;
    private RadioButton radioButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_actvity);

        mAuth = FirebaseAuth.getInstance();

        user_nameedittext = findViewById(R.id.editTextTextPersonName);
        emailedittext = findViewById(R.id.editTextTextEmailAddress);
        passwordedittext = findViewById(R.id.editTextTextPassword);



        loginButton = findViewById(R.id.regbutton);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup = findViewById(R.id.interestradio);
                int gen = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(gen);

                String user_name = user_nameedittext.getText().toString();
                String email = emailedittext.getText().toString().trim();
                String interest = radioButton.getText().toString();
                String password = passwordedittext.getText().toString();

                if (user_name.isEmpty())
                {
                    user_nameedittext.setError("Enter a username");
                    user_nameedittext.requestFocus();
                    return;
                }

                if (email.isEmpty())
                {
                    emailedittext.setError("Enter an email address");
                    emailedittext.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    emailedittext.setError("Enter a valid email address");
                    emailedittext.requestFocus();
                    return;
                }


                if (password.isEmpty())
                {
                    passwordedittext.setError("Enter password");
                    passwordedittext.requestFocus();
                    return;
                }

                if (password.length()<6)
                {
                    passwordedittext.setError("Minimum length is 6 digits");
                    passwordedittext.requestFocus();
                    return;
                }



                register(user_name,email,interest,password);
            }
        });


    }
    public void register (final String user_name , final String email , final String interest, String password)
    {
        final ProgressDialog progressDialog = new ProgressDialog(RegisterActvity.this);
        progressDialog.setMessage("Registering");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    assert firebaseUser != null;
                    String userid = firebaseUser.getUid();


                    HashMap<String,Object> hashMap = new HashMap<>();
                    hashMap.put("id",userid);
                    hashMap.put("username",user_name);
                    hashMap.put("imageurl","dafault");
                    hashMap.put("email",email);
                    hashMap.put("interest",interest);

                    HashMap<String,Object> hashMap1 = new HashMap<>();
                    if (interest.equals("Female"))
                        hashMap1.put("sex","Male");
                    if (interest.equals("Male"))
                        hashMap1.put("sex","Female");


                    DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("UsersMain").child(userid);
                    databaseReference1.setValue(hashMap1);

                    databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(interest).child(userid);
                    databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful())
                            {
                                Intent intent = new Intent(RegisterActvity.this,ProfileActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                            else
                                Toast.makeText(getApplicationContext(),"You can't register with this email and password",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}