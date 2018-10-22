package pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.uri;

import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.ServerRequest;
import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.URI;

public abstract class ServerRequestPatient extends ServerRequest {
    @Override
    public String parameters() {
        return null;
    }

    @Override
    public String path() {
        return super.path() + URI.PATIENT;
    }
}
