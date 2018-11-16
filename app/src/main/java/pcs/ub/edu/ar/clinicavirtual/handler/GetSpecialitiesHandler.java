package pcs.ub.edu.ar.clinicavirtual.handler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.activitys.register.DataRegisterActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.speciality.ServerRequestSearchSpecialities;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.IServerRequest;
import pcs.ub.edu.ar.clinicavirtual.interfaces.handler.IServerResponseHandler;

public class GetSpecialitiesHandler implements IServerResponseHandler {
    @Override
    public void handle(IServerRequest request, BaseActivity activity) {
        ServerRequestSearchSpecialities requestSpecialities = (ServerRequestSearchSpecialities) request;
        String response = requestSpecialities.response();

        DataRegisterActivity drActivity = (DataRegisterActivity) activity;

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("specialities");


            ArrayList<String> mSpecialities = new ArrayList<>();

            for(Integer i = 0; i<jsonArray.length();i++)
                mSpecialities.add(jsonArray.getJSONObject(i).getString("name"));

            drActivity.specialities(mSpecialities);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
