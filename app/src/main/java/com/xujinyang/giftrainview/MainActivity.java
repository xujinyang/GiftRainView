package com.xujinyang.giftrainview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import me.jamesxu.giftrainview.GiftRainView;

public class MainActivity extends Activity {
    private GiftRainView giftRainView;
    private boolean isStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        giftRainView = (GiftRainView) findViewById(R.id.dropview);
        giftRainView.setImages(R.mipmap.ico_money, R.mipmap.ico_gold_money);
        startRain();

        giftRainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStart) {
                    stopRain();
                } else {
                    startRain();
                }
            }
        });
    }

    private void startRain() {
        giftRainView.startRain();
        isStart = true;
    }

    private void stopRain() {
        giftRainView.stopRainDely();
        isStart = false;
    }

}
