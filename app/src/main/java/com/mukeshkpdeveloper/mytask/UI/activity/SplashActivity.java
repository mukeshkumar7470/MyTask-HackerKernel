package com.mukeshkpdeveloper.mytask.UI.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.mukeshkpdeveloper.mytask.R;
import com.mukeshkpdeveloper.mytask.utils.AppPreference;
import com.mukeshkpdeveloper.mytask.utils.Constant;

public class SplashActivity extends AppCompatActivity {
    private final int splashScreenDuration = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        View mSplashImage = findViewById(R.id.iv_splash);
        Animation splashAnimImage = AnimationUtils.loadAnimation(this, R.anim.splash_anim_img);
        mSplashImage.startAnimation(splashAnimImage);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                gotoMainActivity();
                finish();
            }
        }, splashScreenDuration);
    }
    private void gotoMainActivity(){

        if (AppPreference.getBooleanPreference(SplashActivity.this, Constant.IS_LOGIN)){
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}