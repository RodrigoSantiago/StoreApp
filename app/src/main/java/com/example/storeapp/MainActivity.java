package com.example.storeapp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.widget.SearchView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_products, R.id.nav_detail, R.id.nav_cart, R.id.nav_profile,
                R.id.nav_login,  R.id.nav_signup,
                R.id.nav_orders)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        if (searchMenuItem == null) {
            return true;
        }

        final SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        ImageView img = searchView.findViewById(R.id.search_close_btn);
        img.setImageDrawable(getResources().getDrawable(R.drawable.ic_close));

        EditText searchEditText = searchView.findViewById(R.id.search_src_text);
        searchEditText.setTextColor(Color.DKGRAY);
        searchEditText.setHintTextColor(Color.DKGRAY);

        setItemsVisibility(menu, searchMenuItem, true);

        searchMenuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            Drawable d;
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Set styles for expanded state here
                if (getSupportActionBar() != null) {
                    setItemsVisibility(menu, item, false);
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                }
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Set styles for collapsed state here
                if (getSupportActionBar() != null) {
                    setItemsVisibility(menu, item, true);
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
                    d = null;
                }
                return true;
            }
        });

        return true;
    }

    private void setItemsVisibility(Menu menu, MenuItem exception, boolean visible) {
        for (int i=0; i<menu.size(); ++i) {
            MenuItem item = menu.getItem(i);
            if (item.getItemId() == R.id.action_filter) item.setVisible(!visible);
            else if (item != exception) item.setVisible(visible);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
