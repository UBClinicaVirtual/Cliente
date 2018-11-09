package pcs.ub.edu.ar.clinicavirtual;

import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.IServerRequest;

public class SuccessRunable implements Runnable {

    private IServerRequest request;
    private BaseActivity listener;


    public SuccessRunable(IServerRequest request, BaseActivity listener){
        this.request  = request;
        this.listener = listener;
    }

    @Override
    public void run() {
        listener.handlers().get(request.requesterId()).handle(request,listener);
    }
}
