package com.example.tic_tac_toe_;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class WinActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView view;
    ImageView btn_again;
    ImageView btn_exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        getSupportActionBar().hide();

        view = findViewById(R.id.imageWin);
        btn_again = findViewById(R.id.imageView2);
        btn_exit = findViewById(R.id.imageView4);

        Intent intent = getIntent();
        int a = intent.getIntExtra("code", 0);

        switch (a){
            case 0: view.setImageResource(R.drawable.draw); break;
            case 1: view.setImageResource(R.drawable.gameover); break;
            case 2: view.setImageResource(R.drawable.win); break;
        }

        btn_again.setOnClickListener(this);
        btn_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageView2:
                Intent intent = new Intent(WinActivity.this, GameActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.imageView4:
                finish();
                break;
        }
    }
}