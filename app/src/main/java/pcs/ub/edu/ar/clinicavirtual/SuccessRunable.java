package pcs.ub.edu.ar.clinicavirtual;

import pcs.ub.edu.ar.clinicavirtual.activitys.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IServerResponseListener;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces.IResponseListener;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces.IServerRequest;

public class SuccessRunable implements Runnable {

    private IServerRequest request;
    private BaseActivity listener;


    public SuccessRunable(IServerRequest request, BaseActivity listener){
        this.request  = request;
        this.listener = listener;
    }

    @Override
    public void run() {

        listener.getHandlers().get(request.requesterId()).handle(request,listener);

    }
}
