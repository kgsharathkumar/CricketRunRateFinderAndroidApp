package com.kbtci.kbpl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by ext-kgsharat on 30-11-2016.
 */
public class SplashActivity extends Activity {

    /** The loader. */
    private Handler loader = new Handler();

    /** The loader runnable. */
    private Runnable loaderRunnable = new Runnable() {

        @Override
        public void run() {

            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }

    @Override
    protected void onPause() {
        super.onPause();
        loader.removeCallbacks(loaderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loader.removeCallbacks(loaderRunnable);
        // keep spash screen for 3 seconds
        loader.postDelayed(loaderRunnable, 3000);
        //Immaculate
    }
}
