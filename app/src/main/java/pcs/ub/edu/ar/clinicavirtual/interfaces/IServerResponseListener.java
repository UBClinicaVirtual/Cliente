package pcs.ub.edu.ar.clinicavirtual.interfaces;

import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.IServerRequest;

public interface IServerResponseListener {

    public void success( IServerRequest request );
    public void error( IServerRequest request );

}
