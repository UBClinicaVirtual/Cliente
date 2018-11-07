package pcs.ub.edu.ar.clinicavirtual.activitys.base;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import pcs.ub.edu.ar.clinicavirtual.ErrorRunable;
import pcs.ub.edu.ar.clinicavirtual.SuccessRunable;
import pcs.ub.edu.ar.clinicavirtual.connection.ServerConnector;
import pcs.ub.edu.ar.clinicavirtual.connection.ServerConnectorInternet;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IServerConnector;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IServerResponseHandler;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IServerResponseListener;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces.IServerRequest;

public abstract class BaseActivity extends AppCompatActivity implements IServerResponseListener, View.OnClickListener {

    private ServerConnector connector;
    private Map<Integer,IServerResponseHandler> mHandlers;
    private static String APITOKEN;

    public BaseActivity()
    {
        mHandlers = new HashMap<>();


        // Creo un conector con una lista de respuestas que son emuladas
        // Esto deberia ser un singleton para no tener que salir a buscar el api token cada vez que hay un request
        // (Pero puede quedar para otro momento)
        //connector( new ServerConnectorInternet( "https://ubclinicavirtual.000webhostapp.com" ) );
        //connector( new ServerConnectorInternet( "http://www.ubclinicavirtual.tk" ) );
        //https://ubclinicavirtual.000webhostapp.com
        //http://www.ubclinicavirtual.tk

        loadHandlers();
    }

    protected  void loadHandlers(){

    }

    public Map<Integer,IServerResponseHandler> getHandlers(){
        return mHandlers;
    }

    @Override
    public void error(IServerRequest request) {
        //ALGO SALIO MAL CON EL REQUEST
        runOnUiThread(new ErrorRunable(request,this));
    }

    protected ServerConnector connector() {
        return new ServerConnectorInternet( "http://www.ubclinicavirtual.tk" );
    }

    private void connector(ServerConnector connector) {
        this.connector = connector;
    }


    @Override
    public void success(IServerRequest request) {
        runOnUiThread(new SuccessRunable(request,this));
    }

    public String apitoken(){
        return APITOKEN;
    }

    public void apitoken(String apitoken){
        APITOKEN = apitoken;
    }
}
