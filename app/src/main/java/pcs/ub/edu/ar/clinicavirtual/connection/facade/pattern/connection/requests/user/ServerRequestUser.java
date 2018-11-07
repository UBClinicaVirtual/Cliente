package pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.user;

import android.view.View;

import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.ServerRequestAuthenticated;
import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.URI;

public abstract class ServerRequestUser extends ServerRequestAuthenticated {
    public ServerRequestUser(Integer requesterId) {
        super(requesterId);
    }
    @Override
    public String path() {
        return super.path() + URI.USER;
    }
}
