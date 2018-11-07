package pcs.ub.edu.ar.clinicavirtual.activitys.patient;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.activitys.main.MainActivity;

public class MainScreenActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override //Metodo ingresado automaticamente por el activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initNavigationBar();


        String desc = getString(R.string.nav_header_subtitle);
        Toast.makeText(this, desc , Toast.LENGTH_SHORT).show();



    }


    private void initNavigationBar() {
        setContentView(R.layout.patient_activity_main_screen);
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

    @Override //Close Navigation Bar
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

        Intent intent = null;

        switch (id){
            case R.id.nav_searchturn:
                intent = new Intent(MainScreenActivity.this, SearchTurnActivity.class);
                break;
            case R.id.nav_myturns:
                intent = new Intent(MainScreenActivity.this, MyTurnsActivity.class);
                break;
            case R.id.nav_myprofile:
                intent = new Intent(MainScreenActivity.this, ProfileActivity.class);
                break;
            case R.id.nav_logout:
                intent = new Intent(MainScreenActivity.this, MainActivity.class);
                break;
        }

        startActivity(intent);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }
}
