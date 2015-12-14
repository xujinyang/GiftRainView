package com.xujinyang.giftrainview;

import android.app.Activity;
import android.os.Bundle;

import me.jamesxu.giftrainview.GiftRainView;

public class MainActivity extends Activity {
    private GiftRainView giftRainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        giftRainView = (GiftRainView) findViewById(R.id.dropview);
        giftRainView.setImages(R.mipmap.ico_money, R.mipmap.ico_gold_money);
    }

}
