package pcs.ub.edu.ar.clinicavirtual.interfaces.handler;

import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.IServerRequest;

public interface IServerResponseHandler {

    public void handle(IServerRequest request, BaseActivity activity);

}
