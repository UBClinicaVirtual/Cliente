package pcs.ub.edu.ar.clinicavirtual.interfaces;

public interface IServerConnector {


    //Register with gmail
    public IUserData register(String tokenGmail);

    //Login with gmail
    public IUserData login (String tokenGmail);

    //Logout
    public void logout ();

    //Add the patient profile to the user's account
    public IPatientProfileData addPatientProfileToUserAccount( IPatientProfileData patientData );
}
