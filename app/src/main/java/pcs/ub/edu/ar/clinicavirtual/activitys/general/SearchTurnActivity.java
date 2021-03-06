package pcs.ub.edu.ar.clinicavirtual.activitys.general;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.appointment.ServerRequestSearchAvailableAppointments;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.clinic.ServerRequestSearchClinic;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.hcp.ServerRequestSearchHCP;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.speciality.ServerRequestSearchSpecialities;
import pcs.ub.edu.ar.clinicavirtual.handler.SearchAvailableAppointmentsHandler;
import pcs.ub.edu.ar.clinicavirtual.handler.SearchClinicHandler;
import pcs.ub.edu.ar.clinicavirtual.handler.SearchHCPsHandler;
import pcs.ub.edu.ar.clinicavirtual.handler.SearchSpecialitiesHandler;

public class SearchTurnActivity extends BaseActivity  implements Serializable{

    Spinner spnClinic;
    Spinner spnHCP;
    Spinner spnSpeciality;
    Button  btnSince;
    Button  btnUntil;
    private static String response;

    public static Integer ON_ACTIVITY_LOAD_CLINICS = -1;
    public static Integer ON_ACTIVITY_LOAD_SPECIALITIES = -2;
    public static Integer ON_ACTIVITY_LOAD_HCPS = -3;

    public DatePickerDialog.OnDateSetListener mDateSinceSetListener;
    public DatePickerDialog.OnDateSetListener mDateUntilSetListener;

    public static Date date = new Date();

    Spinner spnAppointments;


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

        initListener();



    }

    private void initListener() {
        mDateSinceSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month = month +1;
                if( isValidDate(year,month) )
                    Toast.makeText(SearchTurnActivity.this, "Error en fecha...", Toast.LENGTH_SHORT).show();
                else
                    btnSince.setText(year + validateDate(month) + validateDate(dayOfMonth));
            }
        };

        mDateUntilSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
                if( isValidDate(year,month) )
                    Toast.makeText(SearchTurnActivity.this, "Error en fecha...", Toast.LENGTH_SHORT).show();
                else
                    btnUntil.setText(year + validateDate(month) + validateDate(dayOfMonth));

            }
        };
    }

    private boolean isValidDate(int year, int month) {
       return  ((year%2000) < (date.getYear()%100) /*|| month < (date.getMonth()+1)*/);
    }

    private String validateDate(int mDate ){
        if(mDate < 10)
            return  "-0" + mDate;
        return  "-" + mDate;
    };

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
        btnSince.setOnClickListener(this);
        btnUntil.setOnClickListener(this);
        findViewById(R.id.btnSearchTurn).setOnClickListener(this);
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
        handlers().put(R.id.btnSearchTurn,new SearchAvailableAppointmentsHandler());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSearchTurnDateSince:
                chooseDate(mDateSinceSetListener);
                break;
            case R.id.btnSearchTurnDateUntil:
                chooseDate(mDateUntilSetListener);
                break;
            case R.id.btnSearchTurn:
                Toast.makeText(this, "Buscando turnos...", Toast.LENGTH_SHORT).show();
                ServerRequestSearchAvailableAppointments searchAvailableAppointments = new ServerRequestSearchAvailableAppointments(R.id.btnSearchTurn,parameters());
                searchAvailableAppointments.apiToken( apitoken() );
                connector().execute(searchAvailableAppointments,this);

        }
    }



    private String parameters() {

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("clinic_id",spnClinic.getSelectedItemId()+1);
            jsonObject.put("speciality_id",spnSpeciality.getSelectedItemId()+1);
            jsonObject.put("hcp_id",spnClinic.getSelectedItemId()+1);
            jsonObject.put("date_from",btnSince.getText());
            jsonObject.put("date_to",btnUntil.getText());

            return jsonObject.toString();


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void chooseDate(DatePickerDialog.OnDateSetListener mDateSetListener) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month= cal.get(Calendar.MONTH);
        int date = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year,month,date);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }


    @Override
    protected void loadNextActivityHandler() {

    }

    public void loadAppointment(String response2) throws JSONException{
        response = response2;
    }

}
