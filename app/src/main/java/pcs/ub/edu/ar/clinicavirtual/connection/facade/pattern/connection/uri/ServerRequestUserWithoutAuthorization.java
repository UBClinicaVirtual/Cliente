package pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.uri;

import android.view.View;

import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.ServerRequest;
import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.URI;

public abstract class ServerRequestUserWithoutAuthorization extends ServerRequest {

    public ServerRequestUserWithoutAuthorization(Integer requesterId) {
        super(requesterId);
    }

    @Override
    public String path() {
        return super.path() + URI.USER;
    }

}
