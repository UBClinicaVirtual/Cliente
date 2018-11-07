package pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection;

import android.content.SharedPreferences;
import android.view.View;

import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.HEADER;

public abstract class ServerRequestAuthenticated extends ServerRequest {
    public ServerRequestAuthenticated(Integer requesterId) {
        super(requesterId);
    }

    public void apiToken( String apiToken ){
        super.headers().put(HEADER.AUTHORIZATION, "Bearer " + apiToken );
    }

   /*public String apitoken(){
        SharedPreferences sharedPreferences = this.getPreferences(this.MODE_PRIVATE);
        String apitoken = sharedPreferences.getString("api_token","no existe el contenido");
        return apitoken;
    }*/
}
