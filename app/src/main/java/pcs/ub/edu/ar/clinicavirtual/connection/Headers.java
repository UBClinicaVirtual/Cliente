package pcs.ub.edu.ar.clinicavirtual.connection;

import java.util.ArrayList;

import pcs.ub.edu.ar.clinicavirtual.enums.HEADER;

public class Headers {


    public static ArrayList<HEADER> getTwoHeaders(){

        ArrayList<HEADER> mHeaders = new ArrayList<>();
        mHeaders.add(HEADER.ACCEPT);
        mHeaders.add(HEADER.CONTENT_TYPE);

        return mHeaders;
    }

    public  static ArrayList<HEADER> getThreeHeaders(){

        ArrayList<HEADER> mHeaders = getTwoHeaders();
        mHeaders.add(HEADER.AUTHORIZATION);

        return mHeaders;
    }

}
