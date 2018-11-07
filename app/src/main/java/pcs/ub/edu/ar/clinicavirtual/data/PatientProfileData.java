package pcs.ub.edu.ar.clinicavirtual.data;


import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IPatientProfileData;

import pcs.ub.edu.ar.clinicavirtual.data.exception.PatientProfileDataException.PatientProfileDataDniIsZeroException;
import pcs.ub.edu.ar.clinicavirtual.data.exception.PatientProfileDataException.PatientProfileDataNullDniException;
import pcs.ub.edu.ar.clinicavirtual.data.exception.PatientProfileDataException.PatientProfileDataNullNameException;
import pcs.ub.edu.ar.clinicavirtual.data.exception.PatientProfileDataException.PatientProfileDataNullSurnameException;



public class PatientProfileData implements IPatientProfileData {

    private String mName;
    private String mSName;
    private String mEmail;
    private Long    mDNI;
    private Integer mID;

    public PatientProfileData(String mName,String mSName,String mEmail, Long mDNI) {
        this.setmName(mName);
        this.setmSName(mSName);
        this.mDNI  = mDNI;
    }


    private void setmName(String mName) {
        if ((mName == null) || mName.trim().isEmpty())
            throw new PatientProfileDataNullNameException();
        this.mName = mName;
    }

    private void setmSName (String mSName){
        if ((mSName == null) || mSName.trim().isEmpty() )
            throw new PatientProfileDataNullSurnameException();
        this.mSName = mSName;
    }

    private void setmDNI (Long mDNI){
        if (mDNI == null)
            throw new PatientProfileDataNullDniException();
        if (mDNI == 0)
            throw new PatientProfileDataDniIsZeroException();
        this.mDNI = mDNI;
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
