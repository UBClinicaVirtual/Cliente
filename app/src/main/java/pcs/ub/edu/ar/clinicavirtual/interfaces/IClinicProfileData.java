package pcs.ub.edu.ar.clinicavirtual.interfaces;

import java.util.List;

import pcs.ub.edu.ar.clinicavirtual.data.Speciality;

public interface IClinicProfileData extends IUserProfileData {
    public List<Speciality> getSpecialties();
}