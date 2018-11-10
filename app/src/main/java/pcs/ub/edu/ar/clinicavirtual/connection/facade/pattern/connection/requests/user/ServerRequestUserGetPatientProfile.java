package pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.user;

import pcs.ub.edu.ar.clinicavirtual.enums.METHOD;

public class ServerRequestUserGetPatientProfile extends ServerRequestUser {

    public ServerRequestUserGetPatientProfile(Integer requesterId) {
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
