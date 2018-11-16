package pcs.ub.edu.ar.clinicavirtual.handler;

import android.widget.Toast;

import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.activitys.general.SearchTurnActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.appointment.ServerRequestSearchAvailableAppointments;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.IServerRequest;
import pcs.ub.edu.ar.clinicavirtual.interfaces.handler.IServerResponseHandler;

public class SearchAvailableAppointmentsHandler implements IServerResponseHandler {
    @Override
    public void handle(IServerRequest request, BaseActivity activity) {

        ServerRequestSearchAvailableAppointments requestSeachAppointments = (ServerRequestSearchAvailableAppointments) request;
        SearchTurnActivity searchTurnActivity = (SearchTurnActivity) activity;

        Toast.makeText(searchTurnActivity, requestSeachAppointments.response() , Toast.LENGTH_SHORT).show();
    }
}
