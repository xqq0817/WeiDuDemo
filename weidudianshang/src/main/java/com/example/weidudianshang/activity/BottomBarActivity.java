package com.example.weidudianshang.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.weidudianshang.R;
import com.example.weidudianshang.fragment.FragmentCar;
import com.example.weidudianshang.fragment.FragmentCircle;
import com.example.weidudianshang.fragment.FragmentFind;
import com.example.weidudianshang.fragment.FragmentHomePager;
import com.example.weidudianshang.fragment.FragmentMy;

public class BottomBarActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.na_homepager:
                    viewpager.setCurrentItem(0);
                    return true;
                case R.id.na_circle:
                    viewpager.setCurrentItem(1);
                    return true;
                case R.id.na_car:
                    viewpager.setCurrentItem(2);
                    return true;
                case R.id.na_find:
                    viewpager.setCurrentItem(3);
                    return true;
                case R.id.na_my:
                    viewpager.setCurrentItem(4);
                    return true;
            }
            return false;
        }
    };
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_bar);

        viewpager = findViewById(R.id.viewpager);
        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i){
                    case 0:
                        return new FragmentHomePager();
                    case 1:
                        return new FragmentCircle();
                    case 2:
                        return new FragmentCar();
                    case 3:
                        return new FragmentFind();
                    case 4:
                        return new FragmentMy();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 5;
            }
        });
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        navigation.setSelectedItemId(R.id.na_homepager);
                        break;
                    case 1:
                        navigation.setSelectedItemId(R.id.na_circle);
                        break;
                    case 2:
                        navigation.setSelectedItemId(R.id.na_car);
                        break;
                    case 3:
                        navigation.setSelectedItemId(R.id.na_find);
                        break;
                    case 4:
                        navigation.setSelectedItemId(R.id.na_my);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
