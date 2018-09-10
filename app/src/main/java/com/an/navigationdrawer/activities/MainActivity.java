package com.an.navigationdrawer.activities;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.an.navigationdrawer.R;
import com.an.navigationdrawer.common.BaseActivity;
import com.an.navigationdrawer.config.AppConst;

public class MainActivity extends BaseActivity {

    // Constants
    public static final String TAG = MainActivity.class.getSimpleName();

    // Other objects
    private boolean mBackPressedToExitOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar(getResources().getString(R.string.main_activity_title), MainActivity.this);
        setDrawer();
    }

    @Override
    public void onBackPressed() {
        if (mBackPressedToExitOnce) {
            super.onBackPressed();

        } else {
            this.mBackPressedToExitOnce = true;
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mBackPressedToExitOnce = false;
                }
            }, AppConst.BACK_PRESSED_DELAY);
        }
    }
}
