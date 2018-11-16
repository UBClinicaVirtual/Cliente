package pcs.ub.edu.ar.clinicavirtual.handler;

import android.content.Intent;
import android.os.Bundle;
import org.json.JSONException;

import java.io.Serializable;

import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.activitys.general.MyTurnsActivity;
import pcs.ub.edu.ar.clinicavirtual.activitys.general.SearchTurnActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.appointment.ServerRequestSearchAvailableAppointments;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.IServerRequest;
import pcs.ub.edu.ar.clinicavirtual.interfaces.handler.IServerResponseHandler;

public class SearchAvailableAppointmentsHandler implements IServerResponseHandler {
    @Override
    public void handle(IServerRequest request, BaseActivity activity) {

        ServerRequestSearchAvailableAppointments requestSeachAppointments = (ServerRequestSearchAvailableAppointments) request;
        String response = requestSeachAppointments.response();
        SearchTurnActivity searchTurnActivity = (SearchTurnActivity) activity;
        try {

            searchTurnActivity.loadAppointment(response);

            Intent mIntent = new Intent(searchTurnActivity, MyTurnsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("lista", (Serializable) response);
            mIntent.putExtras(bundle);
            searchTurnActivity.startActivity(mIntent);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
