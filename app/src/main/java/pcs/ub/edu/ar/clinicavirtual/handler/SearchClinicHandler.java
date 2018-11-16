package pcs.ub.edu.ar.clinicavirtual.handler;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.activitys.general.SearchTurnActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.clinic.ServerRequestSearchClinic;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.IServerRequest;
import pcs.ub.edu.ar.clinicavirtual.interfaces.handler.IServerResponseHandler;

public class SearchClinicHandler implements IServerResponseHandler {
    @Override
    public void handle(IServerRequest request, BaseActivity activity) {
        ServerRequestSearchClinic requestSearchClinic = (ServerRequestSearchClinic) request;
        SearchTurnActivity searchTurnActivity = (SearchTurnActivity) activity;



        try {
            JSONObject jsonObject = new JSONObject(requestSearchClinic.response());
            JSONArray jsonArray = jsonObject.getJSONArray("clinics");

            ArrayList<String> clinics = new ArrayList<>();

            for(int i=0;i<jsonArray.length();i++){
                jsonObject = jsonArray.getJSONObject(i);
                clinics.add(jsonObject.getString("business_name"));
            }

            searchTurnActivity.initClinicSpinner(clinics);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
