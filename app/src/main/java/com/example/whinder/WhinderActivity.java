package com.example.whinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.print.PrinterId;
import android.view.View;
import android.widget.TextView;

public class WhinderActivity extends AppCompatActivity {
    private TextView textView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whinder);
        textView = findViewById(R.id.whindername);

        Bundle extras = getIntent().getExtras();
        String userName;

        if (extras != null) {
            userName = extras.getString("Whinder");
            textView.setText(userName);

        }
    }

    public void nextclicked(View view)
    {
        Intent intent = new Intent(WhinderActivity.this,ProfileActivity.class);
        startActivity(intent);
        finish();
    }
}