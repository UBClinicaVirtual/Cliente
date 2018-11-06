package pcs.ub.edu.ar.clinicavirtual.interfaces;

import pcs.ub.edu.ar.clinicavirtual.activitys.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces.IServerRequest;

public interface IServerResponseHandler {

    public void handle(IServerRequest request, BaseActivity activity);

}
