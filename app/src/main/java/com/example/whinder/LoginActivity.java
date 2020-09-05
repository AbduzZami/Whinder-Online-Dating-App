package com.example.whinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailedittext;
    private EditText passwordedittext;
    private Button button;
    private FirebaseUser firebaseUser ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();
        emailedittext = findViewById(R.id.editTextTextEmailAddress);
        passwordedittext = findViewById(R.id.editTextTextPassword);
        button = findViewById(R.id.loginbutton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailedittext.getText().toString().trim();
                String password = passwordedittext.getText().toString();

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

                //progressBar.setVisibility(View.VISIBLE);
                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Logging in");
                progressDialog.show();

                mAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //progressBar.setVisibility(View.GONE);
                                progressDialog.dismiss();
                                if (task.isSuccessful())
                                {
                                    Intent intent = new Intent(LoginActivity.this,ProfileActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();
                                }
                                else
                                    Toast.makeText(LoginActivity.this,"Authentication failed",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser!=null)
        {
            Intent intent = new Intent(LoginActivity.this,ChooseActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void GoToReg(View view)
    {
        Intent intent = new Intent(LoginActivity.this,RegisterActvity.class);
        startActivity(intent);
        finish();
    }
}