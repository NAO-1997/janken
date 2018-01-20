package com.dodge.janken;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import static com.dodge.janken.R.id.counter_text1;
import static com.dodge.janken.R.id.counter_text2;
import static com.dodge.janken.R.id.text;

public class Performance extends AppCompatActivity {

    int dcount = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.data);
        setContentView(R.layout.activity_performance);

        // データの読込
        SharedPreferences preferences = getSharedPreferences("save_data1",MODE_PRIVATE);
        dcount = preferences.getInt("trophy" , 0);
        ((TextView)findViewById(counter_text1)).setText("" + dcount);
        score = preferences.getInt("score" , 0);
        ((TextView)findViewById(counter_text2)).setText("" + score);

        //フォント設定
        TextView count_text1 = (TextView)findViewById(R.id.counter_text1);
        TextView count_text2 = (TextView)findViewById(counter_text2);
        count_text1.setTypeface(Typeface.createFromAsset(getAssets(), "DSEG7.ttf"));
        count_text2.setTypeface(Typeface.createFromAsset(getAssets(), "DSEG7.ttf"));
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
