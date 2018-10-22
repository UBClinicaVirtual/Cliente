package pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests;

import java.util.ArrayList;

import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.uri.ServerRequestUser;
import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.HEADER;
import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.METHOD;

public class ServerRequestGetHCPAppointments extends ServerRequestUser {
    @Override
    public String parameters() {
        return null;
    }

    @Override
    public String method() {
        return METHOD.GET.getKey();
    }

    @Override
    public String path() {
        return super.path();
    }
}
