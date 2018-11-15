package pcs.ub.edu.ar.clinicavirtual.activitys.main.button.logout;

import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.user.ServerRequestUserGetPatientAppointments;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.uri.ServerRequestLogout;
import pcs.ub.edu.ar.clinicavirtual.interfaces.handler.INextActivityHandler;

public class MainScreenActivityButtonUserLogout implements INextActivityHandler {
    @Override
    public void nextActivity(int userType, BaseActivity activity) {
        ServerRequestLogout requestLogout = new ServerRequestLogout(R.id.btnPatientMyTurns);
        getPatientAppointments.apiToken( activity.apitoken() );
        activity.connector().execute(getPatientAppointments,this);
    }
}
