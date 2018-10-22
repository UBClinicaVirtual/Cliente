package pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests;

import java.util.ArrayList;

import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.uri.ServerRequestUser;
import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.HEADER;
import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.METHOD;
import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.URI;

public class ServerRequestAddHCPandSpecialityToClinicSchedule extends ServerRequestUser {

    @Override
    public String path(){
        return super.path() + URI.CLINIC;
    }

    @Override
    public String parameters() {
        return null;
    }

    @Override
    public String method() {
        return METHOD.POST.getKey();
    }

}
