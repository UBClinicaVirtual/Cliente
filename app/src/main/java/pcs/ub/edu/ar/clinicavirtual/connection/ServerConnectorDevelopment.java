package pcs.ub.edu.ar.clinicavirtual.connection;

import java.util.Collection;

import pcs.ub.edu.ar.clinicavirtual.data.ClinicData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IPatientProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IServerConnector;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IUserData;

public class ServerConnectorDevelopment implements IServerConnector {
    @Override
    public IUserData register(String tokenGmail) {
        return null;
    }

    @Override
    public IUserData login(String tokenGmail) {
        return null;
    }

    @Override
    public void logout() {

    }

    @Override
    public IPatientProfileData addPatientProfileToUserAccount(IPatientProfileData patientData) {
        return null;
    }

    public void getConector() {
        // TODO Auto-generated method stub

    }
    public Collection<ClinicData> searchClinicByName(String nameFindClinic) {



        return null;
    }
}
