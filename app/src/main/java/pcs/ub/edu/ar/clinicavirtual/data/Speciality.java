package pcs.ub.edu.ar.clinicavirtual.data;

public class Speciality {
    private String mName;
    private String mIdSpeciality;

    public Speciality(String mName ) {
        setmName(mName);
    }


    public String getmName() {
        return mName;
    }

    private void setmName(String mName) {
        this.mName = mName;
    }

    public String getmIdSpeciality() {
        return mIdSpeciality;
    }

    public void setmIdSpeciality(String mIdSpeciality) {
        this.mIdSpeciality = mIdSpeciality;
    }
}
