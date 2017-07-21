package com.example.propertyanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ObjectAnimatorActivity extends AppCompatActivity {

    private MyAnimView1 my1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animator);
        my1 = (MyAnimView1) findViewById(R.id.my1);
    }
}
