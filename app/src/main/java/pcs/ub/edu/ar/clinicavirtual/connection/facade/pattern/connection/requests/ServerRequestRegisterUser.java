package pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests;

import java.util.ArrayList;
import java.util.List;

import pcs.ub.edu.ar.clinicavirtual.connection.Headers;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.uri.ServerRequestLogout;
import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.HEADER;
import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.METHOD;

public class ServerRequestRegisterUser extends ServerRequestLogout {
    @Override
    public String parameters() {
        return null;
    }

    @Override
    public String method() {
        return METHOD.POST.getKey();
    }

    @Override
    public ArrayList<HEADER> headers() {
        ArrayList <HEADER> mHeaders = new ArrayList<>();
        mHeaders.add(HEADER.ACCEPT);
        mHeaders.add(HEADER.CONTENT_TYPE);

        return mHeaders;
    }
    @Override
    public String path() {
        return super.path();
    }
}
