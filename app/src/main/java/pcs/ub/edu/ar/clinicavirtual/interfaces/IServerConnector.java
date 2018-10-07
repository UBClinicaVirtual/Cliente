package pcs.ub.edu.ar.clinicavirtual.interfaces;

import android.widget.EditText;

public interface IServerConnector{


    //Register with gmail
    public IUserProfileData register(String tokenGmail);

    //Login with gmail
    public IUserProfileData login (String tokenGmail);

    //Logout
    public void logout ();

    //Add the patient profile to the user's account
    public IPatientProfileData addPatientProfileToUserAccount( IPatientProfileData patientData );

    //Add the clinic profile to the user's account
    public IClinicProfileData addClinicProfileToUserAccount( IClinicProfileData clinicData );

    //Add the HCP profile to the user's account
    public IHCPProfileData addHCPProfileToUserAccount( IHCPProfileData hcpData );

    //return IPatientProfileData profile
    public IPatientProfileData getUserPatientProfile();
}
