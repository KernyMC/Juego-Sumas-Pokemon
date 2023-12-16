package com.example.pryjuegosumas;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import java.util.Random;

public class RandomMovementAnimation extends TranslateAnimation {
    private static final int DURATION = 1000;

    public RandomMovementAnimation(View view) {
        super(0, randomCoordinate(view.getWidth() - view.getMeasuredWidth()), 0, randomCoordinate(view.getHeight() - view.getMeasuredHeight()));
        setDuration(DURATION);
        setRepeatCount(INFINITE);
        setRepeatMode(Animation.REVERSE);
        setFillAfter(true);
    }

    private static float randomCoordinate(int max) {
        Random random = new Random();
        return random.nextFloat() * max;
    }
}