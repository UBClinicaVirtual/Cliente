package pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.user;

import android.view.View;

import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.ServerRequestAuthenticated;

public abstract class ServerRequestUser extends ServerRequestAuthenticated {
    public ServerRequestUser(View requesterId) {
        super(requesterId);
    }
    @Override
    public String path() {
        return super.path() + "/user";
    }
}
