package pcs.ub.edu.ar.clinicavirtual.factory;

import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import pcs.ub.edu.ar.clinicavirtual.activitys.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.data.PatientProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IAdminProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IClinicProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IHCPProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IPatientProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces.IServerRequest;

public class ProfileFactory extends BaseActivity {

    List<EditText> mComponents;

    public void generate(Spinner mSpnProfile, List<EditText>mComponents){
        this.mComponents = mComponents;

        int spinnerPos = mSpnProfile.getSelectedItemPosition();

        if( spinnerPos == 0){

             //getServerConnector().addHCPProfileToUserAccount(createHCPProfile());

        }else if( spinnerPos == 1){

            //getServerConnector().addPatientProfileToUserAccount( createPatientProfile() );

        }else if( spinnerPos == 2){

             //getServerConnector().addClinicProfileToUserAccount(createClinicProfile());

        }else if( spinnerPos == 3){

            // createAdminProfile();

        }
    }

    private IAdminProfileData createAdminProfile() {
        return null;
    }

    private IClinicProfileData createClinicProfile() {
        return null;

    }

    private IPatientProfileData createPatientProfile() {

        String mPatientName = mComponents.get(0).getText().toString();
        String mPatientSName = mComponents.get(1).getText().toString();
        Long mPatientsDNI = Long.parseLong(mComponents.get(2).getText().toString());
        //we create the corresponding profile
        IPatientProfileData mPatientProfileData = new PatientProfileData(mPatientName, mPatientSName, "Gabyespina.ge@gmail.com",mPatientsDNI);

        return mPatientProfileData;

    }

    private IHCPProfileData createHCPProfile() {
        return null;

    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }
}
