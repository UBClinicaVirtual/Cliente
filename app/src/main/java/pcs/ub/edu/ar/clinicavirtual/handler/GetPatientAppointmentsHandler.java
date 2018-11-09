package pcs.ub.edu.ar.clinicavirtual.handler;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.user.ServerRequestUserGetPatientAppointments;
import pcs.ub.edu.ar.clinicavirtual.interfaces.handler.IServerResponseHandler;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.IServerRequest;

public class GetPatientAppointmentsHandler implements IServerResponseHandler {
    @Override
    public void handle(IServerRequest request, BaseActivity activity) {
        ServerRequestUserGetPatientAppointments requestPatientProfile  = (ServerRequestUserGetPatientAppointments) request;
        String response = requestPatientProfile.response();

        Toast.makeText(activity, response , Toast.LENGTH_SHORT).show();

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("appointments");
            jsonObject= jsonArray.getJSONObject(0);
            Toast.makeText(activity,jsonObject.toString() , Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
