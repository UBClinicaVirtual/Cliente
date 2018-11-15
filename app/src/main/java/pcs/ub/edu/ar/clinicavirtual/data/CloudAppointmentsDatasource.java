package pcs.ub.edu.ar.clinicavirtual.data;

import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.google.api.client.util.Lists;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;

import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.user.ServerRequestUserGetPatientAppointments;
import pcs.ub.edu.ar.clinicavirtual.handler.GetPatientAppointmentsHandler;


public class CloudAppointmentsDatasource extends BaseActivity implements ICloudAppointmentsDataSource {

   // private static HashMap<String, Appointment> API_DATA = new LinkedHashMap<>();
    private static final long LATENCY = 2000;
    private static Integer ON_LOAD = -1;
    private static HashMap<String, Appointment> mAppointments = new LinkedHashMap<>();



    private static void addAppointment(String clinic, String date, String doctor, String status){
        Appointment newAppointment = new Appointment(clinic, date, doctor,status);
        //API_DATA.put(newAppointment.getID(),newAppointment);
        mAppointments.put(newAppointment.getID(),newAppointment);
    }

    @Override
    public void getAppointments(final AppointmentServiceCallback callback) {
        ServerRequestUserGetPatientAppointments getPatientAppointments = new ServerRequestUserGetPatientAppointments(ON_LOAD);
        getPatientAppointments.apiToken( apitoken() );
        connector().execute(getPatientAppointments,this);


        //addAppointment("Prueba","20180621","prueba doc","dsfnsondfso");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onLoaded(Lists.newArrayList(mAppointments.values()));
            }
            }, LATENCY);
    }


    @Override
    protected void loadNextActivityHandler() {

    }

    @Override
    protected void loadHandlers() {
        this.handlers().put(ON_LOAD, new GetPatientAppointmentsHandler());
    }

    @Override
    public void onClick(View v) {

    }

    public static void loadAppointment(String response) throws JSONException {

        mAppointments.clear();

            JSONObject json = new JSONObject(response);
            //json = json.getJSONObject("patient");

        //JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = json.getJSONArray("appointments");
            //json= jsonArray.getJSONObject(0);
        //HACER FOREACH A JSON ARRAY, AGREGAR
            //Toast.makeText(activity,jsonObject.toString() , Toast.LENGTH_SHORT).show();
        //    addAppointment("Prueba de LoadAppointment","20180621","prueba doc","dsfnsondfso");
        for(int i = 0; i<jsonArray.length(); i++){
        //for(int i = 0; i<5; i++){
            json = jsonArray.getJSONObject(i);
            addAppointment(json.getString("clinic_business_name"),json.getString("appointment_date"),json.getString("hcp_last_name"),json.getString("appointment_status_name"));
        }
            //txtName.setText(json.getString("first_name"));
            //txtSName.setText(json.getString("last_name"));
            //txtDNI.setText(json.getString("identification_number"));
            //txtBirthDate.setText(json.getString("birth_date"));
            //txtGender.setText(json.getString("gender_id"));
            //txtAddress.setText(json.getString("address"));
            //txtPhone.setText(json.getString("phone"));
    }
}
