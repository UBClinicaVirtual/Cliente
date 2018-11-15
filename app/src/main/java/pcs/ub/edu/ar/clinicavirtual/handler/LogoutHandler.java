package pcs.ub.edu.ar.clinicavirtual.handler;

import android.content.Intent;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.activitys.main.MainScreenActivity;
import pcs.ub.edu.ar.clinicavirtual.activitys.start.MainActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.ServerRequestLogout;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.IServerRequest;
import pcs.ub.edu.ar.clinicavirtual.interfaces.handler.IServerResponseHandler;

public class LogoutHandler implements IServerResponseHandler {
    @Override
    public void handle(IServerRequest request, BaseActivity activity) {

        ServerRequestLogout requestLogout = (ServerRequestLogout) request;
        MainScreenActivity mActivity = (MainScreenActivity) activity;


        String msg = requestLogout.response();

        try {

            JSONObject jsonObject = new JSONObject(msg);
            msg  = jsonObject.getString("msg");
            Toast.makeText(mActivity, msg , Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(mActivity, MainActivity.class);
            mActivity.startActivity(intent);
            mActivity.closeDrawer();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
