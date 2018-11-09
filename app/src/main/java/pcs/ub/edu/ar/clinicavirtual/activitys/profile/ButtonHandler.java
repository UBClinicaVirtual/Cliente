package pcs.ub.edu.ar.clinicavirtual.activitys.profile;

import java.util.HashMap;
import java.util.Map;

import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.interfaces.handler.INextActivityHandler;

public abstract class ButtonHandler implements pcs.ub.edu.ar.clinicavirtual.interfaces.handler.INextActivityHandler  {

    protected Map<Integer,INextActivityHandler> butttonHandler;

    public ButtonHandler(){
        butttonHandler = new HashMap<>();
        loadButtonHandler();
    }

    protected abstract void loadButtonHandler();

    protected Map<Integer, INextActivityHandler> butttonHandler() {
        return butttonHandler;
    }

    @Override
    public void nextActivity(int userType, BaseActivity activity) {
        butttonHandler.get(userType).nextActivity(userType,activity);
    }

}
