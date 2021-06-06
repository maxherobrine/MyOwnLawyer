package com.example.applayer;

import android.accessibilityservice.AccessibilityService;
import android.app.ActivityManager;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {

    private CheckBox agree;
    private Button next;
    private ListView mDrawerListView;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp=getSharedPreferences("First_Time", Context.MODE_PRIVATE);
        boolean first=sp.getBoolean("First_Time",true);
        if(first)
        {
            setContentView(R.layout.activity_main);
            agree = (CheckBox) findViewById(R.id.agree);
            next = (Button) findViewById(R.id.next);
            agree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!agree.isChecked()) {
                        next.setEnabled(false);
                    } else
                        next.setEnabled(true);
                }
            });
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences sp=getSharedPreferences("First_Time", Context.MODE_PRIVATE);
                    SharedPreferences.Editor e = sp.edit();
                    e.putBoolean("First_Time",false);
                    e.apply();
                    main();
                }
            });
        }
        else {
            main();
        }
    }

    protected void main() {
        setContentView(R.layout.main);
        setTitle("Сам себе адвокат");
        String[] NavTitles = getResources().getStringArray(R.array.navigation_array);
        mDrawerListView = (ListView) findViewById(R.id.left_drawer);
        mDrawerListView.setAdapter(new ArrayAdapter<String>(this, R.layout.navigation, NavTitles));
        mDrawerListView.setOnItemClickListener(new DrawerItemClickListener());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        Hamburger();
    }
    protected void doing()
    {
        setContentView(R.layout.doing);
        setTitle("Что делать?");
        String[] NavTitles = getResources().getStringArray(R.array.navigation_array);
        mDrawerListView = (ListView) findViewById(R.id.left_drawer);
        mDrawerListView.setAdapter(new ArrayAdapter<String>(this, R.layout.navigation, NavTitles));
        mDrawerListView.setOnItemClickListener(new DrawerItemClickListener());
        Hamburger();
    }
    protected void laws(){
        setContentView(R.layout.laws);
        setTitle("Законы");
        String[] NavTitles = getResources().getStringArray(R.array.navigation_array);
        mDrawerListView = (ListView) findViewById(R.id.left_drawer);
        mDrawerListView.setAdapter(new ArrayAdapter<String>(this, R.layout.navigation, NavTitles));
        mDrawerListView.setOnItemClickListener(new DrawerItemClickListener());
        Hamburger();
    }
    protected void tests(){
        setContentView(R.layout.tests);
        setTitle("Тесты");
        String[] NavTitles = getResources().getStringArray(R.array.navigation_array);
        mDrawerListView = (ListView) findViewById(R.id.left_drawer);
        mDrawerListView.setAdapter(new ArrayAdapter<String>(this, R.layout.navigation, NavTitles));
        mDrawerListView.setOnItemClickListener(new DrawerItemClickListener());
        Hamburger();
    }
    protected void about(){
        setContentView(R.layout.about);
        setTitle("О приложении");
        String[] NavTitles = getResources().getStringArray(R.array.navigation_array);
        mDrawerListView = (ListView) findViewById(R.id.left_drawer);
        mDrawerListView.setAdapter(new ArrayAdapter<String>(this, R.layout.navigation, NavTitles));
        mDrawerListView.setOnItemClickListener(new DrawerItemClickListener());
        Hamburger();
    }
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(position==0)
            {
                main();
            }
           else if(position==1)
            {
               doing();
            }
          else  if(position==2)
            {
                laws();
            }
           else if(position==3)
            {
                tests();
            }
          else  if(position==4)
            {
                about();
            }
            else
                about();
        }
        }

        private void Hamburger() {
            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            toggle = new ActionBarDrawerToggle(
                    this,                  // host Activity
                    drawer,         // DrawerLayout object
                    null,  // значок-гамбургер для замены стрелки 'Up'
                    R.string.drawer_open,
                    R.string.drawer_close
            );
            drawer.addDrawerListener(toggle);
            toggle.syncState();
        }

   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        toggle.onConfigurationChanged(newConfig);
    }

    //При нажатии на кнопку "назад" закрывается меню
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}


