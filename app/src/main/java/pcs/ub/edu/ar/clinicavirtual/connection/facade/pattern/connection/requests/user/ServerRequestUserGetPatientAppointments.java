package pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.user;


import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.METHOD;
import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.URI;

public class ServerRequestUserGetPatientAppointments extends ServerRequestUser {
    public ServerRequestUserGetPatientAppointments(Integer requesterId) {
        super(requesterId);
    }

    @Override
    public String parameters() {
        return "";
    }

    @Override
    public String path() {
        return ( super.path() + URI.PATIENT + URI.APPOINTMENTS );
    }

    @Override
    public String method() {
        return METHOD.POST.getKey();
    }
}
