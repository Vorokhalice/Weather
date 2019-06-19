package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        int orientation = getResources().getConfiguration().orientation;
        Fragment fragment1 = new ListFragment();
        Fragment fragment2 = new DetailFragment();
        View view1 = findViewById(R.id.layout_list);
        View view2 = findViewById(R.id.layout_detail);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (view2 != null) {
                ViewGroup parent = (ViewGroup) view2.getParent();
                if (parent != null)
                    parent.removeView(view2);
            }
            fragmentTransaction.add(R.id.layout_list, fragment1);
            ((LinearLayout.LayoutParams) view1.getLayoutParams()).weight = 2;
        } else {
            fragmentTransaction.add(R.id.layout_list, fragment1);
            fragmentTransaction.add(R.id.layout_detail, fragment2);
            ((LinearLayout.LayoutParams) view1.getLayoutParams()).weight = 1;
        }
        fragmentTransaction.commit();
    }
}
