package pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.uri;

import android.view.View;

import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.ServerRequest;
import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.URI;

public abstract class ServerRequestLogin extends ServerRequest {
    public ServerRequestLogin(View requesterId) {
        super(requesterId);
    }

    @Override
    public String parameters() {
        return null;
    }

    @Override
    public String path() {
        return super.path() + "/" + URI.LOGIN;
    }
}
