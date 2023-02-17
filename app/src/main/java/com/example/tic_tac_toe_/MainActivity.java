package com.example.tic_tac_toe_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView btnPlay;
    ImageView btnRules;
    ImageView btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        getSupportActionBar().hide();
        btnPlay = findViewById(R.id.imageBtnPlay);
        btnRules = findViewById(R.id.imageBtnRules);
        btnExit = findViewById(R.id.imageBtnExit);

        btnPlay.setOnClickListener(this);
        btnRules.setOnClickListener(this);
        btnExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageBtnPlay:
                Intent game = new Intent(MainActivity.this, GameActivity.class);
                startActivity(game);
                finish();
                break;
            case R.id.imageBtnRules:
                Intent rules = new Intent(MainActivity.this, ActivityRules.class);
                startActivity(rules);
                finish();
                break;
            case R.id.imageBtnExit:
                finish();
                break;
        }
    }
}