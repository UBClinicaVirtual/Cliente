package pcs.ub.edu.ar.clinicavirtual.handler;

import org.json.JSONException;

import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.activitys.general.MyTurnsActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.user.ServerRequestUserGetPatientAppointments;
import pcs.ub.edu.ar.clinicavirtual.interfaces.handler.IServerResponseHandler;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.IServerRequest;

public class GetPatientAppointmentsHandler implements IServerResponseHandler {


    @Override
    public void handle(IServerRequest request, BaseActivity activity) {
        ServerRequestUserGetPatientAppointments requestPatientProfile  = (ServerRequestUserGetPatientAppointments) request;
        String response = requestPatientProfile.response();
        MyTurnsActivity myTurnsActivity = (MyTurnsActivity)activity;
       // Toast.makeText(activity, response , Toast.LENGTH_SHORT).show();
   //     CloudAppointmentsDatasource cloudAppointmentsDatasource = (CloudAppointmentsDatasource) activity;

        try {
            //CloudAppointmentsDatasource.loadAppointment(response);
            myTurnsActivity.loadAppointment(response);
            //JSONObject jsonObject = new JSONObject(response);
            //JSONArray jsonArray = jsonObject.getJSONArray("appointments");
            //jsonObject= jsonArray.getJSONObject(0);
            //HACER FOREACH A JSON ARRAY, AGREGAR
            //Toast.makeText(activity,jsonObject.toString() , Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
