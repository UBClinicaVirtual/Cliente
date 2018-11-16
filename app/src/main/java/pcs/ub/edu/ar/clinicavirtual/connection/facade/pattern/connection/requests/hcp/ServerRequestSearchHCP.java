package pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.hcp;

import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.ServerRequestAuthenticated;
import pcs.ub.edu.ar.clinicavirtual.enums.METHOD;
import pcs.ub.edu.ar.clinicavirtual.enums.URI;

public class ServerRequestSearchHCP extends ServerRequestAuthenticated {
    public ServerRequestSearchHCP(Integer requesterId) {
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
        return super.path() + URI.HCP + URI.SEARCH;
    }
}
