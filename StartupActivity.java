package com.example.pranaykohad.qrattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class StartupActivity extends AppCompatActivity {

    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        rl = (RelativeLayout)findViewById(R.id.rl1);


        rl.setOnClickListener(
                new RelativeLayout.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(StartupActivity.this,MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
        );
    }
}
