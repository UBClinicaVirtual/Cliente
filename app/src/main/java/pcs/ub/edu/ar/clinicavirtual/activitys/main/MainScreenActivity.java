package pcs.ub.edu.ar.clinicavirtual.activitys.main;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.enums.USER_TYPE;
import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.activitys.general.MyTurnsActivity;
import pcs.ub.edu.ar.clinicavirtual.activitys.general.SearchTurnActivity;
import pcs.ub.edu.ar.clinicavirtual.activitys.start.MainActivity;
import pcs.ub.edu.ar.clinicavirtual.activitys.profile.ProfileActivity;
import pcs.ub.edu.ar.clinicavirtual.interfaces.handler.IActivityVisibilityHandler;

public class MainScreenActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override //Metodo ingresado automaticamente por el activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initNavigationBar();


        String desc = getString(R.string.nav_header_subtitle);
        Toast.makeText(this, desc , Toast.LENGTH_SHORT).show();

        //Pido el json de la variable statica para disparar el handle de controles de la ventana
        //(deberia ser un metodo y no un acceso directo a la variable)
        this.visibilityHandlers().get( BaseActivity.userTypeId() ).accept(this);
    }

    @Override
    protected void loadNextActivityHandler() {
        //nextActivityHandlers().put()
    }

    @Override
    public void loadVisibilityHandlers(){

        //if its a patient hides the user type name
        this.visibilityHandlers().put(USER_TYPE.PATIENT.getValue(), new IActivityVisibilityHandler() {
            @Override
            public void accept(BaseActivity activity) {
                NavigationView navigationView = (NavigationView) findViewById(R.id.navMenu);
                View headerView = navigationView.getHeaderView(0);
                TextView navUsername = (TextView) headerView.findViewById(R.id.nav_profile);
                navUsername.setVisibility(View.INVISIBLE);

            }
        });

        // If its a hcp, hides the name of the user
        this.visibilityHandlers().put(USER_TYPE.HCP.getValue(), new IActivityVisibilityHandler() {
            @Override
            public void accept(BaseActivity activity) {
                NavigationView navigationView = (NavigationView) findViewById(R.id.navMenu);
                View headerView = navigationView.getHeaderView(0);
                TextView navUsername = (TextView) headerView.findViewById(R.id.nav_patient_name);
                navUsername.setVisibility(View.INVISIBLE);
            }
        });
/*
        //Ejemplo para crear multiples handlers
        IActivityVisibilityHandler handler = new MainScreenVisibilityHandler();
        this.visibilityHandlers().put(USER_TYPE.CLINIC.getValue(), handler);
        this.visibilityHandlers().put(USER_TYPE.NEW_USER.getValue(), handler);
*/
    }

    private void initScreen() {
        android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.RED));
    }


    private void initNavigationBar() {
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



        return true;
    }

    public void closeDrawer(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
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
