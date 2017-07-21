package com.example.propertyanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ValueAnimatorActivity extends AppCompatActivity {

    private MyAnimView my;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator);
        my = (MyAnimView) findViewById(R.id.my);
    }
}
