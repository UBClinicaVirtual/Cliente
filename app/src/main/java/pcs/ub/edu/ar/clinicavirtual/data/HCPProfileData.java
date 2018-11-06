package pcs.ub.edu.ar.clinicavirtual.data;

import java.util.List;

import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IHCPProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.ISpeciality;

public class HCPProfileData implements IHCPProfileData {

    Integer  mID;
    String   mName;
    Long     mLicenseID;
    List<ISpeciality> mSpecialities;


    public HCPProfileData(Integer id, String name, Long licenseID, List<ISpeciality> specialities){
        mID = id;
        mName = name;
        mLicenseID = licenseID;
        mSpecialities = specialities;
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
}
