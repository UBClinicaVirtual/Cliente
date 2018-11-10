package pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.user;

import pcs.ub.edu.ar.clinicavirtual.enums.METHOD;
import pcs.ub.edu.ar.clinicavirtual.enums.URI;

public class ServerRequestUserAddPatientProfile extends ServerRequestUser {

    String mParameters;

    public ServerRequestUserAddPatientProfile(Integer requesterId, String mParameters) {
        super(requesterId);
        this.mParameters = mParameters;
    }


    @Override
    public String parameters() {
        return mParameters;
    }

    @Override
    public String method() {
        return METHOD.POST.getKey();
    }

    @Override
    public String path() {
        return ( super.path() + URI.PATIENT );
    }

    public String getPatientProfile() {
        return this.response();
    }
}
