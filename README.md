
# GiftRainView
还记得有一招从天而降的功夫？GiftRainView，一个用来下金币，下红包的View

# 直接看效果

![](http://7o4zmy.com1.z0.glb.clouddn.com/2015-12-14%2022_21_24.gif)

# Usage
### Step 1
##### Install with gradle
        dependencies {
            compile 'com.xujinyang.GiftRainView:library:1.3.0'
        }
### Step 2

      <me.jamesxu.giftrainview.GiftRainView
        android:id="@+id/gift_rain_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:gift_background="@android:color/white"
        app:gift_count="15"
        app:gift_max_size="0.8"
        app:gift_min_size="0.5"
        app:gift_speed="300" />
      
####attrs:

| 参数 | 类型 |含义|
|--------|--------|--------|
|gift_background|color|背景颜色|
|gift_count|integer|屏幕内物体数量|
|gift_speed|integer|物体移动最小速度|
|gift_max_size|float|最大的物体比例|
|gift_min_size|float|最小的物体比例|

### Step 3
```

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
}

```
##### 	如果想要动画停止有俩个方式 

1. 等界面上所有的物体掉落完了之后，停止。 
		giftRainView.stopRainDely();
2. 立马停止动画，清空界面
		giftRainView.stiopRainNow();


License
--------
GiftRainView is opensource, contribution and feedback are welcomed

[Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

    Copyright 2015 Supercharge

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 
## About me
[CSDN](http://blog.csdn.net/mobilexu)

[weibo](http://weibo.com/3654795601/profile?topnav=1&wvr=6)

