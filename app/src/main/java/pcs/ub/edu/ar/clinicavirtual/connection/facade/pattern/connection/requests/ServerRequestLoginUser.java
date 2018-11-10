package pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests;

import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.uri.ServerRequestLogin;
import pcs.ub.edu.ar.clinicavirtual.enums.METHOD;

public class ServerRequestLoginUser extends ServerRequestLogin {


    private String gmailToken;

    public ServerRequestLoginUser(Integer requesterId, String gmailToken) {
        super(requesterId);
        this.gmailToken(gmailToken);
    }


    @Override
    public String parameters() {
        // Creo el JSON en demanda para el requerimiento
        // Esto se puede hacer de la forma que mas les convenga
        // Puede venir como un parametro jsonObject en el constructor del request
        // y evitar tener que sobrecargar el constructor de cada uno
        // pudiendolo dejar en la clase abstracta ServerRequest
        // quedando en esta clase solo el metodo path, method y getUserData

       // return String.format("{\"access_token:\":\"%s\"}", gmailToken());

        return "{\"access_token\": \"" + gmailToken() + "\"}";
    }

    @Override
    public String path() {
        return super.path();
    }

    @Override
    public String method() {
        return METHOD.POST.getKey();
    }

    public String getUserData() {
        // Deberia crear un UserData basado en el JSON que tengo como response del requerimiento
        //return UserData.createFromJSON( this.response() );
        return this.response();
    }

    private String gmailToken() {
        return gmailToken;
    }

    private void gmailToken(String gmailToken) {
        this.gmailToken = gmailToken;
    }

    /*public ServerRequestLoginUser(String idToken) throws JSONException {
        super.setJsonObject( new JSONObject(idToken) );
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
    public ArrayList<HEADER> headers() {
        ArrayList <HEADER> mHeaders = new ArrayList<>();
        mHeaders.add(HEADER.ACCEPT);
        mHeaders.add(HEADER.CONTENT_TYPE);

        return mHeaders;
    }
    @Override
    public String path() {
        return super.path();
    }

    public IUserProfileData getProfile() throws JSONException {
        JsonFactory jsonFactory = new JsonFactory();

        return  jsonFactory.jsonToObject().getUserProfileData( new JSONObject(getResponse()));
    }*/
}
