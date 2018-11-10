package pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.speciality;

import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.ServerRequestAuthenticated;
import pcs.ub.edu.ar.clinicavirtual.enums.URI;

public abstract class ServerRequestSpeciality extends ServerRequestAuthenticated {
    public ServerRequestSpeciality(Integer requesterId) {
        super(requesterId);
    }

    @Override
    public String path() {
        return super.path() + URI.SPECIALITY;
    }
}
