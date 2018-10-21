package pcs.ub.edu.ar.clinicavirtual.data;

import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IPatientProfileData;

public class PatientProfileData implements IPatientProfileData {

    private String mName;
    private String mSName;
    private String mEmail;
    private Long    mDNI;
    private Integer mID;

    public PatientProfileData(String mName,String mSName,String mEmail, Long mDNI) {
        this.mName = mName;
        this.mEmail= mEmail;
        this.mDNI  = mDNI;
    }


    @Override
    public Integer getmID() {
        return mID;
    }

    @Override
    public String getmName() {
        return mName;
    }

    @Override
    public String getmEmail() {
        return mEmail;
    }

    @Override
    public Long getDNI() {
        return mDNI;
    }
}
