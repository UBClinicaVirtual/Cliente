package pcs.ub.edu.ar.clinicavirtual.connection;

import org.json.JSONObject;

import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IClinicProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IHCPProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IPatientProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IServerConnector;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IUserProfileData;

public class ServerConnectorProxy implements IServerConnector {

    private  IServerConnector mLocalConnector = new ServerConnectorDevelopment();
    private  IServerConnector mInternetConnector = new ServerConnectorInternet();

    private IServerConnector getConnector(){
        if(mLocalConnector!=null)
            return mLocalConnector;
        return mInternetConnector;
    }

    @Override
    public IUserProfileData register(String tokenGmail) {
        return mInternetConnector.register(tokenGmail);
    }

    @Override
    public IUserProfileData login(String tokenGmail) {
        return mInternetConnector.login(tokenGmail);
    }

    @Override
    public void logout() {


    }

    @Override
    public IPatientProfileData addPatientProfileToUserAccount(JSONObject patientData) {
        return getConnector().addPatientProfileToUserAccount(patientData);
    }

    @Override
    public IClinicProfileData addClinicProfileToUserAccount(IClinicProfileData clinicData) {
        return getConnector().addClinicProfileToUserAccount(clinicData);
    }

    @Override
    public IHCPProfileData addHCPProfileToUserAccount(IHCPProfileData hcpData) {
        return getConnector().addHCPProfileToUserAccount(hcpData);
    }

    @Override
    public IPatientProfileData getUserPatientProfile() {
        return getConnector().getUserPatientProfile();
    }
}
