package pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests;

import android.view.View;

import java.util.ArrayList;

import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.uri.ServerRequestLogout;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.uri.ServerRequestRegister;
import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.HEADER;
import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.METHOD;
import pcs.ub.edu.ar.clinicavirtual.data.UserData;

public class ServerRequestLogoutUser extends ServerRequestLogout {

    public ServerRequestLogoutUser(Integer requesterId) {
        super(requesterId);
    }

    @Override
    public String parameters() {
        //Creo el JSON en demanda para el requerimiento
        return "";
    }

    @Override
    public String path() {
        return super.path();
    }

    @Override
    public String method() {
        return "GET";
    }

    //No todos los requerimientos dentro del paquete user van a devolver UserData
    //Eso depende de la especificacion del readme
    //Ver en el readme en cuales es necesario hacerlo
    public UserData getUserData() {
        //Deberia crear un UserData basado en el JSON que tengo como response del requerimiento
        return UserData.createFromJSON( this.response() );
    }

    /*public ServerRequestLogoutUser(View requesterId) {
        super(requesterId);
    }

    @Override
    public String parameters() {
        return null;
    }

    @Override
    public String method() {
        return METHOD.POST.getKey();
    }

    @Override
    public String path() {
        return super.path();
    }*/
}
