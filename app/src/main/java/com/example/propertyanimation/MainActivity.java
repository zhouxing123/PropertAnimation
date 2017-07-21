package com.example.propertyanimation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private Button bt,bt1,bt2;
    private MyAnimView my;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        bt = (Button) findViewById(R.id.button);
        bt1 = (Button) findViewById(R.id.button1);
        bt2 = (Button) findViewById(R.id.button2);
        initClick();

    }

    private void initClick() {
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                doObjectAnimator();
//                doValueAnimator();
                //组合动画
//                doAnimatorSet();
                //ViewPropertyAnimator的用法
                doPropertyAnimator();
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"我在这",Toast.LENGTH_SHORT);
                Log.d("TAG", "我在这");
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doHighAnimator();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ObjectAnimatorActivity.class));
            }
        });
    }

    /**
     * 3.1加入的类 对属性动画的简化
     *  默认隐式启动
     */
    private void doPropertyAnimator() {
        tv.animate().alpha(0.5f).translationX(200f).setDuration(1000).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                tv.animate().translationY(200f);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void doHighAnimator() {
        startActivity(new Intent(this,ValueAnimatorActivity.class));
    }

    private void doObjectAnimator() {
//        (1) translationX 和 translationY：这两个属性控制着 View 的屏幕位置坐标变化量，以 layout 容器的左上角为坐标原点;
//        (2) rotation、rotationX 和 rotationY：这三个属性控制着 2D 旋转角度（rotation属性）和围绕某枢轴点的 3D 旋转角度;
//        (3) scaleX、scaleY：这两个属性控制着 View 围绕某枢轴点的 2D 缩放比例;
//        (4) pivotX 和 pivotY: 这两个属性控制着枢轴点的位置，前述的旋转和缩放都是以此点为中心展开的,缺省的枢轴点是 View 对象的中心点;
//        (5) x 和 y：这是指 View 在容器内的最终位置，等于 View 左上角相对于容器的坐标加上 translationX 和 translationY 后的值;
//        (6)alpha：表示 View 的 alpha 透明度。缺省值为 1 （不透明），为 0 则表示完全透明（看不见）;
        //        值可以在多个值之间变动，而不仅仅局限于从一个变化到另一个。
        final ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tv, "translationX", 0f, 600f);
        objectAnimator.setDuration(1500);
        objectAnimator.start();
    }

    private void doValueAnimator() {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f,600f,200f,0f);
//        ValueAnimator.ofObject()需要通过自定义TypeEvaluator来实现。
        //设置动画时长，单位毫秒
        valueAnimator.setDuration(1500);
        //设置动画的次数
//        valueAnimator.setRepeatCount(2);
        //重复模式，有RESTART(从头开始)和REVERSE(倒序开始)
//        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        //取消动画
//        valueAnimator.cancel();
        valueAnimator.start();
        //方法会在每一个动画帧的时候回调
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.d("TAG", "cuurent value is " + animation.getAnimatedValue());
                tv.setTranslationX((Float) animation.getAnimatedValue());

            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ObjectAnimator rotation = ObjectAnimator.ofFloat(tv, "rotation", 0f, 360f);
                rotation.setDuration(1500);
                rotation.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void doAnimatorSet() {
        ObjectAnimator moveIn = ObjectAnimator.ofFloat(tv, "translationX", 500f, 0f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(tv, "rotation", 0f, 360f);
        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(tv, "alpha", 1f, 0f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(rotate).with(fadeInOut).after(moveIn);
        animSet.setDuration(5000);
        animSet.start();
    }
}
