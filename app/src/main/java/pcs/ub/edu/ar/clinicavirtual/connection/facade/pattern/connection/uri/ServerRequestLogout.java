package pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.uri;

import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.ServerRequest;
import pcs.ub.edu.ar.clinicavirtual.enums.URI;

public abstract class ServerRequestLogout extends ServerRequest {
    public ServerRequestLogout(Integer requesterId) {
        super(requesterId);
    }

    @Override
    public String parameters() {
        return null;
    }

    @Override
    public String path() {
        return super.path() + "/" + URI.LOGOUT;
    }
}
