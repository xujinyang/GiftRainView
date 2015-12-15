package me.jamesxu.giftrainview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by james on 14/12/15.
 */
public class GiftRainView extends View {

    private int duration;

    private int[] imgs;
    private int count;
    private int background;
    private int speed;

    private ArrayList<Gift> giftList;
    private Matrix m = new Matrix();
    private ValueAnimator animator;
    private long startTime, prevTime;
    public static HashMap<Integer, Bitmap> bitmapMap = new HashMap<Integer, Bitmap>();

    public GiftRainView(Context context) {
        super(context, null);
        init();
    }

    public GiftRainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (isInEditMode())
            return;

        if (null == attrs) {
            throw new IllegalArgumentException("Attributes should be provided to this view,");
        }
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DropDownStyle);
        count = typedArray.getInt(R.styleable.DropDownStyle_gift_count, 20);
        speed = typedArray.getInt(R.styleable.DropDownStyle_gift_speed, 100);
        background = typedArray.getInt(R.styleable.DropDownStyle_gift_background, android.R.color.white);
        typedArray.recycle();
        init();
    }


    private void setAnimator() {
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                long nowTime = System.currentTimeMillis();
                float secs = (float) (nowTime - prevTime) / 1000f;
                prevTime = nowTime;
                for (int i = 0; i < giftList.size(); ++i) {
                    Gift gift = giftList.get(i);
                    gift.y += (gift.speed * secs);
                    if (gift.y > getHeight()) {
                        gift.y = 0 - gift.height;
                    }
                    gift.rotation = gift.rotation
                            + (gift.rotationSpeed * secs);
                }
                invalidate();
            }
        });
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(duration);
    }

    private void init() {
        giftList = new ArrayList<Gift>();
        animator = ValueAnimator.ofFloat(0, 1);
        setLayerType(View.LAYER_TYPE_NONE, null);
        setBackgroundColor(background);

        setAnimator();
    }

    public void setGiftCount(int quantity) {
        if (imgs == null || imgs.length == 0)
            return;
        for (int i = 0; i < quantity; ++i) {
            Bitmap originalBitmap = BitmapFactory
                    .decodeResource(getResources(), imgs[i % imgs.length]);
            Gift gift = new Gift(getWidth(), originalBitmap, speed);
            gift.bitmap = bitmapMap.get(gift.width);
            if (gift.bitmap == null) {
                gift.bitmap = Bitmap.createScaledBitmap(originalBitmap,
                        (int) gift.width, (int) gift.height, true);
                bitmapMap.put(gift.width, gift.bitmap);
            }
            giftList.add(gift);
        }
    }

    public void setImages(int... images) {
        imgs = images;
        setGiftCount(count);
    }

    public void cutGiftCount(int quantity) {
        if (quantity > giftList.size())
            return;
        for (int i = 0; i < quantity; ++i) {
            giftList.remove(i);
        }
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        giftList.clear();
        setGiftCount(count);
        animator.cancel();
        startTime = System.currentTimeMillis();
        prevTime = startTime;
        animator.start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < giftList.size(); ++i) {
            Gift gift = giftList.get(i);
            m.setTranslate(-gift.width / 2, -gift.height / 2);
            m.postRotate(gift.rotation);
            m.postTranslate(gift.width / 2 + gift.x, gift.height / 2
                    + gift.y);
            canvas.drawBitmap(gift.bitmap, m, null);
        }
    }

    public class Gift {
        private float x, y;
        private float rotation;
        private float speed;
        private float rotationSpeed;
        private int width, height;
        private Bitmap bitmap;

        public Gift(float xRange, Bitmap originalBitmap, int speed) {
            width = (int) (originalBitmap.getWidth() * (Math.random() + 0.3));
            float hwRatio = originalBitmap.getHeight() * 1.0f / originalBitmap.getWidth();
            height = (int) (width * hwRatio);
            x = (float) Math.random() * (xRange - width);
            y = 0 - (height + (float) Math.random() * height);
            this.speed = speed + (float) Math.random() * 550;
            rotation = (float) Math.random() * 180 - 90;
            rotationSpeed = (float) Math.random() * 90 - 45;
        }
    }
}
