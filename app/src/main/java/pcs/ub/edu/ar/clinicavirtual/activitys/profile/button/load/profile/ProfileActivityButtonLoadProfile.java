package pcs.ub.edu.ar.clinicavirtual.activitys.profile.button.load.profile;

import pcs.ub.edu.ar.clinicavirtual.activitys.profile.ButtonHandler;
import pcs.ub.edu.ar.clinicavirtual.enums.USER_TYPE;

public class ProfileActivityButtonLoadProfile extends ButtonHandler {

    @Override
    protected void loadButtonHandler() {
        butttonHandler().put(USER_TYPE.PATIENT.getValue(),new ProfileActivityButtonLoadPatientProfile());
        butttonHandler().put(USER_TYPE.HCP.getValue(),new ProfileActivityButtonLoadHCPProfile());
        butttonHandler().put(USER_TYPE.CLINIC.getValue(),new ProfileActivityButtonLoadClinicProfile());
    }

}
