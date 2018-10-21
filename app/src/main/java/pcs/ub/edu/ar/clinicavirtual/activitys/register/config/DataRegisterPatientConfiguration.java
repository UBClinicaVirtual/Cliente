package pcs.ub.edu.ar.clinicavirtual.activitys.register.config;

//<editor-fold desc="IMPORTS">
import android.app.Activity;
import android.view.View;
import android.widget.*;
import org.json.JSONObject;
import java.util.*;
import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.activitys.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.activitys.DataRegisterActivity;
import pcs.ub.edu.ar.clinicavirtual.factory.JsonFactory.JsonFactory;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IElementsConfiguration;
import pcs.ub.edu.ar.clinicavirtual.interfaces.json.interfaces.IJsonFactory;
//</editor-fold>

public class DataRegisterPatientConfiguration extends BaseActivity implements IElementsConfiguration {

    public Activity mActivity;;

    Spinner mSpnCivilState;
    Spinner mSpnGender;
    Spinner mSpnAllergies;
    ArrayAdapter<CharSequence> mAdapter;
    EditText txtPatientAllergies;
    IJsonFactory mJsonFactory = new JsonFactory();

    public DataRegisterPatientConfiguration(DataRegisterActivity activity ){
        mActivity = activity;
    }

    @Override
    public void initElements() {
        //patient profile
        mSpnGender = (Spinner) mActivity.findViewById(R.id.spnPatientGender);
        mAdapter = ArrayAdapter.createFromResource(mActivity,R.array.arrayGender,R.layout.data_register_spinner_text_style);
        mSpnGender.setAdapter(mAdapter);

        //patient options allergies
        mSpnAllergies = (Spinner) mActivity.findViewById(R.id.spnPatientAllergies);
        mAdapter = ArrayAdapter.createFromResource(mActivity,R.array.arrayAllergies,R.layout.data_register_spinner_text_style);
        mSpnAllergies.setAdapter(mAdapter);

        //patient allergies choosed
        txtPatientAllergies = (EditText) mActivity.findViewById(R.id.txtPatientAllergies);
        txtPatientAllergies.setEnabled(false);
        txtPatientAllergies.setText( "" );

        createOnClickListenerSpinner();
    }

    @Override
    public JSONObject getInformationJsonFormat() {

        return mJsonFactory.objectToJson().getPatientProfileData( null );

    }


    private void createOnClickListenerSpinner() {

        mSpnGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 1){
                    mSpnCivilState =  (Spinner) mActivity.findViewById(R.id.spnPatientCivilState);
                    mAdapter = ArrayAdapter.createFromResource(mActivity, R.array.arrayFemaleCivilState, R.layout.data_register_spinner_text_style );
                    mSpnCivilState.setAdapter(mAdapter);
                }else{
                    mSpnCivilState =  (Spinner) mActivity.findViewById(R.id.spnPatientCivilState);
                    mAdapter = ArrayAdapter.createFromResource( mActivity , R.array.arrayMaleCivilState, R.layout.data_register_spinner_text_style );
                    mSpnCivilState.setAdapter(mAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}


        });

        mSpnAllergies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String mAllergieSelected = ((String)mSpnAllergies.getItemAtPosition(position)).toString();
                if (ItemSelectedOK( mAllergieSelected )){
                    String mText = txtPatientAllergies.getText().toString() + "\n" + ((String)mSpnAllergies.getItemAtPosition(position)).toString();
                    txtPatientAllergies.setText( mText );
                }
            }

            private boolean ItemSelectedOK(String allergieSelected) {
                String[] arrayAllergies = txtPatientAllergies.getText().toString().split("\n");
                ArrayList<String> arrayListAllergies = new ArrayList<>(Arrays.asList(arrayAllergies));
                if(allergieSelected.toString().equals("INGRESAR ALERGIAS"))
                    return false;

                for (String mAllergie : arrayListAllergies)
                    if(mAllergie.toString().equals(allergieSelected.toString()))
                        return false;
                return true;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                txtPatientAllergies.setText( "" );
            }
        });

    }


}
