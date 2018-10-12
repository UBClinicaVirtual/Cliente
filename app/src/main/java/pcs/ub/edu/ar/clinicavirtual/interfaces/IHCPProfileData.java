package pcs.ub.edu.ar.clinicavirtual.interfaces;

import java.util.List;

public interface IHCPProfileData extends IUserProfileData {
    public List<ISpeciality> getSpecialities();

    public Long getLicenseID();
}
