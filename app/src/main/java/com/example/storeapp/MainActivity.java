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
import android.widget.TextView;

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
    private NavController navController;
    private TextView textCartItemCount;

    public static int selectedProduct;
    public static int searchPage = 0;
    public static int searchPages = 10;
    public static int cartCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_products, R.id.nav_detail, R.id.nav_cart, R.id.nav_profile,
                R.id.nav_login,  R.id.nav_signup, R.id.nav_orders)
                .setDrawerLayout(drawer)
                .build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);


        //---------- SEARCH ------------//
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
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

        //------------ CART --------------//
        final MenuItem cartMenuItem = menu.findItem(R.id.action_cart);
        final View cartView = cartMenuItem.getActionView();
        textCartItemCount = cartView.findViewById(R.id.cart_badge);

        setupBadge();

        cartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(cartMenuItem);
            }
        });

        return true;
    }

    private void setItemsVisibility(Menu menu, MenuItem exception, boolean visible) {
        for (int i = 0; i < menu.size(); ++i) {
            MenuItem item = menu.getItem(i);
            if (item.getItemId() == R.id.action_filter) item.setVisible(!visible);
            else if (item != exception) item.setVisible(visible);
        }
    }

    private void setupBadge() {
        if (textCartItemCount != null) {
            if (cartCount == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(cartCount, 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_cart) {
            navController.navigate(R.id.nav_cart);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void onProductCardClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof Integer) {
            selectedProduct = (int) view.getTag();
            navController.navigate(R.id.nav_detail);
        }
    }
}
