package pcs.ub.edu.ar.clinicavirtual.enums;

import java.lang.reflect.Method;

public enum METHOD {
    GET ("GET"),
    POST("POST");

    String mKey;

    private METHOD (String key){
        this.mKey = key;
    }

    public String getKey() {
        return mKey;
    }
}