package com.example.tic_tac_toe_;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView btn_your_turn;
    ImageView btn_exit;
    ImageView btn_again;
    ImageView btn_b00;
    ImageView btn_b01;
    ImageView btn_b02;
    ImageView btn_b10;
    ImageView btn_b11;
    ImageView btn_b12;
    ImageView btn_b20;
    ImageView btn_b21;
    ImageView btn_b22;

    int t[][] = new int[3][3];

    int turnes = 0;

    ImageView btn[][];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        getSupportActionBar().hide();

        btn_your_turn = findViewById(R.id.imageYourTurn);
        btn_exit = findViewById(R.id.imageBlackExit);
        btn_again = findViewById(R.id.imageView3);

        btn_b00 = findViewById(R.id.n00);
        btn_b01 = findViewById(R.id.n01);
        btn_b02 = findViewById(R.id.n02);
        btn_b10 = findViewById(R.id.n10);
        btn_b11 = findViewById(R.id.n11);
        btn_b12 = findViewById(R.id.n12);
        btn_b20 = findViewById(R.id.n20);
        btn_b21 = findViewById(R.id.n21);
        btn_b22 = findViewById(R.id.n22);

        btn_exit.setOnClickListener(this);
        btn_again.setOnClickListener(this);

        btn_b00.setOnClickListener(this);
        btn_b01.setOnClickListener(this);
        btn_b02.setOnClickListener(this);
        btn_b10.setOnClickListener(this);
        btn_b11.setOnClickListener(this);
        btn_b12.setOnClickListener(this);
        btn_b20.setOnClickListener(this);
        btn_b21.setOnClickListener(this);
        btn_b22.setOnClickListener(this);


        btn = new ImageView[][]{
                {btn_b00, btn_b01, btn_b02},
                {btn_b10, btn_b11, btn_b12},
                {btn_b20, btn_b21, btn_b22}
        };

        startGame();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageBlackExit:
                Animation animation= AnimationUtils.loadAnimation(GameActivity.this,R.anim.mixed_anim);
                btn_exit.startAnimation(animation);
                finish();
                break;
            case R.id.imageView3:
                Animation animation2=AnimationUtils.loadAnimation(GameActivity.this,R.anim.mixed_anim);
                btn_again.startAnimation(animation2);
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.n00: checkMove(0,0); break;
            case R.id.n01: checkMove(0,1); break;
            case R.id.n02: checkMove(0,2); break;
            case R.id.n10: checkMove(1,0); break;
            case R.id.n11: checkMove(1,1); break;
            case R.id.n12: checkMove(1,2); break;
            case R.id.n20: checkMove(2,0); break;
            case R.id.n21: checkMove(2,1); break;
            case R.id.n22: checkMove(2,2); break;
        }
    }

    private void startGame(){
        clearArr();
        showArr();
        turnes = 0;
    }

    private void showArr() {
        for(int i = 0;i<3;i++)
            for(int j = 0;j<3;j++)
                if     (t[i][j] == 0) btn[i][j].setImageResource(R.drawable.btn0);
                else if(t[i][j] == 1) btn[i][j].setImageResource(R.drawable.btn1);
                else                  btn[i][j].setImageResource(R.drawable.btn2);
    }

    private void clearArr() {
        for(int i = 0;i<3;i++)
            for(int j = 0;j<3;j++) t[i][j] = 0;
    }

    private void checkMove(int row, int col){
        if(t[row][col]!=0) return;

        t[row][col] = 2;
        showArr();
        animationMove(row, col);
        if (checkWin(2)){       // выйграл человек
            showWin(2);
            return;
        }
        turnes++;
        if (turnes == 9){         // ничья
            showWin(0);
            return;
        }
        turnAI();
        showArr();
        turnes++;
        if (checkWin(1)){       // выйграл ии
            showWin(1);
            return;
        }

    }

    private void turnAI() {
        Random random = new Random();
        while (true){
            int id = random.nextInt(9);
            int r = id / 3;
            int c = id % 3;
            if(t[r][c] == 0){
                t[r][c] = 1;
                animationMove(r,c);
                return;
            }
        }
    }

    private void showWin(int i) {
        Intent intent = new Intent(GameActivity.this, WinActivity.class);
        intent.putExtra("code", i);
        startActivity(intent);
        finish();
        startGame();

    }

    private boolean checkWin(int x) {
        if(t[0][0] == x && t[0][1] == x && t[0][2] == x) return true;
        if(t[1][0] == x && t[1][1] == x && t[1][2] == x) return true;
        if(t[2][0] == x && t[2][1] == x && t[2][2] == x) return true;

        if(t[0][0] == x && t[1][0] == x && t[2][0] == x) return true;
        if(t[0][1] == x && t[1][1] == x && t[2][1] == x) return true;
        if(t[0][2] == x && t[1][2] == x && t[2][2] == x) return true;

        if(t[0][0] == x && t[1][1] == x && t[2][2] == x) return true;
        if(t[0][2] == x && t[1][1] == x && t[2][0] == x) return true;

        return false;
    }

    private void animationMove(int row, int col){
        Animation animation=AnimationUtils.loadAnimation(GameActivity.this,R.anim.bounce);
        btn[row][col].startAnimation(animation);
    }

}