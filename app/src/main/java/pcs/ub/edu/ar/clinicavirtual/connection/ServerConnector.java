package pcs.ub.edu.ar.clinicavirtual.connection;

import pcs.ub.edu.ar.clinicavirtual.interfaces.IServerConnector;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces.IServerRequest;

public abstract class ServerConnector implements IServerConnector {


    private String urlBase;
    String 			apiToken;

    public ServerConnector(String urlBase ) {
        this.urlBase(urlBase);
        this.apiToken("");
    }

    public String urlBase() {
        return urlBase;
    }

    private void urlBase(String urlBase) {
        this.urlBase = urlBase;
    }

    public void apiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String apiToken() {
        return this.apiToken;
    }

    protected String urlRequest( IServerRequest request) {
        return this.urlBase() + request.path();
    }


   /* private static IServerConnector connector;


    public static IServerConnector getConnector() {
        if (connector == null) {
            //Se utilizara el conector desarrollo hasta tener el real
            return new ServerConnectorProxy();
        }
        return connector;
    }*/

}
