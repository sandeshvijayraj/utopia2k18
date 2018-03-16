package com.example.vijayrajbafna.utopia2k18;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    WebView vv;
    private int SPLASH_TIME_OUT = 5600;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (vv.canGoBack()) {
                        vv.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        vv = (WebView) findViewById(R.id.webber);
        WebSettings webset = vv.getSettings();
        webset.setJavaScriptEnabled(true);
        vv.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                if (isNetworkAvailable()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent homeintent = new Intent(MainActivity.this, homeactivity.class);
                            startActivity(homeintent);
                            finish();
                        }
                    }, SPLASH_TIME_OUT);
                }
            }
        });
        thread1 tt = new thread1();
        webset.setCacheMode(WebSettings.LOAD_NO_CACHE);
        vv.loadUrl("https://bafnasweather.000webhostapp.com/gfgf.html");
        tt.start();
    }

    public class thread1 extends Thread {
        public void run() {
            final ImageView img = (ImageView) findViewById(R.id.imageer);
            Animation fadeout = new AlphaAnimation(2, 0);
            fadeout.setInterpolator(new AccelerateInterpolator());
            fadeout.setStartOffset(800);
            fadeout.setDuration(1200);
            fadeout.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    img.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            img.setAnimation(fadeout);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        int id = android.os.Process.myPid();
        android.os.Process.killProcess(id);
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
