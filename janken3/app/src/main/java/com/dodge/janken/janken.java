package com.dodge.janken;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class janken extends AppCompatActivity {

    // 追加
    /*int count1 = 0;
    private TextView counter_text1;
    int count2 = 0;
    private TextView counter_text2;*/
    int p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Stringデータより取得
        setTitle(R.string.nomal_mode);
        //counter_text1 = (TextView) findViewById(R.id.txt_counter1);
        //counter_text2 = (TextView) findViewById(R.id.txt_counter2);

        TextView counter_text1 = (TextView)findViewById(R.id.txt_counter1);
        TextView counter_text2 = (TextView)findViewById(R.id.txt_counter1);
        counter_text1.setTypeface(Typeface.createFromAsset(getAssets(), "DSEG7.ttf"));
        counter_text2.setTypeface(Typeface.createFromAsset(getAssets(), "DSEG7.ttf"));
    }

    //ボタンにするものはすべてonClick0を指定
    //押されたボタンのidの処理
    // 0 = グー、1 = チョキ、2 = パー
    public void onClick0(View v) {
        switch (v.getId()) {
            case R.id.imageView1:
                p = 0;
                break;

            case R.id.imageView2:
                //onClick2();
                p = 1;
                break;

            case R.id.imageView3:
                //onClick3();
                p = 2;
                break;
        }
        Random r = new Random();
        int n = r.nextInt(3);

        //グローバル（前処理共通のため）
        ImageView iv = new ImageView(this);
        iv.setAdjustViewBounds(true);

        //勝ちのときの処理
        if (p == 0 && n == 1 || p == 2 && n == 0 || p == 1 && n == 2) {
            if (p == 0 && n == 1) {
                iv.setImageResource(R.drawable.image0_1);
            } else if (p == 2 && n == 0) {
                iv.setImageResource(R.drawable.image2_0);
            } else {
                iv.setImageResource(R.drawable.image1_2);
            }
            //counter_text1.setText(String.valueOf(count1));
            //MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.kati);
            //mediaPlayer.start();
            new AlertDialog.Builder(this)
                    .setView(iv)
                    .setTitle(R.string.dialog_title1)
                    .setPositiveButton("OK", null)
                    .show();

            //負けのときの処理
        } else if (p == 0 && n == 2 || p == 2 && n == 1 || p == 1 && n == 0) {
            if (p == 0 && n == 2) {
                iv.setImageResource(R.drawable.image0_2);
            } else if (p == 2 && n == 1) {
                iv.setImageResource(R.drawable.image2_1);
            } else {
                iv.setImageResource(R.drawable.image1_0);
            }
            //counter_text2.setText(String.valueOf(count2));
            //MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.make);
            //mediaPlayer.start();
            new AlertDialog.Builder(this)
                    .setView(iv)
                    .setTitle(R.string.dialog_title2)
                    .setPositiveButton("OK", null)
                    .show();

            //あいこのときの処理
        } else {
            if (p == 0 && n == 0) {
                iv.setImageResource(R.drawable.image0_0);
            } else if (p == 1 && n == 1) {
                iv.setImageResource(R.drawable.image1_1);
            } else {
                iv.setImageResource(R.drawable.image2_2);
            }
            //MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.aiko);
            //mediaPlayer.start();
            new AlertDialog.Builder(this)
                    .setView(iv)
                    .setTitle(R.string.dialog_title3)
                    .setPositiveButton("OK", null)
                    .show();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                finish();
                overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);
        }
        return false;
    }

}

