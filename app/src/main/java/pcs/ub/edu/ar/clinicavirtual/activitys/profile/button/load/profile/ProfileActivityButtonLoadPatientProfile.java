package pcs.ub.edu.ar.clinicavirtual.activitys.profile.button.load.profile;

import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.user.ServerRequestUserGetPatientProfile;
import pcs.ub.edu.ar.clinicavirtual.interfaces.handler.INextActivityHandler;

public class ProfileActivityButtonLoadPatientProfile implements INextActivityHandler {
    @Override
    public void nextActivity(int id, BaseActivity activity) {
        ServerRequestUserGetPatientProfile getPatientProfile = new ServerRequestUserGetPatientProfile(R.id.btnPatientProfileLoad);
        getPatientProfile.apiToken( activity.apitoken() );
        activity.connector().execute(getPatientProfile,activity);
    }
}
