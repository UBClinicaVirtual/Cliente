package pcs.ub.edu.ar.clinicavirtual.handler;

import android.widget.Toast;

import org.json.JSONException;

import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.activitys.profile.ProfileActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.user.ServerRequestUserGetPatientProfile;
import pcs.ub.edu.ar.clinicavirtual.interfaces.handler.IServerResponseHandler;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.IServerRequest;

public class GetPatientProfileHandler  implements IServerResponseHandler {
    @Override
    public void handle(IServerRequest request, BaseActivity activity) {
        ServerRequestUserGetPatientProfile requestPatientProfile  = (ServerRequestUserGetPatientProfile) request;
        String response = requestPatientProfile.response();

        ProfileActivity profileActivity = (ProfileActivity) activity;

        try {
            profileActivity.loadProfile(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
