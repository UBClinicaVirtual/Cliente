package pcs.ub.edu.ar.clinicavirtual.handler;

import android.content.Intent;
import android.widget.Toast;

import pcs.ub.edu.ar.clinicavirtual.activitys.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.activitys.DataRegisterActivity;
import pcs.ub.edu.ar.clinicavirtual.activitys.GoogleSignInActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.ServerRequestLoginUser;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IServerResponseHandler;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces.IServerRequest;

public class LoginResponseHandler implements IServerResponseHandler {
    @Override
    public void handle(IServerRequest request, BaseActivity activity) {
        ServerRequestLoginUser serverRequestLoginUser  = (ServerRequestLoginUser) request;
        String response = serverRequestLoginUser.getUserData();

        Toast.makeText(activity,response, Toast.LENGTH_SHORT).show();

        Intent mIntentDataRegister = new Intent( activity , DataRegisterActivity.class);
        activity.startActivity(mIntentDataRegister);
    }
}
