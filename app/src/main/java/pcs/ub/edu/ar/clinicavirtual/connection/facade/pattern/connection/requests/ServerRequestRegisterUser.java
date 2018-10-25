package pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests;

import android.view.View;

import java.util.ArrayList;
import java.util.Map;

import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.uri.ServerRequestLogout;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.uri.ServerRequestRegister;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.uri.ServerRequestUser;
import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.HEADER;
import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.METHOD;
import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.URI;
import pcs.ub.edu.ar.clinicavirtual.data.UserData;

public class ServerRequestRegisterUser extends ServerRequestRegister {
    private String gmailToken;

    public ServerRequestRegisterUser(View requesterId, String gmailToken) {
        super(requesterId);
        this.gmailToken(gmailToken);
    }

    @Override
    public String parameters() {
        //Creo el JSON en demanda para el requerimiento
        return String.format("{\"access_token:\":\"%s\"}", gmailToken());
    }

    @Override
    public String path() {
        return super.path();
    }

    @Override
    public String method() {
        return METHOD.POST.getKey();
    }

    //No todos los requerimientos dentro del paquete user van a devolver UserData
    //Eso depende de la especificacion del readme
    //Ver en el readme en cuales es necesario hacerlo

    public UserData getUserData() {
        //Deberia crear un UserData basado en el JSON que tengo como response del requerimiento
        return UserData.createFromJSON( this.response() );
    }

    private String gmailToken() {
        return gmailToken;
    }

    private void gmailToken(String gmailToken) {
        this.gmailToken = gmailToken;
    }
}
