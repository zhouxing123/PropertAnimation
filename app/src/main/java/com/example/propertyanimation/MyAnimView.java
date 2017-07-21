package com.example.propertyanimation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;

public class MyAnimView extends View {

    public static final float RADIUS = 60f;//圆的半径
    private final Paint mPaint;
    private Point currentPoint;

    public MyAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        Log.d("TAG", "onMeasure");
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }
//
//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        Log.d("TAG", "onLayout");
//        super.onLayout(changed, left, top, right, bottom);
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (currentPoint == null) {
            currentPoint = new Point(RADIUS, RADIUS);
            drawCircle(canvas);
            startAnimation();
        } else {
            drawCircle(canvas);
        }
    }

    private void drawCircle(Canvas canvas) {
        float x = currentPoint.getX();
        float y = currentPoint.getY();
        canvas.drawCircle(x, y, RADIUS, mPaint);
    }

    private void startAnimation() {
//        Point startPoint = new Point(RADIUS, RADIUS);
//        Point endPoint = new Point(getWidth() - RADIUS, getHeight() - RADIUS);
        Point startPoint = new Point(getWidth()/2, 0);
        Point endPoint = new Point(getWidth()/2, getHeight()-RADIUS);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        //系统自带调制器
        anim.setInterpolator(new BounceInterpolator());
        //自定义速度调制器
        //anim.setInterpolator(new DecelerateAccelerateInterpolator());
        anim.setDuration(5000);
        anim.start();
    }
}
