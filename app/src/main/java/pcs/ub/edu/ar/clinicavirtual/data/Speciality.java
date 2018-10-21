package pcs.ub.edu.ar.clinicavirtual.data;

import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.ISpeciality;

public class Speciality implements ISpeciality {
    private String mName;
    private Integer mIdSpeciality;

    public Speciality(Integer idSpeciality, String name ) {
        setmIdSpeciality(idSpeciality);
        setmName(name);
    }

    @Override
    public String getmName() {
        return mName;
    }

    private void setmName(String mName) {
        this.mName = mName;
    }

    @Override
    public Integer getmIdSpeciality() {
        return mIdSpeciality;
    }

    public void setmIdSpeciality(Integer mIdSpeciality) {
        this.mIdSpeciality = mIdSpeciality;
    }
}
