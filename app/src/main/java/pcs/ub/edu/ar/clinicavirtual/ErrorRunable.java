package pcs.ub.edu.ar.clinicavirtual;

import android.widget.Toast;

import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.ServerRequest;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.IServerRequest;

public class ErrorRunable implements Runnable {

    private ServerRequest request;
    private BaseActivity listener;

    public ErrorRunable(IServerRequest request, BaseActivity listener){
        this.request  = (ServerRequest)request;
        this.listener = listener;
    }

    @Override
    public void run() {

        Toast.makeText(listener, request.response(), Toast.LENGTH_SHORT).show();

    }
}
