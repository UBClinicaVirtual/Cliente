package pcs.ub.edu.ar.clinicavirtual.activitys;

import android.os.Bundle;
import android.widget.EditText;

import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IPatientProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IUserProfileData;

public class ProfileActivity extends BaseActivity {

    EditText txtMyProfileName;
    EditText txtMyProfileDNI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initElements();
        loadProfile();
    }

    private void loadProfile() {

        IPatientProfileData mPatientProfileData =  getServerConnector().getUserPatientProfile();

        txtMyProfileName.setText(mPatientProfileData.getmName());
        txtMyProfileDNI.setText(Long.toString(mPatientProfileData.getDNI()));

    }

    private void initElements() {

        txtMyProfileDNI = (EditText) findViewById(R.id.txtMyProfileDNI);
        txtMyProfileName= (EditText) findViewById(R.id.txtMyProfileName);

    }
}