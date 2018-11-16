package pcs.ub.edu.ar.clinicavirtual.handler;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.activitys.general.SearchTurnActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.speciality.ServerRequestSearchSpecialities;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.IServerRequest;
import pcs.ub.edu.ar.clinicavirtual.interfaces.handler.IServerResponseHandler;

public class SearchSpecialitiesHandler implements IServerResponseHandler {
    @Override
    public void handle(IServerRequest request, BaseActivity activity) {
        SearchTurnActivity turnActivity = (SearchTurnActivity) activity;
        ServerRequestSearchSpecialities searchSpecialities = (ServerRequestSearchSpecialities) request;


        try {
            JSONObject jsonObject = new JSONObject(searchSpecialities.response());
            JSONArray jsonArray = jsonObject.getJSONArray("specialities");

            ArrayList<String> specialities = new ArrayList<>();

            for(int i=0;i<jsonArray.length();i++){
                jsonObject = jsonArray.getJSONObject(i);
                specialities.add(jsonObject.getString("name"));
            }

            turnActivity.initSpecialitiesSpinner(specialities);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
