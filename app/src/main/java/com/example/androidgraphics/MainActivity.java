package com.example.androidgraphics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private My3DGraphView mMyView=null;//a custom view for drawing

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).hide();//hide the title bar
        mMyView = new My3DGraphView(this);
        setContentView(mMyView);
    }
}