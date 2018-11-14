package pcs.ub.edu.ar.clinicavirtual.data;

import android.os.Handler;

import com.google.api.client.util.Lists;

import java.util.HashMap;
import java.util.LinkedHashMap;


public class CloudAppointmentsDatasource implements ICloudAppointmentsDataSource {

    private static HashMap<String, Appointment> API_DATA;
    private static final long LATENCY = 2000;

    static {
        API_DATA = new LinkedHashMap<>();
        for (int i = 0; i<5; i++){
            addAppointment("Hospital de clinicas" , "14/11/2018 12:27 pm", "Dr. Duartes Walter", "Pendiente");
        }
    }

    private static void addAppointment(String clinic, String date, String doctor, String status){
        Appointment newAppointment = new Appointment(clinic, date, doctor,status);
        API_DATA.put(newAppointment.getID(),newAppointment);
    }

    @Override
    public void getAppointments(final AppointmentServiceCallback callback) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onLoaded(Lists.newArrayList(API_DATA.values()));
            }
            }, LATENCY);
    }


}
