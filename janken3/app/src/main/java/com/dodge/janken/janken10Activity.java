package com.dodge.janken;

import android.content.DialogInterface;
import android.content.SharedPreferences;
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


public class janken10Activity extends AppCompatActivity {

    // 追加
    int count1 = 0;     //プレイヤーの点数カウント
    private TextView counter_text1;
    int count2 = 0;     //CPUの点数カウント
    private TextView counter_text2;
    //優勝データカウント
    int dcount = 0;
    int score = 0;
    int p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janken10);
        setTitle(R.string.ten_th_mode);
        counter_text1 = (TextView) findViewById(R.id.txt_counter1);
        counter_text2 = (TextView) findViewById(R.id.txt_counter2);

        // データの読込
        SharedPreferences preferences = getSharedPreferences("save_data1", MODE_PRIVATE);
        dcount = preferences.getInt("trophy", 0);
        score = preferences.getInt("score", 0);
        //dcount =0;

        //フォント設定
        TextView counter_text1 = (TextView)findViewById(R.id.txt_counter1);
        TextView counter_text2 = (TextView)findViewById(R.id.txt_counter2);
        counter_text1.setTypeface(Typeface.createFromAsset(getAssets(), "DSEG7.ttf"));
        counter_text2.setTypeface(Typeface.createFromAsset(getAssets(), "DSEG7.ttf"));
    }

    //ボタンにするものはすべてonClick0を指定
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

        ImageView iv = new ImageView(this);
        iv.setAdjustViewBounds(true);
        //プレイヤーが10点未満の時の処理
        if(count1 < 10) {
            //CPUが10点未満の時の処理
            if(count2 < 10) {
                //勝ちのときの処理
                if (p == 0 && n == 1 || p == 2 && n == 0 || p == 1 && n == 2) {
                    if (p == 0 && n == 1) {
                        iv.setImageResource(R.drawable.image0_1);
                    } else if (p == 2 && n == 0) {
                        iv.setImageResource(R.drawable.image2_0);
                    } else {
                        iv.setImageResource(R.drawable.image1_2);
                    }
                    //MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.kati);
                    //mediaPlayer.start();
                    count1++;
                    score += 1;
                    counter_text1.setText(String.valueOf(count1));
                    new AlertDialog.Builder(this)
                            .setView(iv)
                            .setTitle(R.string.dialog_title1)
                            .setMessage(R.string.dialog_mes1)
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
                    //MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.make);
                    //mediaPlayer.start();
                    count2++;
                    counter_text2.setText(String.valueOf(count2));
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

                //CPUが先に10点を取った時の処理
            }else{
                //MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.make10);
                //mediaPlayer.start();
                new AlertDialog.Builder(this)
                        .setTitle(R.string.result1)
                        .setMessage(R.string.result_mes1)
                        .setPositiveButton(R.string.result_button,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                }

                        )
                        .show();
            }

        }else if(count2 <= 5){
            //プレイヤーの勝ち、相手が5点以下
            dcount++;
            score += 20;
            //ImageView iv = new ImageView(this);
            iv.setImageResource(R.drawable.yusyou);
            //iv.setAdjustViewBounds(true);
            //MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.yuusyo);
            //mediaPlayer.start();
            new AlertDialog.Builder(this)
                    .setView(iv)
                    .setTitle(R.string.result2)
                    .setMessage(R.string.result_mes2)
                    .setNegativeButton(R.string.result_button,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            }

                    )
                    .show();
        }else{
            //プレイヤーの勝ち、相手が6点以上
           // MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.kati10);
            //mediaPlayer.start();
            new AlertDialog.Builder(this)
                    .setTitle(R.string.result1)
                    .setMessage(R.string.result_mes3)
                    .setPositiveButton(R.string.result_button,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            }

                    )
                    .show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // データの保存
        SharedPreferences preferences = getSharedPreferences("save_data1",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        //トロフィー獲得数
        editor.putInt("trophy",dcount);
        editor.commit();
        //スコア
        editor.putInt("score",score);
        editor.commit();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                new AlertDialog.Builder(this)
                        .setTitle(R.string.exit_mes_title)
                        .setMessage(R.string.exit_mes)
                        .setNeutralButton("YES",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which){
                                        finish();
                                        overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);
                                    }
                                })
                        .setNegativeButton("NO", null)
                        .show();

        }
        return false;
    }
}
