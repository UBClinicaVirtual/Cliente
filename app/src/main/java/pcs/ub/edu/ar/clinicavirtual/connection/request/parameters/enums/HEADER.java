package pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums;


public enum HEADER {
    ACCEPT("Accept","application/json"),
    CONTENT_TYPE("Content-Type","application/json"),
    AUTHORIZATION("Authorization","Bearer AN_API_TOKEN")
    ;


    private String mKey;
    private String mValue;

    private HEADER (String key, String value){
        this.mKey = key;
        this.mValue = value;
    }

    public String getKey() {
        return mKey;
    }

    public String getValue() {
        return mValue;
    }
}