package com.example.mr_g.bottombar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.mr_gao.bottombar.BottomBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomBarView bottombar = (BottomBarView) findViewById(R.id.bottombar);
        String[]  s={ "hah","asdfasd","adsfsa","sdfkjh"};

        int[] image = { R.drawable.github_circle,R.drawable.github_circle, R.drawable.ic_favorite_black_24dp,
                 R.drawable.github_circle};
        bottombar.setUpWithView(image,s);
    }
}
