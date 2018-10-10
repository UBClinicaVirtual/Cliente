package pcs.ub.edu.ar.clinicavirtual.factory.JsonFactory;

import org.json.JSONException;
import org.json.JSONObject;

import pcs.ub.edu.ar.clinicavirtual.data.PatientProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.*;

public class JsonResponse{

    public static IUserProfileData register(String tokenGmail) {
        return null;
    }


    public static IUserProfileData login(String tokenGmail) {
        return null;
    }


    public static IPatientProfileData addPatientProfileToUserAccount(IPatientProfileData patientData) {

        JsonUserInfo jsonUserInfo = null;
        JSONObject jsonObject = null;
        IPatientProfileData iPatientProfileData;

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
            jsonObject = new JSONObject(stringJson);
            jsonUserInfo = new JsonUserInfo( jsonObject  );
            iPatientProfileData = new PatientProfileData(jsonUserInfo.getmName(),jsonUserInfo.getmName(),jsonUserInfo.getmEmail(),new Long(38579707));
            return iPatientProfileData;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new PatientProfileData("usrPrueba","espina" ,"prueba@mail.com",new Long(123));
    }


    public static IClinicProfileData addClinicProfileToUserAccount(IClinicProfileData clinicData) {
        return null;
    }


    public static IHCPProfileData addHCPProfileToUserAccount(IHCPProfileData hcpData) {
        return null;
    }


    public static IPatientProfileData getUserPatientProfile() {
        return null;
    }
}
