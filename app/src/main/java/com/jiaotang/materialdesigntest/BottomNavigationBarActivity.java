package com.jiaotang.materialdesigntest;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class BottomNavigationBarActivity extends AppCompatActivity {

    private BottomNavigationBar bottomNavigationBar;
    private BottomNavigationItem item1;
    private BottomNavigationItem item2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_bar);

        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);//两种模式。static是点中后显示
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

        item1 = new BottomNavigationItem(R.drawable.ic_menu_camera,"111").setActiveColorResource(android.R.color.holo_blue_dark).setInActiveColor(Color.GRAY);
        item2 = new BottomNavigationItem(R.drawable.ic_menu_camera,"222").setActiveColorResource(android.R.color.holo_red_dark).setInActiveColorResource(android.R.color.darker_gray);
//
//
//        bottomNavigationBar.addItem(item1);
//        bottomNavigationBar.addItem(item2);
//        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_menu_camera,"333").setActiveColorResource(android.R.color.holo_orange_dark).setInActiveColorResource(android.R.color.darker_gray));
//        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_menu_camera,"444").setActiveColorResource(android.R.color.holo_green_dark).setInActiveColorResource(android.R.color.holo_purple));
//        bottomNavigationBar.setBarBackgroundColor(android.R.color.black);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_menu_camera,"111").setInactiveIconResource(R.drawable.ic_menu_camera_new).setActiveColorResource(android.R.color.holo_green_dark));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_menu_camera,"222").setInactiveIconResource(R.drawable.ic_menu_camera_new).setActiveColorResource(android.R.color.holo_green_dark));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_menu_camera,"333").setInactiveIconResource(R.drawable.ic_menu_camera_new).setActiveColorResource(android.R.color.holo_green_dark));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_menu_camera,"444").setInactiveIconResource(R.drawable.ic_menu_camera_new).setActiveColorResource(android.R.color.holo_green_dark));

        bottomNavigationBar.setFirstSelectedPosition(1);
        bottomNavigationBar.initialise();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {

            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });



    }
}
