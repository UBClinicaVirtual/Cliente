package pcs.ub.edu.ar.clinicavirtual.handler;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.activitys.register.config.DataRegisterActivity;
import pcs.ub.edu.ar.clinicavirtual.activitys.patient.MainScreenActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.ServerRequestLoginUser;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IServerResponseHandler;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces.IServerRequest;

public class LoginResponseHandler implements IServerResponseHandler {
    @Override
    public void handle(IServerRequest request, BaseActivity activity) {
        ServerRequestLoginUser serverRequestLoginUser  = (ServerRequestLoginUser) request;
        String response = serverRequestLoginUser.getUserData();

        Toast.makeText(activity,response, Toast.LENGTH_SHORT).show();

        try {

            JSONObject jsonObject = new JSONObject(((ServerRequestLoginUser) request).response());



            jsonObject = jsonObject.getJSONObject("user");

            saveApiToken( jsonObject.getString("api_token") , activity);

            Integer mUserType = jsonObject.getInt("user_type_id");

            Intent mIntent;

            if ( isRegisteredUser( mUserType ) ){
                mIntent = new Intent( activity , MainScreenActivity.class);
            } else{
                mIntent = new Intent( activity , DataRegisterActivity.class);
                mIntent.putExtra("response",response);
            }



            activity.findViewById(R.id.progress).setVisibility(View.INVISIBLE);
            activity.startActivity(mIntent);
            activity.finish();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void saveApiToken(String apitoken, BaseActivity activity) {

        activity.apitoken(apitoken);

        SharedPreferences file = PreferenceManager.getDefaultSharedPreferences(activity);//activity.getPreferences(activity.MODE_PRIVATE);

        SharedPreferences.Editor editor = file.edit();

        editor.putString("api_token",apitoken);

        editor.apply();


    }

    private boolean isRegisteredUser(Integer mUserType) {
        return !mUserType.equals(0);
    }
}
