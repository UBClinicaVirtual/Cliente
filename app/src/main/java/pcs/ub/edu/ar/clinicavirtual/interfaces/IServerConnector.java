package pcs.ub.edu.ar.clinicavirtual.interfaces;

import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.ServerRequest;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.ServerRequestAuthenticated;

public interface IServerConnector{
    public void call(ServerRequestAuthenticated request, IServerResponseListener listener );
    public void call(ServerRequest request, IServerResponseListener listener );
    public void apiToken(String apiToken);
}
