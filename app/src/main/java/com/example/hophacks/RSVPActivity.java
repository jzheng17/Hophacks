package com.example.hophacks;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

public class RSVPActivity extends AppCompatActivity {
    //private TextView mTextMessage;
    //private ArrayList<TextView> storeRSVPBoxes;

    private GroceryStore[] checkedStores;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    Intent intent = new Intent(RSVPActivity.this, MainActivity.class);

                    // start the activity connect to the specified class
                    startActivity(intent);
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkedStores = (GroceryStore[]) getIntent().getSerializableExtra("checkedStoreList");
        setContentView(R.layout.activity_rsvp);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        for (int i = 0; i < checkedStores.length; i++) {
            int resID = getResources().getIdentifier("textBox" + (i + 1), "id", getPackageName());
            TextView cb = findViewById(resID);
            String out = checkedStores[i].getName() + "\n" + checkedStores[i].discountTimes();
            cb.setText(out);
            cb.setTag(checkedStores[i]);
            if (checkedStores[i].isBeingFollowed()) {
                cb.setVisibility(View.VISIBLE);
            } else {
                cb.setVisibility(View.INVISIBLE);
            }
        }
        //mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
