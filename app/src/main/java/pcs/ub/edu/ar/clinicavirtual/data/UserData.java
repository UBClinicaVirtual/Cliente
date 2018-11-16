package pcs.ub.edu.ar.clinicavirtual.data;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pcs.ub.edu.ar.clinicavirtual.data.exception.UserData.UserDataIdIsZeroException;
import pcs.ub.edu.ar.clinicavirtual.data.exception.UserData.UserDataInvalidEmailException;
import pcs.ub.edu.ar.clinicavirtual.data.exception.UserData.UserDataNullEmailException;
import pcs.ub.edu.ar.clinicavirtual.data.exception.UserData.UserDataNullIdException;
import pcs.ub.edu.ar.clinicavirtual.data.exception.UserData.UserDataNullNameException;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.IUserProfileData;

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
    private String 	mFirstName;
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
        if (mID == null)
            throw new UserDataNullIdException();
        if (mID == 0)
            throw new UserDataIdIsZeroException();
        this.mID = mID;
    }
    @Override
    public String getmFirstName() {
        return mFirstName;
    }

    private void setmName(String mFirstName) {
        if ((mFirstName == null) || (mFirstName.trim().isEmpty()))
            throw new UserDataNullNameException();
        this.mFirstName = mFirstName;
    }
    @Override
    public String getmEmail() {
        return mEmail;
    }

    private void setmEmail(String mEmail) {
        if((mEmail == null) || (mEmail.trim().isEmpty()))
            throw new UserDataNullEmailException();
        if(!validarmail(mEmail))
            throw new UserDataInvalidEmailException();

        this.mEmail = mEmail;
    }

    private boolean validarmail(String mEmail) {

        //Pattern to validate the email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(mEmail);

        if (mather.find() == true){
            return true;
        }else{
            return false;
        }
    }
*/
}
