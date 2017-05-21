package com.example.anandaron.yesmam;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import Fragments.UpdateAttendanceFragment;
import Fragments.DiscussionsFragment;
import Fragments.FeedBackFragment;
import Fragments.MarksFragment;
import Fragments.WallFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedData.userName= PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("USER_NAME",null) ;
        SharedData.access= PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("ACCESS",null);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                TextView navTextView = (TextView) findViewById(R.id.nav_tv1) ;
                TextView navTextView2 = (TextView) findViewById(R.id.nav_tv2) ;
                navTextView.setText(SharedData.userName);
                navTextView2.setText(SharedData.access);
            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
            toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_wall) {
            Fragment frag = new WallFragment();
            getFragmentManager().beginTransaction().replace(R.id.content_home,frag).commit();
            // Handle the camera action
        } else if (id == R.id.nav_attendance) {
            Fragment frag = new UpdateAttendanceFragment();
            getFragmentManager().beginTransaction().replace(R.id.content_home,frag).commit();

        } else if (id == R.id.nav_marks) {
            Fragment frag = new MarksFragment();
            getFragmentManager().beginTransaction().replace(R.id.content_home,frag).commit();
        } else if (id == R.id.nav_discussions) {
            Fragment frag = new DiscussionsFragment();
            getFragmentManager().beginTransaction().replace(R.id.content_home,frag).commit();
        } else if (id == R.id.nav_feedback) {
            Fragment frag = new FeedBackFragment();
            getFragmentManager().beginTransaction().replace(R.id.content_home,frag).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
