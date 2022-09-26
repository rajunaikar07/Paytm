package com.example.paytm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ToselfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toself);
        findViewById(R.id.backarrowid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ToselfActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void help(View view) {
        Toast.makeText(this, "help or support", Toast.LENGTH_SHORT).show();
    }

    public void setting(View view) {
        Toast.makeText(this, "go to settings ", Toast.LENGTH_SHORT).show();
    }

    public void addbank(View view) {
        Toast.makeText(ToselfActivity.this,"Add New Account",Toast.LENGTH_SHORT).show();
    }
}