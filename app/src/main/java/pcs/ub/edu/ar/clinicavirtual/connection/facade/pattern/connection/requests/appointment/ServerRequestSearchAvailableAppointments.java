package pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.appointment;

import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.ServerRequestAuthenticated;
import pcs.ub.edu.ar.clinicavirtual.enums.METHOD;
import pcs.ub.edu.ar.clinicavirtual.enums.URI;

public class ServerRequestSearchAvailableAppointments extends ServerRequestAuthenticated {

    private String mParameters;

    public ServerRequestSearchAvailableAppointments(Integer requesterId , String parameters) {
        super(requesterId);
        this.mParameters = parameters;
    }

    @Override
    public String parameters() {
        return mParameters;
    }

    @Override
    public String method() {
        return METHOD.POST.getKey();
    }

    @Override
    public String path(){
        return super.path() + URI.APPOINTMENT + URI.AVAILABLE;
    }
}
