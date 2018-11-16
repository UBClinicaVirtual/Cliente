package pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.speciality;

import pcs.ub.edu.ar.clinicavirtual.enums.METHOD;
import pcs.ub.edu.ar.clinicavirtual.enums.URI;


public class ServerRequestSearchSpecialities extends ServerRequestSpeciality {

    public ServerRequestSearchSpecialities(Integer requesterId) {
        super(requesterId);
    }

    @Override
    public String parameters() { return "";}

    @Override
    public String path() {
        return ( super.path());
    }

    @Override
    public String method() {
        return METHOD.POST.getKey();
    }
}
