package com.salonig.studentdemo;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class WelcomeActivity extends AppCompatActivity {
//
//    private DrawerLayout mdrawerlayout;
//  //  private ActionBarDrawerToggle mtoggle;
//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test);


        final DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });



//        mdrawerlayout = (DrawerLayout)findViewById(R.id.drawer_layout);
////        mtoggle = new ActionBarDrawerToggle(this, mdrawerlayout,R.string.open,R.string.close);
////
////        mdrawerlayout.addDrawerListener(mtoggle);
////        mtoggle.syncState();
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//
//        if(mtoggle.onOptionsItemSelected(item)){
//
//            return  true;
//        }

        return super.onOptionsItemSelected(item);
    }
}






