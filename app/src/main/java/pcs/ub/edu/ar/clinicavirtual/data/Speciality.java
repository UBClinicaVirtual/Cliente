package pcs.ub.edu.ar.clinicavirtual.data;


import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.ISpeciality;

import pcs.ub.edu.ar.clinicavirtual.data.exception.Speciality.SpecialityIdIsZeroException;
import pcs.ub.edu.ar.clinicavirtual.data.exception.Speciality.SpecialityNullIdException;
import pcs.ub.edu.ar.clinicavirtual.data.exception.Speciality.SpecialityNullNameException;



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
        if ((mName == null) || (mName.trim().isEmpty()))
            throw new SpecialityNullNameException();
        this.mName = mName;
    }

    @Override
    public Integer getmIdSpeciality() {
        return mIdSpeciality;
    }

    public void setmIdSpeciality(Integer mIdSpeciality) {
        if (mIdSpeciality == null)
            throw new SpecialityNullIdException();
        if (mIdSpeciality == 0 )
            throw new SpecialityIdIsZeroException();
        this.mIdSpeciality = mIdSpeciality;
    }
}
