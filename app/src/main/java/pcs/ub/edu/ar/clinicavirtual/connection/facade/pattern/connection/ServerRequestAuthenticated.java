package pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection;

import android.view.View;

import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.HEADER;

public abstract class ServerRequestAuthenticated extends ServerRequest {
    public ServerRequestAuthenticated(View requesterId) {
        super(requesterId);
    }

    public void apiToken( String apiToken ){
        super.headers().put(HEADER.AUTHORIZATION, "Bearer " + apiToken );
    }
}
