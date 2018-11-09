package pcs.ub.edu.ar.clinicavirtual.handler;

import android.content.SharedPreferences;
import android.widget.Toast;

import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.user.ServerRequestUserAddPatientProfile;
import pcs.ub.edu.ar.clinicavirtual.interfaces.handler.IServerResponseHandler;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.IServerRequest;

public class AddPatientProfileHandler implements IServerResponseHandler {
    @Override
    public void handle(IServerRequest request, BaseActivity activity) {

        ServerRequestUserAddPatientProfile requestPatientProfile  = (ServerRequestUserAddPatientProfile) request;
        String response = requestPatientProfile.response();

        Toast.makeText(activity,response, Toast.LENGTH_SHORT).show();


        Toast.makeText(activity, "Logeado como paciente", Toast.LENGTH_SHORT).show();
        /*try {

            JSONObject jsonObject = new JSONObject( response );


            Intent mIntent;


            mIntent = new Intent( activity , DataRegisterActivity.class);

            activity.startActivity(mIntent);
            activity.finish();

        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }

    private void saveApiToken(String apitoken, BaseActivity activity) {

        SharedPreferences file = activity.getPreferences(activity.MODE_PRIVATE);

        SharedPreferences.Editor editor = file.edit();

        editor.putString("api_token",apitoken);

        editor.apply();

    }

    private boolean isRegisteredUser(Integer mUserType) {
        return !mUserType.equals(0);
    }
}
