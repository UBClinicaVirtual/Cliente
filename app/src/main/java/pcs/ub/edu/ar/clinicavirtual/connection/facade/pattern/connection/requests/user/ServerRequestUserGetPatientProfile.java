package pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.user;

import android.view.View;

import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.METHOD;

public class ServerRequestUserGetPatientProfile extends ServerRequestUser {

    public ServerRequestUserGetPatientProfile(View requesterId) {
        super(requesterId);
    }

    @Override
    public String parameters() {
        return "";
    }

    @Override
    public String path() {
        return super.path() + "/patient";
    }

    @Override
    public String method() {
        return METHOD.GET.getKey();
    }
}
