package com.dodge.janken;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;


public class Mainmenu extends AppCompatActivity {

    //トロフィー獲得数
    int dcount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        //縦画面のみ可能（このアクティビティのみ）
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        // データの読込
        SharedPreferences preferences = getSharedPreferences("save_data1",MODE_PRIVATE);
        dcount = preferences.getInt("trophy" , 0);


    }
    // じゃんけん
    public void onButton1( View v){
        Intent intent = new Intent(this,janken.class); // 画面指定
        startActivity(intent);
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.poch);
        mediaPlayer.start();
        overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
    }
    //じゃんけん10点マッチ
    public void onButton2( View v ){
        Intent intent = new Intent(this,janken10Activity.class); // 画面指定
        startActivity(intent);
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.poch);
        mediaPlayer.start();
        overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
    }

    //実績
    public void onButton4( View v ){
        Intent intent = new Intent(this,Performance.class); // 画面指定
        startActivity(intent);
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.poch);
        mediaPlayer.start();
        overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
    }

    //アップデート
    public void onButton5( View v){
        Intent intent = new Intent(this,AboutActivity.class); // 画面指定
        startActivity(intent);                          //  画面を開く
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.poch);
        mediaPlayer.start();
        overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
    }
}
