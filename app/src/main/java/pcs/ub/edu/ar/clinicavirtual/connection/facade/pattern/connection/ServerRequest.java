package pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection;

import java.util.HashMap;
import java.util.Map;

import pcs.ub.edu.ar.clinicavirtual.enums.HEADER;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.IServerRequest;

public  abstract class ServerRequest implements IServerRequest {

    // Eso podria ser un JSONObject en vez de String
    // Depende de lo que le sea mas practico

    private String 	mResponse;
    private Integer		mRequesterId;
    private Map<HEADER, String> mHeaders;

    public ServerRequest(Integer requesterId) {
        this.requesterId( requesterId );
        this.initHeaders();
    }

    private void requesterId(Integer requesterId) {
        this.mRequesterId = requesterId;
    }

    public String response() {
        return mResponse;
    }

    public void response(String response) {
        this.mResponse = response;
    }

    @Override
    public Integer requesterId() {
        return this.mRequesterId;
    }
    @Override
    public String path() {
        return "/api/v1";
    }

    private void initHeaders() {
        this.headers( new HashMap<HEADER,String>() );
        this.headers().put(HEADER.ACCEPT, HEADER.ACCEPT.getValue());
        this.headers().put(HEADER.CONTENT_TYPE, HEADER.CONTENT_TYPE.getValue());
    }

    @Override
    public Map<HEADER, String>headers() {
        return mHeaders;
    }

    private void headers(Map<HEADER, String> headers) {
        this.mHeaders = headers;
    }


    /*private String mHeaderURL = "http://www.ubclinicavirtual.tk/api/v1";
    private ArrayList<HEADER> mHeaders = new ArrayList<>();

    private String mResponse;
    private JSONObject jsonObject;

    public ServerRequest(){
        mHeaders.add(HEADER.ACCEPT);
        mHeaders.add(HEADER.CONTENT_TYPE);
        mHeaders.add(HEADER.AUTHORIZATION);
    }

    public void setJsonObject( JSONObject jsonObject ){
        this.jsonObject = jsonObject;
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
    }*/
}
