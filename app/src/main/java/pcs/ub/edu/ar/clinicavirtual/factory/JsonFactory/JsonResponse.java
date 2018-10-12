//ESTA CLASE TENDRIA TODOS LOS TIPOS DE RESPUESTA QUE PUEDE DEVOLVER EL SERVIDOR COMO JSON

package pcs.ub.edu.ar.clinicavirtual.factory.JsonFactory;

import org.json.JSONException;
import org.json.JSONObject;

import pcs.ub.edu.ar.clinicavirtual.data.PatientProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.*;

public class JsonResponse{

    public static JSONObject register(String tokenGmail) {
        return null;
    }


    public static JSONObject login(String tokenGmail) {
        return null;
    }


    public static JSONObject addPatientProfileToUserAccount() {

        String stringJson =  "{\n" +
                "    \"data\": {\n" +
                "        \"id\": 6,\n" +
                "        \"name\": \"walter ub\",\n" +
                "        \"email\": \"ubelarga@gmail.com\",\n" +
                "        \"email_verified_at\": null,\n" +
                "        \"created_at\": \"2018-09-20 17:30:52\",\n" +
                "        \"updated_at\": \"2018-09-27 15:06:20\",\n" +
                "        \"api_token\": \"HoFcCOSgtjyfD5GELBuyQ3xHZasPiBSyqGsFeDsYLXj4BewYcIEOvyqAn0iZ\",\n" +
                "        \"active\": 1\n" +
                "    }\n" +
                "}";

        try {
            return new JSONObject(stringJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static JSONObject addClinicProfileToUserAccount() {
        return null;
    }


    public static JSONObject addHCPProfileToUserAccount() {
        return null;
    }


    public static JSONObject getUserPatientProfile() {
        return null;
    }
}
