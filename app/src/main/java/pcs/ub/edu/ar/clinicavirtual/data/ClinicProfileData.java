package pcs.ub.edu.ar.clinicavirtual.data;

import java.util.LinkedList;
import java.util.List;

import pcs.ub.edu.ar.clinicavirtual.interfaces.IClinicProfileData;

public class ClinicProfileData implements IClinicProfileData {

    public ClinicProfileData(String name, List<Speciality> listSpecialitys) {

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
        List<Speciality> mSpecialitys = new LinkedList<Speciality>();
        mSpecialitys.add( new Speciality("Ginecologo"));
        mSpecialitys.add( new Speciality("TocoGinecologo"));

        return mSpecialitys;
    }
}
