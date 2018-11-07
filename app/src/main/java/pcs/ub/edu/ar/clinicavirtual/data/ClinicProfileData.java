package pcs.ub.edu.ar.clinicavirtual.data;

import java.util.List;


import pcs.ub.edu.ar.clinicavirtual.data.exception.ClinicProfileDataException.ClinicProfileDataIdIsZeroException;
import pcs.ub.edu.ar.clinicavirtual.data.exception.ClinicProfileDataException.ClinicProfileDataNullBusinessNameException;
import pcs.ub.edu.ar.clinicavirtual.data.exception.ClinicProfileDataException.ClinicProfileDataNullIdException;
import pcs.ub.edu.ar.clinicavirtual.data.exception.ClinicProfileDataException.ClinicProfileDataNullSpecialitiesException;


import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IClinicProfileData;


public class ClinicProfileData implements IClinicProfileData {

    Integer mId;
    String mBusinessName;
    List<Speciality> mSpecialties;

    public ClinicProfileData(Integer id, String businessName, List<Speciality> specialities) {
            setmId(id);
            setmBusinessName(businessName);
            setSpecialties(specialities);
    }

    @Override
    public Integer getmID() {
        return mId;
    }

    @Override
    public String getmName() {
        return mBusinessName;
    }

    @Override
    public String getmEmail() {
        return null;
    }

    @Override
    public List<Speciality> getSpecialties() {

        /*List<Speciality> mSpecialitys = new LinkedList<Speciality>();
        mSpecialitys.add( new Speciality("Ginecologo"));
        mSpecialitys.add( new Speciality("TocoGinecologo"));*/

        return mSpecialties;
    }

    private void setSpecialties(List <Speciality> Specialties){
        if ((Specialties == null) || Specialties.isEmpty())
            throw new ClinicProfileDataNullSpecialitiesException();
        this.mSpecialties = Specialties;
    }

    private void setmId(Integer Id){
        if (Id == null)
            throw new ClinicProfileDataNullIdException();
        if (Id == 0)
            throw new ClinicProfileDataIdIsZeroException();
        this.mId = Id;
    }

    private void setmBusinessName (String businessName){
        //if ((mName == null) || mName.trim().isEmpty())
        if ((businessName == null) || businessName.trim().isEmpty())
           throw new ClinicProfileDataNullBusinessNameException();
        this.mBusinessName = businessName;
    }
}
