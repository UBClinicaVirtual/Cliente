package pcs.ub.edu.ar.clinicavirtual.connection;

import java.util.Collection;

import pcs.ub.edu.ar.clinicavirtual.data.ClinicProfileData;
import pcs.ub.edu.ar.clinicavirtual.data.PatientProfileData;
import pcs.ub.edu.ar.clinicavirtual.factory.JsonFactory.JsonResponse;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IClinicProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IHCPProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IPatientProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IServerConnector;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IUserProfileData;

public class ServerConnectorDevelopment implements IServerConnector {
    @Override
    public IUserProfileData register(String tokenGmail) {
        return null;
    }

    @Override
    public IUserProfileData login(String tokenGmail) {
        return null;
    }

    @Override
    public void logout() {

    }

    @Override
    public IPatientProfileData addPatientProfileToUserAccount(IPatientProfileData patientData) {
        return new PatientProfileData("gabriel","espina","gabyespina@gmail.com", new Long(38579707));
    }

    @Override
    public IClinicProfileData addClinicProfileToUserAccount(IClinicProfileData clinicData) {
        return clinicData;
    }

    @Override
    public IHCPProfileData addHCPProfileToUserAccount(IHCPProfileData hcpData) {
        return hcpData;
    }

    @Override
    public IPatientProfileData getUserPatientProfile() {

        return new PatientProfileData("gabriel","espina","gabyespina.ge@gmail.com",new Long(123));
    }

    public Collection<ClinicProfileData> searchClinicByName(String nameFindClinic) {



        return null;
    }
}
