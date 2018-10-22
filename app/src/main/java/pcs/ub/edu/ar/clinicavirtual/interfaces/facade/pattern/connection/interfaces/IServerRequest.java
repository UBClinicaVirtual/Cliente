package pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces;

import java.util.ArrayList;
import java.util.List;

import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.HEADER;

public interface IServerRequest {

    public String parameters();

    public String path();

    public String method();

    public ArrayList<HEADER> headers();

}
