package pcs.ub.edu.ar.clinicavirtual.handler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.activitys.general.SearchTurnActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.hcp.ServerRequestSearchHCP;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.IServerRequest;
import pcs.ub.edu.ar.clinicavirtual.interfaces.handler.IServerResponseHandler;

public class SearchHCPsHandler implements IServerResponseHandler {
    @Override
    public void handle(IServerRequest request, BaseActivity activity) {
        ServerRequestSearchHCP requestSearchHCP = (ServerRequestSearchHCP) request;
        SearchTurnActivity searchTurnActivity = (SearchTurnActivity) activity;

        try {
            JSONObject jsonObject = new JSONObject(requestSearchHCP.response());
            JSONArray jsonArray = jsonObject.getJSONArray("hcps");

            ArrayList<String> hcps = new ArrayList<>();

            for(int i=0;i<jsonArray.length();i++){
                jsonObject = jsonArray.getJSONObject(i);
                hcps.add(jsonObject.getString("last_name") + " " +
                            jsonObject.getString("first_name"));
            }

            searchTurnActivity.initHCPSpinner(hcps);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
