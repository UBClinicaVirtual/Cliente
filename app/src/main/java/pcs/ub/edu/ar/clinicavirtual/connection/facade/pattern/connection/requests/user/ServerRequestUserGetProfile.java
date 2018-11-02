package pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.user;

import android.view.View;

import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.METHOD;

public class ServerRequestUserGetProfile extends ServerRequestUser {
    public ServerRequestUserGetProfile(Integer requesterId) {
        super(requesterId);
    }

    @Override
    public String parameters() {
        return "";
    }

    @Override
    public String method() {
        return METHOD.GET.getKey();
    }

}
