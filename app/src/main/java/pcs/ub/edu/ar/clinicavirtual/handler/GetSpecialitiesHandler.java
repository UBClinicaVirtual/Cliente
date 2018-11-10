package pcs.ub.edu.ar.clinicavirtual.handler;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.speciality.ServerRequestSpecialityGetSpecialities;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.IServerRequest;
import pcs.ub.edu.ar.clinicavirtual.interfaces.handler.IServerResponseHandler;

public class GetSpecialitiesHandler implements IServerResponseHandler {
    @Override
    public void handle(IServerRequest request, BaseActivity activity) {
        ServerRequestSpecialityGetSpecialities requestSpecialities = (ServerRequestSpecialityGetSpecialities) request;
        String response = requestSpecialities.response();

        Toast.makeText(activity, response , Toast.LENGTH_SHORT).show();

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("specialities");
            //jsonObject= jsonArray.getJSONObject(0);
            Toast.makeText(activity, jsonArray.toString() , Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
