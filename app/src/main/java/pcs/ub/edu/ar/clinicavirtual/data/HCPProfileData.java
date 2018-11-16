package pcs.ub.edu.ar.clinicavirtual.data;

import java.util.List;

import pcs.ub.edu.ar.clinicavirtual.interfaces.data.IHCPProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.ISpeciality;

public class HCPProfileData implements IHCPProfileData {

    Integer mID;
    String  mFirstName;
    String  mLastName;
    Long    mLicenseID;
    List<ISpeciality> mSpecialities;


    public HCPProfileData(Integer id, String name, Long licenseID, List<ISpeciality> specialities){
        mID = id;
        mFirstName = name;
        mLicenseID = licenseID;
        mSpecialities = specialities;
    }

    public HCPProfileData(Integer id, String lastName, String firstName ){
        mID = id;
        mLastName = lastName;
        mFirstName = firstName;
    }

    @Override
    public Integer getmID() {
        return mID;
    }

    @Override
    public String getmFirstName() {
        return mFirstName;
    }

    @Override
    public String getmEmail() {
        return null;
    }

    @Override
    public List<ISpeciality> getSpecialities() {
        return mSpecialities;
    }

    @Override
    public Long getLicenseID() {
        return mLicenseID;
    }


    public String getmLastName() {
        return mLastName;
    }

    public String completeName(){
        return (mLastName+", "+mFirstName);
    }

}
