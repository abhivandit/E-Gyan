package easyway2in.com.onlinetest;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;

import easyway2in.com.onlinetest.slidelist.AccountFragment;
import easyway2in.com.onlinetest.slidelist.HomeFragment;
import easyway2in.com.onlinetest.slidelist.MarksFragment;
import easyway2in.com.onlinetest.slidelist.TestsFragment;

import static android.R.attr.fragment;

public class LoginSuccess extends AppCompatActivity {
    PrefManager session;
    EditText Username, Password;
    TextView UsernameHV, AgeHV;
    private FragmentTransaction fragmentTransaction;
    private static DrawerLayout mDrawerLayout;
    private static ActionBarDrawerToggle mDrawerToggle;
    private static Toolbar toolbar;
    private static android.support.v4.app.FragmentManager fragmentManager;
    private static NavigationView navigationView;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_success);

        session = new PrefManager(getApplicationContext());

        initViews();

        setUpHeaderView();
        onMenuItemSelected();

        //At start set home fragment
        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.home);
            MenuItem item = navigationView.getMenu().findItem(R.id.home);
            setFragment(item);
        }
    }

    /*  Init all views  */
    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.slider_menu);
        fragmentManager = getSupportFragmentManager();

        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, toolbar, // nav menu toggle icon
                R.string.drawer_open, // nav drawer open - description for
                // accessibility
                R.string.drawer_close // nav drawer close - description for
                // accessibility
        ) {
            public void onDrawerClosed(View view) {
            }

            public void onDrawerOpened(View drawerView) {
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    /**
     * For using header view use this method
     **/
    public void setUpHeaderView() {
        View headerView = navigationView.inflateHeaderView(R.layout.header_view);
        UsernameHV = (TextView) headerView.findViewById(R.id.tvUsernameHV);
        String name =session.getUsername();
        UsernameHV.setText(name);
    }


    /*  Method for Navigation View item selection  */
    private void onMenuItemSelected() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                //Check and un-check menu item if they are checkable behaviour
                if (item.isCheckable()) {
                    if (item.isChecked()) item.setChecked(false);
                    else item.setChecked(true);
                }

                //Closing drawer on item click
                mDrawerLayout.closeDrawers();

                AccountFragment accountFragment = null;
                TestsFragment testsFragment = null;
                MarksFragment marksFragment = null;
                HomeFragment homeFragment = null;

                switch (item.getItemId()) {
                    case R.id.home:
                        setFragment(item);
                        homeFragment = new HomeFragment();
                        fragmentTransaction=fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_container, homeFragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.my_account:
                        setFragment(item);
                        accountFragment = new AccountFragment();
                        fragmentTransaction=fragmentManager.beginTransaction();
                        System.out.println("Frag Access");
                        fragmentTransaction.replace(R.id.frame_container, accountFragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.tests:
                        setFragment(item);
                        testsFragment = new TestsFragment();
                        fragmentTransaction=fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_container, testsFragment);
                        fragmentTransaction.commit();
                        break;
                    /*case R.id.marks:
                        setFragment(item);
                        marksFragment = new MarksFragment();
                        fragmentTransaction=fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_container, marksFragment);
                        fragmentTransaction.commit();
                        break;*/
                }

                return false;
            }
        });
    }

    @SuppressLint("NewApi")
    /*  Set Fragment, setting toolbar title and passing item title via bundle to fragments*/
    public void setFragment(MenuItem item) {
        toolbar.setTitle(item.getTitle());

        //Find fragment by tag
        android.support.v4.app.Fragment fr = fragmentManager.findFragmentByTag(item.getTitle().toString());

        HomeFragment homefragment = new HomeFragment();
        Bundle b = new Bundle();

        //If fragment is null replace fragment
        if (fr == null) {
            b.putString("data", item.getTitle().toString());
            homefragment.setArguments(b);//Set Arguments
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container,
                            homefragment, item.getTitle().toString())
                    .commit();
        }
    }

    //On back press check if drawer is open and closed
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT))
            mDrawerLayout.closeDrawers();
        else
            super.onBackPressed();
    }
}


/*
package easyway2in.com.onlinetest;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import android.widget.AdapterView.OnItemClickListener;

import easyway2in.com.onlinetest.slidelist.AccountFragment;
import easyway2in.com.onlinetest.slidelist.MarksFragment;
import easyway2in.com.onlinetest.slidelist.TestsFragment;

public class LoginSuccess extends AppCompatActivity {

    String[] menutitles;

    //navigation drawer title
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private List<RowItem> rowItems;
    private CustomAdapter adapter;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        //TODO auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        mTitle = mDrawerTitle = getTitle();

        menutitles = getResources().getStringArray(R.array.titles);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.slider_list);

        rowItems = new ArrayList<RowItem>();

        for(int i = 0; i < menutitles.length; i++){
            RowItem items = new RowItem(menutitles[i]);
            rowItems.add(items);
        }
        adapter = new CustomAdapter(getApplicationContext(), rowItems);

        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new SlideitemListener());

        //enabling action bar app icon and behaving it as toggle button
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, // nav menu toggle icon
                R.string.sliding, //nav drawer open - description for
                                    //accessibility
                R.string.sliding //nav drawer close - description for
                //accessibility
                ) {
            public void onDrawerClosed(View view){
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        if(savedInstanceState == null){
            //on first time display view for first nav item
            updateDisplay(0);
        }
    }

    class SlideitemListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            updateDisplay(position);
        }
    }

    @SuppressLint("NewApi")
    private void updateDisplay(int position){
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new AccountFragment();
                break;
            case 1:
                fragment = new TestsFragment();
                break;
            case 2:
                fragment = new MarksFragment();
                break;
            default:
                break;
        }

        if(fragment != null){
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();

            //update selected item and title, then close the drawer
          // setTitle(menutitles.);
           // mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            //error in creating fragment
            Log.e("Main activity", "Error in creating fragment");
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
      //  getMenuInflater().inflate(R.menu, menu);
        return true;
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
*/
