package pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.clinic;

import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.ServerRequestAuthenticated;
import pcs.ub.edu.ar.clinicavirtual.enums.METHOD;
import pcs.ub.edu.ar.clinicavirtual.enums.URI;

public class ServerRequestSearchClinic extends ServerRequestAuthenticated {

    public ServerRequestSearchClinic(Integer requesterId) {
        super(requesterId);
    }

    @Override
    public String parameters() {
        return "";
    }

    @Override
    public String method() {
        return METHOD.POST.getKey();
    }

    @Override
    public String path(){
        return super.path() + URI.CLINIC + URI.SEARCH;
    }
}
