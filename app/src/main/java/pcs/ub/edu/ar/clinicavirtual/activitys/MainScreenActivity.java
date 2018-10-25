package pcs.ub.edu.ar.clinicavirtual.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces.IServerRequest;

public class MainScreenActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override //Metodo ingresado automaticamente por el activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navMenu);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override //Metodo ingresado automaticamente por el activity
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override //Metodo ingresado automaticamente por el activity
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_screen, menu);
        return true;
    }



    @Override //Metodo ingresado automaticamente por el activity
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_searchturn) {
            //DIRIGE AL ACTIVITY SEARCH TURN
            Intent mIntentSearchTurn = new Intent(MainScreenActivity.this, SearchTurnActivity.class);
            startActivity(mIntentSearchTurn);

        } else if (id == R.id.nav_myturns) {
            //DIRIGE AL ACTIVITY MY TURNS
            Intent mIntentMyTurns = new Intent(MainScreenActivity.this, MyTurnsActivity.class);
            startActivity(mIntentMyTurns);

        } else if (id == R.id.nav_myprofile) {
            //DIRIGE AL ACTIVITY MY PROFILE
            Intent mIntentMyProfile = new Intent(MainScreenActivity.this, ProfileActivity.class);
            startActivity(mIntentMyProfile);

        } else if (id == R.id.nav_logout) {
            //DIRIGE AL ACTIVITY SIGN-IN
            Intent mIntentLogOut = new Intent(MainScreenActivity.this, MainActivity.class);
            startActivity(mIntentLogOut);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void success(IServerRequest request) {

    }
}
