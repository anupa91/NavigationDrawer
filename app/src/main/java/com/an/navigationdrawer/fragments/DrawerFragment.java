package com.an.navigationdrawer.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.an.navigationdrawer.R;
import com.an.navigationdrawer.activities.MainActivity;
import com.an.navigationdrawer.adapters.NavigationDrawerAdapter;
import com.an.navigationdrawer.config.AppConst;
import com.an.navigationdrawer.dto.NavDrawerItem;
import com.an.navigationdrawer.listeners.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class DrawerFragment extends Fragment implements OnItemClickListener<NavDrawerItem> {

    // constants
    private static String TAG = DrawerFragment.class.getSimpleName();

    private static String[] mTitles = null;

    // UI components
    private RecyclerView mRvDrawerList;
    private TextView mTvLoggedUser, mTvLoggedUserEmail;
    private LinearLayout mLlLogout;

    private Activity mActivity;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private View mContainerView;
    private NavigationDrawerAdapter mAdapter;
    private FragmentDrawerListener mDrawerListener;

    public DrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO - You can find drawer labels from here
        mTitles = getActivity().getResources().getStringArray(R.array.nav_drawer_labels);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflating view layout
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        mActivity = getActivity();

        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRvDrawerList = view.findViewById(R.id.fragment_navigation_drawer_rv_drawer_list);

        mAdapter = new NavigationDrawerAdapter(mActivity, getData(), this);
        mRvDrawerList.setAdapter(mAdapter);
        mRvDrawerList.setLayoutManager(new LinearLayoutManager(getContext()));

        mTvLoggedUser = view.findViewById(R.id.fragment_navigation_drawer_tv_logged_user);
        mTvLoggedUserEmail = view.findViewById(R.id.fragment_navigation_drawer_tv_logged_user_email);

        mTvLoggedUser.setText("Logged user");
        mTvLoggedUserEmail.setText("Logged user details");
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        mContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                //TODO - Can change toolbar color when drawer opens
                //toolbar.setAlpha(1 - slideOffset / 2);
            }
        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    @Override
    public void onItemClick(int position, NavDrawerItem data) {
        mDrawerListener.onDrawerItemSelected(position);
        mDrawerLayout.closeDrawer(mContainerView);
    }

    public void setDrawerListener(FragmentDrawerListener listener) {
        this.mDrawerListener = listener;
    }

    public List<NavDrawerItem> getData() {
        List<NavDrawerItem> data = new ArrayList<>();

        // preparing navigation drawer items
        for (int i = 0; i < mTitles.length; i++) {
            NavDrawerItem navItem = new NavDrawerItem();
            navItem.setTitle(mTitles[i]);
            if (mActivity.getClass().getSimpleName().equals(MainActivity.class.getSimpleName())) {
                if (navItem.getTitle().equals(AppConst.NAV_ITEM_1)) {
                    navItem.setSelectedItem(true);
                }
            }
            // TODO - Add selected item according to mActivity
            data.add(navItem);
        }
        return data;
    }

    public interface FragmentDrawerListener {
        void onDrawerItemSelected(int position);
    }

}
