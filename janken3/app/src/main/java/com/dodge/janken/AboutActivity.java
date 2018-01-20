package com.dodge.janken;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        //縦画面のみ可能（このアクティビティのみ）
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setTitle(R.string.update);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }
    // アップデート
    public void onButton1( View v){
        String url = "";
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        //ツールバーの色を変更
        builder.setToolbarColor(Color.YELLOW);

        //色・タイトル指定はここより上
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));
    }

    //ダウングレード
    public void onButton3(View v){
        new AlertDialog.Builder(this)
                //モーダル
                .setCancelable(false)
                .setTitle("確認")
                .setMessage("AppDownLoaderをご利用の端末にインストールされていますか？")
                .setPositiveButton("はい",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // ボタンをクリックしたときの動作
                                new AlertDialog.Builder(AboutActivity.this)
                                        .setCancelable(false)
                                        .setTitle("アンインストール")
                                        .setMessage("ご利用中のバージョンをアンインストールします。アンインストールすると、実績情報は削除されます。" +
                                                "アンインストール後にAppDownLoaderから旧バージョンをダウンロードしてください。")
                                        .setPositiveButton("OK",
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(Intent.ACTION_DELETE);
                                                        intent.setData(Uri.parse("package:com.dodge.janken"));
                                                        startActivity(intent);
                                                    }
                                                }
                                        )
                                        .setNegativeButton("キャンセル",null)
                                        .show();
                            }
                        })
                .setNegativeButton("いいえ",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                new AlertDialog.Builder(AboutActivity.this)
                                        .setCancelable(false)
                                        .setTitle("確認")
                                        .setMessage("ご利用中のバージョンをアンインストールする前に、AppDownLoaderを端末にインストールしてください。")
                                        .setPositiveButton("OK",
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        String url = "https://1drv.ms/f/s!Avdi-ls1GYkwrQmk4FV4aX8IbHzO";
                                                        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                                                        //ツールバーの色を変更
                                                        builder.setToolbarColor(Color.BLUE);

                                                        //色・タイトル指定はここより上
                                                        CustomTabsIntent customTabsIntent = builder.build();
                                                        customTabsIntent.launchUrl(AboutActivity.this, Uri.parse(url));
                                                    }
                                                }
                                        )
                                        .setNegativeButton("キャンセル",null)
                                        .show();
                            }
                        }
                )
                .show();
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
