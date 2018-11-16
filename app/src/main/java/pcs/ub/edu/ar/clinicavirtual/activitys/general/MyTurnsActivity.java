package pcs.ub.edu.ar.clinicavirtual.activitys.general;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.user.ServerRequestUserGetPatientAppointments;
import pcs.ub.edu.ar.clinicavirtual.data.Appointment;
import pcs.ub.edu.ar.clinicavirtual.data.AppointmentAdapter2;
import pcs.ub.edu.ar.clinicavirtual.handler.GetPatientAppointmentsHandler;

public class MyTurnsActivity extends BaseActivity {

    //Button mSearchMyTurns;
    //TextView mMyTurns;

   // private Toolbar mToolbar;
    //private Fragment mTurnsFragment;
    private static Integer ON_LOAD = -1;

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager Imanager;
    private static List<Appointment> items = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_turns);


        Toast.makeText(this, "Cargando turnos...", Toast.LENGTH_SHORT).show();

        getAppointments();


        List<Appointment> items = new ArrayList<>();

        items.add(new Appointment(8820,123,"Clinica de la trinidad",124,"Traumatologo",9988,
                "Bernabe","Marquez","2018/04/01 16:50",1,"Pending"));
        items.add(new Appointment(8822,124,"Hospital Italiano",125,"Pediatria",9989,
                "Fernando","Romero","2018/04/01 16:52",1,"Pending"));
        items.add(new Appointment(8825,125,"Clinica de la trinidad",126,"Salud mental",9991,
                "Gabriel","Espina","2018/04/01 16:53",1,"Pending"));


        recycler = (RecyclerView) findViewById(R.id.recycler);
       // LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(MyTurnsActivity.this,
              //  LinearLayoutManager.VERTICAL,false);
        //recycler.setLayoutManager(mLinearLayoutManager);
        recycler.setHasFixedSize(true);

        Imanager = new LinearLayoutManager(this);
        recycler.setLayoutManager(Imanager);

        adapter = new AppointmentAdapter2(MyTurnsActivity.this,items);
        recycler.setAdapter(adapter);




        /*
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_turns);
        //initScreen();
       // initElements();
       // initListeners();
       // mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTurnsFragment = getSupportFragmentManager().findFragmentById(R.id.turns_container);

       // setUpToolbar();
        setUpTurnsFragment();

        //getAppointments();
*/
    }

    private void getAppointments() {
        ServerRequestUserGetPatientAppointments getPatientAppointments = new ServerRequestUserGetPatientAppointments(ON_LOAD);
        getPatientAppointments.apiToken( apitoken() );
        connector().execute(getPatientAppointments,this);


    }

    public void loadAppointment(String response)throws JSONException {

        items.clear();

        JSONObject json = new JSONObject(response);
        JSONArray jsonArray = json.getJSONArray("appointments");
        for(int i = 0; i<jsonArray.length(); i++){
            json = jsonArray.getJSONObject(i);
            items.add(new Appointment(json.getInt("id"),json.getInt("clinic_id"),
                    json.getString("clinic_business_name"),json.getInt("speciality_id"),
                    json.getString("speciality_name"),json.getInt("hcp_id"),
                    json.getString("hcp_first_name"),json.getString("hcp_last_name"),
                    json.getString("appointment_date"),json.getInt("appointment_status_id"),
                    json.getString("appointment_status_name")));
        }

        showAppointments();

    }

    private  void showAppointments() {
        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);

        Imanager = new LinearLayoutManager(this);
        recycler.setLayoutManager(Imanager);

        adapter = new AppointmentAdapter2(items);
        recycler.setAdapter(adapter);
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
    /*
    private void setUpTurnsFragment() {
        if(mTurnsFragment == null){
            mTurnsFragment = TurnsFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.turns_container, mTurnsFragment)
                    .commit();
        }
    }
    */
/*
    private void setUpToolbar() {
        setSupportActionBar(mToolbar);
    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
       // getMenuInflater().inflate(R.menu.menu_turns, menu);
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

    @Override
    protected void loadHandlers() {
        this.handlers().put(ON_LOAD, new GetPatientAppointmentsHandler());


    }

}
