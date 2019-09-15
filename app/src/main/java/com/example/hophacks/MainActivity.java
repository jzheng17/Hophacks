package com.example.hophacks;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private ArrayList<CheckBox> storeOptionBoxes;

    private ArrayList<GroceryStore> stores;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    GroceryStore[] gss = (GroceryStore[]) stores.toArray();
                    Intent intent = new Intent(MainActivity.this, RSVPActivity.class);
                    intent.putExtra("checkedStoreList", gss);
                    // start the activity connect to the specified class
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    private CheckBox.OnCheckedChangeListener mOnCheckedChangeListener
            = new CheckBox.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(@NonNull CompoundButton cb, boolean b) {
            try {
                GroceryStore gs = (GroceryStore) cb.getTag();
                gs.setFollowStatus(b);
            } catch (NullPointerException | ClassCastException e) {
                //LOL :)
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Initialize our ArrayList of GroceryStores.
        stores = new ArrayList<>();
        stores.add(new GroceryStore("Charles Street Market", "3339 N Charles St.",
                420, 1440, 1350));
        stores.add(new GroceryStore("Eddie's Market", "3117 St. Paul St.",
                480, 1320, 1260));
        stores.add(new GroceryStore("Giant", "601 E 33rd St.",
                540, 1080, 1065));
        //
        //Initialize checkboxes appropriately.
        /*
        storeOptionBoxes = new ArrayList<>(stores.size());
        for (int i = 0; i < stores.size(); i++) {
            int resID = getResources().getIdentifier("checkBox" + (i + 1), "id", getPackageName());
            CheckBox cb = new CheckBox(this);
            cb.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            cb.setText(stores.get(i).toString());
            cb.setTag(stores.get(i));
            cb.setId(i);
            cb.setOnCheckedChangeListener(mOnCheckedChangeListener);
        }
        //*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        for (int i = 0; i < stores.size(); i++) {
            int resID = getResources().getIdentifier("checkBox" + (i + 1), "id", getPackageName());
            CheckBox cb = findViewById(resID);
            cb.setText(stores.get(i).toString());
            cb.setTag(stores.get(i));
            cb.setOnCheckedChangeListener(mOnCheckedChangeListener);
        }
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
