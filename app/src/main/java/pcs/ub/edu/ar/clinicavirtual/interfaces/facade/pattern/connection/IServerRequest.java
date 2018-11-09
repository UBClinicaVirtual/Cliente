package pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection;

import java.util.Map;

import pcs.ub.edu.ar.clinicavirtual.enums.HEADER;

public interface IServerRequest {

    public String parameters();

    public String path();

    public String method();

    public Map<HEADER, String> headers();

    //Deberia ser un int
    public Integer requesterId();

}
