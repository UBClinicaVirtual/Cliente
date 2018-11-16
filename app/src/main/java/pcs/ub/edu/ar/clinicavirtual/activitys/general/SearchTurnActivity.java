package pcs.ub.edu.ar.clinicavirtual.activitys.general;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.ArrayList;

import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.clinic.ServerRequestSearchClinic;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.hcp.ServerRequestSearchHCP;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.speciality.ServerRequestSearchSpecialities;
import pcs.ub.edu.ar.clinicavirtual.handler.SearchClinicHandler;
import pcs.ub.edu.ar.clinicavirtual.handler.SearchHCPsHandler;
import pcs.ub.edu.ar.clinicavirtual.handler.SearchSpecialitiesHandler;

public class SearchTurnActivity extends BaseActivity  {

    Spinner spnClinic;
    Spinner spnHCP;
    Spinner spnSpeciality;
    Button  btnSince;
    Button  btnUntil;

    public static Integer ON_ACTIVITY_LOAD_CLINICS = -1;
    public static Integer ON_ACTIVITY_LOAD_SPECIALITIES = -2;
    public static Integer ON_ACTIVITY_LOAD_HCPS = -3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initScreen();
        setContentView(R.layout.activity_search_turn);
        initElements();

        Toast.makeText(this, "Cargando filtros...", Toast.LENGTH_SHORT).show();

        ServerRequestSearchClinic requestSearchClinic = new ServerRequestSearchClinic(ON_ACTIVITY_LOAD_CLINICS);
        requestSearchClinic.apiToken( apitoken() );
        connector().execute(requestSearchClinic,this);



    }

    public void initClinicSpinner(ArrayList<String> clinics) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,clinics);
        spnClinic.setAdapter(adapter);

        ServerRequestSearchSpecialities requestSearchSpecialities = new ServerRequestSearchSpecialities(ON_ACTIVITY_LOAD_SPECIALITIES);
        requestSearchSpecialities.apiToken( apitoken() );
        connector().execute(requestSearchSpecialities,this);
    }

    public void initSpecialitiesSpinner(ArrayList<String> specialities){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,specialities);
        spnSpeciality.setAdapter(adapter);


        ServerRequestSearchHCP serverRequestSearchHCP = new ServerRequestSearchHCP(ON_ACTIVITY_LOAD_HCPS);
        serverRequestSearchHCP.apiToken( apitoken() );
        connector().execute(serverRequestSearchHCP,this);
    }

    public void initHCPSpinner(ArrayList<String> hcps){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,hcps);
        spnHCP.setAdapter(adapter);
    }

    private void initElements() {
        spnClinic = (Spinner) findViewById(R.id.spnSearchTurnClinic);
        spnHCP = (Spinner)findViewById(R.id.spnSearchTurnHCP);
        spnSpeciality = (Spinner)findViewById(R.id.spnSearchTurnSpeciality);
        btnSince = (Button) findViewById(R.id.btnSearchTurnDateSince);
        btnUntil = (Button) findViewById(R.id.btnSearchTurnDateUntil);
    }

    private void initScreen() {
        android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.RED));
    }

    @Override
    public void loadHandlers(){
        handlers().put(ON_ACTIVITY_LOAD_CLINICS,new SearchClinicHandler());
        handlers().put(ON_ACTIVITY_LOAD_SPECIALITIES,new SearchSpecialitiesHandler());
        handlers().put(ON_ACTIVITY_LOAD_HCPS,new SearchHCPsHandler());

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void loadNextActivityHandler() {

    }
}
