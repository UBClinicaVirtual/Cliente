package pcs.ub.edu.ar.clinicavirtual.handler;

import android.content.Intent;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import pcs.ub.edu.ar.clinicavirtual.activitys.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.activitys.DataRegisterActivity;
import pcs.ub.edu.ar.clinicavirtual.activitys.MainScreenActivity;
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

            Integer mUserType = jsonObject.getJSONObject("user").getInt("user_type_id");

            Intent mIntent;

            if ( isRegisteredUser( mUserType ) )
                mIntent = new Intent( activity , MainScreenActivity.class);
            else
                mIntent = new Intent( activity , DataRegisterActivity.class);


            activity.startActivity(mIntent);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private boolean isRegisteredUser(Integer mUserType) {
        return !mUserType.equals(0);
    }
}
