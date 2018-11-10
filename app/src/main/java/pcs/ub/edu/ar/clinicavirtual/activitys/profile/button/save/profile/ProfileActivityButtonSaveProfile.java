package pcs.ub.edu.ar.clinicavirtual.activitys.profile.button.save.profile;

import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.activitys.profile.ButtonHandler;
import pcs.ub.edu.ar.clinicavirtual.enums.USER_TYPE;

public class ProfileActivityButtonSaveProfile extends ButtonHandler {
    @Override
    protected void loadButtonHandler() {
        butttonHandler().put(USER_TYPE.PATIENT.getValue(),new ProfileActivityButtonSavePatientProfile());
        butttonHandler().put(USER_TYPE.HCP.getValue(),new ProfileActivityButtonSaveHCPProfile());
        butttonHandler().put(USER_TYPE.CLINIC.getValue(),new ProfileActivityButtonSaveClinicProfile());
    }

}
