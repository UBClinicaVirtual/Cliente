//<editor-fold desc="PACKAGE">
package pcs.ub.edu.ar.clinicavirtual.activitys.register;
//</editor-fold>

//<editor-fold desc="IMPORTS">
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.user.ServerRequestUserAddPatientProfile;
import pcs.ub.edu.ar.clinicavirtual.handler.AddPatientProfileHandler;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IElementsConfiguration;
//</editor-fold>


public class DataRegisterActivity extends BaseActivity {

    Spinner mSpnProfile;
    ArrayAdapter<CharSequence> mAdapter;
    Integer mSpnProfileSelected;

    List < LinearLayout > mRegisterLayouts;
    List <EditText> mComponents; //PACIENTE

    Button mBtnCancel;
    Button mBtnNext;

    IElementsConfiguration mPatientConfiguration;
    IElementsConfiguration mHCPConfiguration;
    IElementsConfiguration mClinicConfiguration;

    ArrayList <IElementsConfiguration> mElementsConfigurations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_register);


        //The elements are initialized
        initElements();
        createClickListeners();
        initElementsConfigurations();
        setApiToken();

    }

    private void setApiToken() {
        SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        String apitoken = mSharedPreferences.getString("api_token","no hay apitoken");
        connector().apiToken(apitoken);
    }

    private void initElementsConfigurations() {

        mElementsConfigurations = new ArrayList<IElementsConfiguration>();

        mPatientConfiguration = new DataRegisterPatientConfiguration(this);
        mHCPConfiguration     = new DataRegisterHCPConfiguration (this);
        mClinicConfiguration  = new DataRegisterClinicConfiguration(this);

        mElementsConfigurations.add(mHCPConfiguration);
        mElementsConfigurations.add(mPatientConfiguration);
        mElementsConfigurations.add(mClinicConfiguration);


        mPatientConfiguration.initElements();

    }

    private void createClickListeners() {
        createOnClickListenerSpinner();
        createOnClickListenerButtons();
    }

    private void createOnClickListenerButtons() {
        //we add behavior to the button mBtnNext
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendParametersToServer();


                /*Toast.makeText(DataRegisterActivity.this, "Boton Finalizar", Toast.LENGTH_SHORT).show();
                initProfile();
                Intent mIntentMainScreen = new Intent(DataRegisterActivity.this, MainScreenActivity.class);
                startActivity(mIntentMainScreen);*/

            }

            private void initProfile() {

                JSONObject mProfileData = mElementsConfigurations.get( mSpnProfileSelected ).getInformationJsonFormat();
                /*getServerConnector().addPatientProfileToUserAccount(mProfileData);
                connector().call();*/

                //Toast.makeText(DataRegisterActivity.this, mProfileData.toString() , Toast.LENGTH_SHORT).show();

                //new ProfileFactory().generate(mSpnProfile,mComponents);
                //Toast.makeText(DataRegisterActivity.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
            }
        });

        //we add behavior to the button mBtnCancel
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // onBackPressed();
                Toast.makeText(DataRegisterActivity.this, "Boton Cancelar", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void sendParametersToServer() {

        JSONObject mProfile = new JSONObject();
        switch (mSpnProfileSelected){
            case 0:
                //DOCTOR
                mProfile = mHCPConfiguration.getInformationJsonFormat();
                Toast.makeText(this, mProfile.toString() , Toast.LENGTH_SHORT).show();
                break;
            case 1:
                //PATIENT
                mProfile = mPatientConfiguration.getInformationJsonFormat();
                ServerRequestUserAddPatientProfile addPatientProfile = new ServerRequestUserAddPatientProfile(R.id.btnNext, mProfile.toString());
                addPatientProfile.apiToken( apitoken() );
                connector().execute(addPatientProfile,this);
                break;
            case 2:
                //CLINIC
                mProfile = mClinicConfiguration.getInformationJsonFormat();
                Toast.makeText(this, mProfile.toString() , Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void createOnClickListenerSpinner() {
        mSpnProfile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cleanLayouts();
                showProfileDataLayout(position);
                mSpnProfileSelected = position;
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                cleanLayouts();
            }
        });
    }

    private void showProfileDataLayout(int position){
        mRegisterLayouts.get(position).setVisibility(View.VISIBLE);
    }

    private void cleanLayouts(){
        for(LinearLayout ll : mRegisterLayouts){
            ll.setVisibility(View.INVISIBLE);
        }
    }

    private void initElements() {
        //<editor-fold desc="INITIALIZATION OF ELEMENTS">
        //the spinners is initialized
        mSpnProfile =  (Spinner) findViewById(R.id.spn_profile);
        mAdapter = ArrayAdapter.createFromResource(this, R.array.arrayProfiles, R.layout.data_register_spinner_text_style );
        mSpnProfile.setAdapter(mAdapter);

        //the layouts are initialized
        mRegisterLayouts = new LinkedList<LinearLayout>();
        mRegisterLayouts.add((LinearLayout) findViewById(R.id.linDoctor));
        mRegisterLayouts.add((LinearLayout) findViewById(R.id.linPatient));
        mRegisterLayouts.add((LinearLayout) findViewById(R.id.linClinic));
        mRegisterLayouts.add((LinearLayout) findViewById(R.id.linAdmin));

        //the buttons are initialized
        mBtnCancel = (Button)findViewById(R.id.btnCancel);
        mBtnNext = (Button)findViewById(R.id.btnNext);
        //</editor-fold>
    }



    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }

    @Override
    protected void loadNextActivityHandler() {

    }

    @Override
    protected void loadHandlers() {
        this.handlers().put(R.id.btnNext, new AddPatientProfileHandler());
    }
}
