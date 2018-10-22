package pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection;

import java.util.ArrayList;
import java.util.List;

import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.HEADER;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces.IResponseListener;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces.IServerRequest;

public  abstract class ServerRequest implements IServerRequest, IResponseListener {

    private String mHeaderURL = "http://www.ubclinicavirtual.tk/api/v1";
    private ArrayList<HEADER> mHeaders = new ArrayList<>();
    private String mResponse;

    public ServerRequest(){
        mHeaders.add(HEADER.ACCEPT);
        mHeaders.add(HEADER.CONTENT_TYPE);
        mHeaders.add(HEADER.AUTHORIZATION);
    }

    @Override
    public void setResponse( String response ){
        mResponse = response;
    }

    @Override
    public String getResponse(){
        return mResponse;
    }

    @Override
    public String path() {
        return mHeaderURL;
    }

    @Override
    public ArrayList<HEADER> headers() {
        return mHeaders;
    }
}
