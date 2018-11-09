package pcs.ub.edu.ar.clinicavirtual.data;

public class UserData /*implements IUserProfileData*/ {

    private static final String CAMPO_API_TOKEN = "\"api_token\": \"";

    private String apiToken;

    // Esto podria recibir un JSONObject en vez de un String
    // depende de lo que les sea mas practico
    public static UserData createFromJSON(String response) {

        //Deberia crear un UserData basado en el JSON que me llega como parametro
        UserData usr = new UserData();

        //Busco en el response donde esta el api token y lo seteo
        //Esto deberia hacerse usando el JSONObject en Android y no buscando con el string
        //Algo asi como jsonObject.get("user").get("api_token")
        int startIndex = response.indexOf(CAMPO_API_TOKEN) + CAMPO_API_TOKEN.length();
        usr.apiToken( response.substring( startIndex , startIndex + 60 ) );

        return usr;
    }

    @Override
    public String toString() {
        return "Aca deberia estar formateada la infromacion del usuario logeado en la estructura";
    }

    public String apiToken() {
        return this.apiToken;
    }

    private void apiToken(String apiToken) {
        this.apiToken = apiToken;
    }

  /*  private Integer mID;
    private String 	mName;
    private String 	mEmail;

    public UserData(Integer id, String name, String email) {
            setmEmail(email);
            setmID(id);
            setmName(name);
    }
    @Override
    public Integer getmID() {
        return mID;
    }

    private void setmID(Integer mID) {
        this.mID = mID;
    }
    @Override
    public String getmName() {
        return mName;
    }

    private void setmName(String mName) {
        this.mName = mName;
    }
    @Override
    public String getmEmail() {
        return mEmail;
    }

    private void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }
*/
}
