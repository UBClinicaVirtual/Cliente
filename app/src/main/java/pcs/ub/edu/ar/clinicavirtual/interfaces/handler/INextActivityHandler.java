package pcs.ub.edu.ar.clinicavirtual.interfaces.handler;

import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;

public interface INextActivityHandler {
    void nextActivity(int userType, BaseActivity activity);
}
