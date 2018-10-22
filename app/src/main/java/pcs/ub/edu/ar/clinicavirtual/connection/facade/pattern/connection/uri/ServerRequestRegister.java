package pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.uri;

import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.ServerRequest;
import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.URI;

public abstract class ServerRequestRegister extends ServerRequest {


    @Override
    public String path() {
        return super.path() + URI.REGISTER;
    }
}
