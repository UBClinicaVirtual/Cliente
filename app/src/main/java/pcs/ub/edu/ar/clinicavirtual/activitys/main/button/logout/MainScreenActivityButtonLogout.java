package pcs.ub.edu.ar.clinicavirtual.activitys.main.button.logout;

import pcs.ub.edu.ar.clinicavirtual.activitys.profile.ButtonHandler;
import pcs.ub.edu.ar.clinicavirtual.enums.USER_TYPE;

public class MainScreenActivityButtonLogout extends ButtonHandler {
    @Override
    protected void loadButtonHandler() {
        butttonHandler().put(USER_TYPE.PATIENT.getValue(),new MainScreenActivityButtonUserLogout());
        butttonHandler().put(USER_TYPE.HCP.getValue(),new MainScreenActivityButtonUserLogout());
        butttonHandler().put(USER_TYPE.CLINIC.getValue(),new MainScreenActivityButtonUserLogout());
    }
}
