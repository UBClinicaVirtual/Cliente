package pcs.ub.edu.ar.clinicavirtual.handler;

import android.widget.Toast;

import org.json.JSONException;

import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.activitys.patient.ProfileActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.user.ServerRequestUserGetPatientAppointments;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.user.ServerRequestUserGetPatientProfile;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IServerResponseHandler;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces.IServerRequest;

public class GetPatientProfileHandler  implements IServerResponseHandler {
    @Override
    public void handle(IServerRequest request, BaseActivity activity) {
        ServerRequestUserGetPatientProfile requestPatientProfile  = (ServerRequestUserGetPatientProfile) request;
        String response = requestPatientProfile.response();

        ProfileActivity profileActivity = (ProfileActivity) activity;

        Toast.makeText(profileActivity, response, Toast.LENGTH_SHORT).show();

        try {
            profileActivity.loadProfile(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
