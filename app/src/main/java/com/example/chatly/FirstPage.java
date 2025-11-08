package com.example.chatly;

import android.os.Bundle;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.activity.OnBackPressedCallback;

import com.google.android.material.navigation.NavigationView;

public class FirstPage extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    private final OnBackPressedCallback backCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                setEnabled(false);
                getOnBackPressedDispatcher().onBackPressed();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        // تنظیم Navigation Drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new ChatsFragment())
                    .commit();
            navigationView.setCheckedItem(R.id.nav_chats);
        }

        setupActionBarAndDrawer();

        getOnBackPressedDispatcher().addCallback(this, backCallback);
    }

    private void setupActionBarAndDrawer() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view); // اینجا مقداردهی

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_chats) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new ChatsFragment())
                        .commit();
                getSupportActionBar().setTitle("Chatly");

            } else if (id == R.id.nav_saved_messages) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new SavedMessagesFragment())
                        .commit();
                getSupportActionBar().setTitle("Saved Messages");

            } else if (id == R.id.nav_settings) {
                // بعداً تنظیمات رو اضافه کن
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }
    public void loadSavedMessages() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new SavedMessagesFragment())
                .commit();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Saved Messages");
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return true;
    }
}