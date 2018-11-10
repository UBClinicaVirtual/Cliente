package pcs.ub.edu.ar.clinicavirtual.activitys.profile.button.save.profile;

import android.widget.Toast;

import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;

public class ProfileActivityButtonSavePatientProfile implements pcs.ub.edu.ar.clinicavirtual.interfaces.handler.INextActivityHandler {

    @Override
    public void nextActivity(int userType, BaseActivity activity) {
        Toast.makeText(activity, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
    }
}
