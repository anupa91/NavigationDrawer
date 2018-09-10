package com.an.navigationdrawer.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.an.navigationdrawer.R;
import com.an.navigationdrawer.activities.MainActivity;
import com.an.navigationdrawer.fragments.DrawerFragment;

public class BaseActivity extends AppCompatActivity implements DrawerFragment.FragmentDrawerListener {

    // constants
    private static final String TAG = BaseActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private DrawerFragment mDrawerFragment;
    private DrawerLayout mDrawerLayout;

    public BaseActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void setToolbar(String toolbarName, Activity activity) {
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        TextView tvTitle = findViewById(R.id.tv_toolbar_title);
        tvTitle.setText(toolbarName);

        setSupportActionBar(mToolbar);
        if (!activity.getClass().getSimpleName().equals(MainActivity.class.getSimpleName())) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void setDrawer() {
        mDrawerLayout = findViewById(R.id.drawer_layout);

        mDrawerFragment = (DrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        mDrawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        mDrawerFragment.setDrawerListener(this);
    }

    @Override
    public void onDrawerItemSelected(int position) {
        displayView(this, position);
    }

    private void displayView(final Activity activity, int position) {
        switch (position) {
            case 0:
                if (!activity.getClass().getSimpleName().equals(MainActivity.class.getSimpleName())) {
                    activity.finish();
                    activity.overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                }
                break;
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            super.onBackPressed();
            overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
        }
    }
}
