package pcs.ub.edu.ar.clinicavirtual.activitys.base;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import pcs.ub.edu.ar.clinicavirtual.ErrorRunable;
import pcs.ub.edu.ar.clinicavirtual.SuccessRunable;
import pcs.ub.edu.ar.clinicavirtual.connection.ServerConnector;
import pcs.ub.edu.ar.clinicavirtual.connection.ServerConnectorInternet;
import pcs.ub.edu.ar.clinicavirtual.interfaces.handler.IActivityVisibilityHandler;
import pcs.ub.edu.ar.clinicavirtual.interfaces.handler.INextActivityHandler;
import pcs.ub.edu.ar.clinicavirtual.interfaces.handler.IServerResponseHandler;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IServerResponseListener;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.IServerRequest;

public abstract class BaseActivity extends AppCompatActivity implements IServerResponseListener, View.OnClickListener {

    private ServerConnector connector;
    private Map<Integer,IServerResponseHandler> mHandlers;
    private Map<Integer,IActivityVisibilityHandler> mVisibilityHandlers;
    private Map<Integer,INextActivityHandler> mNextActivityHandler;
    private static String APITOKEN;


    public static JSONObject jsonUser;

    public BaseActivity()
    {
        mHandlers = new HashMap<>();
        mVisibilityHandlers = new HashMap<>();
        mNextActivityHandler = new HashMap<>();
        // Creo un conector con una lista de respuestas que son emuladas
        // Esto deberia ser un singleton para no tener que salir a buscar el api token cada vez que hay un request
        // (Pero puede quedar para otro momento)
        //connector( new ServerConnectorInternet( "https://ubclinicavirtual.000webhostapp.com" ) );
        //connector( new ServerConnectorInternet( "http://www.ubclinicavirtual.tk" ) );
        //https://ubclinicavirtual.000webhostapp.com
        //http://www.ubclinicavirtual.tk
        loadNextActivityHandler();
        loadHandlers();
        loadVisibilityHandlers();
    }

    protected abstract void loadNextActivityHandler();

    protected static Integer userTypeId() {
        try {
            return jsonUser.getInt("user_type_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    protected  void loadHandlers(){

    }

    protected  void loadVisibilityHandlers(){

    }

    public Map<Integer,IServerResponseHandler> handlers(){
        return mHandlers;
    }

    public Map<Integer,IActivityVisibilityHandler> visibilityHandlers(){
        return mVisibilityHandlers;
    }

    public Map<Integer,INextActivityHandler> nextActivityHandlers(){
        return mNextActivityHandler;
    }

    @Override
    public void error(IServerRequest request) {
        //ALGO SALIO MAL CON EL REQUEST
        runOnUiThread(new ErrorRunable(request,this));
    }

    public ServerConnector connector() {
        return new ServerConnectorInternet( "https://ubclinicavirtual.000webhostapp.com" );
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
