package com.example.android_city_hunter;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.android_city_hunter.fragment.ActivityFragment;
import com.example.android_city_hunter.fragment.HomeFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_ACTIVITY = 1;
    private static final int FRAGMENT_LOGOUT = 2;

    private static int currentFragment = FRAGMENT_HOME;

    NavigationView navigationView;

    ActivityResultLauncher<Intent> intentMaps = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result != null && result.getResultCode() == RESULT_OK) {
            navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        mDrawerLayout.addDrawerListener(drawerToggle);

        drawerToggle.syncState();

        // Handle listening on view navigation
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new HomeFragment());

        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            if (currentFragment != FRAGMENT_HOME) {
                replaceFragment(new HomeFragment());
                currentFragment = FRAGMENT_HOME;
                return true;
            }
        } else if (id == R.id.nav_activity) {
            if (currentFragment != FRAGMENT_ACTIVITY) {
                Intent mapsIntent = new Intent(this, MapsActivity.class);
                intentMaps.launch(mapsIntent);
                currentFragment = FRAGMENT_ACTIVITY;
                return true;
            }
        } else if (id == R.id.nav_logout) {
            if (currentFragment != FRAGMENT_LOGOUT) {
                Intent logoutIntent = new Intent(this, AuthenticationView.class);

                startActivity(logoutIntent);

                finish();
                return true;
            }
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_content_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}