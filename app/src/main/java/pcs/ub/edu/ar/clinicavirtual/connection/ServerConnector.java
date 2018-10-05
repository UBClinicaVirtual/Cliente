package pcs.ub.edu.ar.clinicavirtual.connection;

import pcs.ub.edu.ar.clinicavirtual.interfaces.IServerConnector;

public class ServerConnector {

    private static IServerConnector connector;


    public static IServerConnector getConnector() {
        if (connector == null) {
            //Se utilizara el conector desarrollo hasta tener el real
            return new ServerConnectorProxy();
        }
        return connector;
    }

}
