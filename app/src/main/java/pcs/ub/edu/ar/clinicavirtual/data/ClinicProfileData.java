package pcs.ub.edu.ar.clinicavirtual.data;

import java.util.List;

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
        return null;
    }

    @Override
    public String getmName() {
        return null;
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
        this.mSpecialties = Specialties;
    }

    private void setmId(Integer Id){
        this.mId = Id;
    }

    private void setmBusinessName (String businessName){
        this.mBusinessName = businessName;
    }
}
