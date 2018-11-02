package pcs.ub.edu.ar.clinicavirtual.activitys;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import pcs.ub.edu.ar.clinicavirtual.connection.ServerConnector;
import pcs.ub.edu.ar.clinicavirtual.connection.ServerConnectorInternet;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IServerConnector;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IServerResponseListener;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces.IServerRequest;

public abstract class BaseActivity extends AppCompatActivity implements IServerResponseListener, View.OnClickListener {

    private IServerConnector connector;

    public BaseActivity()
    {
        // Creo un conector con una lista de respuestas que son emuladas
        // Esto deberia ser un singleton para no tener que salir a buscar el api token cada vez que hay un request
        // (Pero puede quedar para otro momento)
        connector( new ServerConnectorInternet( "https://ubclinicavirtual.000webhostapp.com" ) );
        //https://ubclinicavirtual.000webhostapp.com
        //http://www.ubclinicavirtual.tk
    }


    @Override
    public void error(IServerRequest request) {
        //ALGO SALIO MAL CON EL REQUEST
    }

    protected IServerConnector connector() {
        return connector;
    }

    private void connector(IServerConnector connector) {
        this.connector = connector;
    }

    /*public IServerConnector getServerConnector() {
        return ServerConnector.getConnector();
    }*/

}
