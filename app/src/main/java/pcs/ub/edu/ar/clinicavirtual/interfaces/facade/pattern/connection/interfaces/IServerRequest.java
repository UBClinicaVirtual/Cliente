package pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces;

import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.HEADER;
import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.METHOD;

public interface IServerRequest {

    public String parameters();

    public String path();

    public String method();

    public Map<HEADER, String> headers();

    //Deberia ser un int
    public Integer requesterId();

}
