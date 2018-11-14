package pcs.ub.edu.ar.clinicavirtual.activitys.general;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.user.ServerRequestUserGetPatientAppointments;
import pcs.ub.edu.ar.clinicavirtual.handler.GetPatientAppointmentsHandler;

public class MyTurnsActivity extends BaseActivity {

    //Button mSearchMyTurns;
    //TextView mMyTurns;

    private Toolbar mToolbar;
    private Fragment mTurnsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_turns);
        //initScreen();
       // initElements();
       // initListeners();
       // mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTurnsFragment = getSupportFragmentManager().findFragmentById(R.id.turns_container);

       // setUpToolbar();
        setUpTurnsFragment();
    }
/*
    private void initScreen() {
        android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.RED));
    }

    private void initListeners() {
        findViewById(R.id.btnPatientMyTurns).setOnClickListener(this);
    }

    private void initElements() {
       // mSearchMyTurns = (Button) findViewById(R.id.btnPatientMyTurns);
       // mMyTurns = (TextView) findViewById(R.id.txtMyTurns);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTurnsFragment = getSupportFragmentManager().findFragmentById(R.id.turns_container);
        
        setUpToolbar();
        setUpTurnsFragment();


    }
*/
    private void setUpTurnsFragment() {
        if(mTurnsFragment == null){
            mTurnsFragment = TurnsFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.turns_container, mTurnsFragment)
                    .commit();
        }
    }
/*
    private void setUpToolbar() {
        setSupportActionBar(mToolbar);
    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_turns, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
/*
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */

    @Override
    public void onClick(View v) {
/*
        switch (v.getId()) {
            case R.id.btnPatientMyTurns:
                ServerRequestUserGetPatientAppointments getPatientAppointments = new ServerRequestUserGetPatientAppointments(R.id.btnPatientMyTurns);
                getPatientAppointments.apiToken( apitoken() );
                connector().execute(getPatientAppointments,this);

        }
*/
    }

    @Override
    protected void loadNextActivityHandler() {

    }
/*
    @Override
    protected void loadHandlers() {
        this.handlers().put(R.id.btnPatientMyTurns, new GetPatientAppointmentsHandler());
    }
    */
}
