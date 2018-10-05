package pcs.ub.edu.ar.clinicavirtual.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.factory.ProfileCreator;

public class DataRegisterActivity extends BaseActivity {

    Spinner mSpnProfile;
    ArrayAdapter<CharSequence> mProfileAdapter;

    List < LinearLayout > mRegisterLayouts;
    List <EditText> mComponents; //PACIENTE

    Button mBtnCancel;
    Button mBtnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_register);

        //The elements are initialized
        initElements();
        createClickListeners();

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
                Toast.makeText(DataRegisterActivity.this, "Boton Finalizar", Toast.LENGTH_SHORT).show();
                initProfile();
                Intent mIntentMainScreen = new Intent(DataRegisterActivity.this, MainScreenActivity.class);
                startActivity(mIntentMainScreen);

            }

            private void initProfile() {

                new ProfileCreator().generate(mSpnProfile,mComponents);
                Toast.makeText(DataRegisterActivity.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
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

    private void createOnClickListenerSpinner() {
        mSpnProfile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cleanLayouts();
                showProfileDataLayout(position);
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

        //the spinner is initialized
        mSpnProfile =  (Spinner) findViewById(R.id.spn_profile);
        mProfileAdapter = ArrayAdapter.createFromResource(this, R.array.arrayProfiles, R.layout.data_register_spinner_text_style );
        mSpnProfile.setAdapter(mProfileAdapter);

        //the layouts are initialized
        mRegisterLayouts = new LinkedList<LinearLayout>();
        mRegisterLayouts.add((LinearLayout) findViewById(R.id.linDoctor));
        mRegisterLayouts.add((LinearLayout) findViewById(R.id.linPatient));

            mComponents = new LinkedList<EditText>();
            mComponents.add((EditText) findViewById(R.id.txtPatientName));
            mComponents.add((EditText) findViewById(R.id.txtPatientSName));
            mComponents.add((EditText) findViewById(R.id.numPatientDNI));

        mRegisterLayouts.add((LinearLayout) findViewById(R.id.linClinic));
        mRegisterLayouts.add((LinearLayout) findViewById(R.id.linAdmin));

        //the buttons are initialized
        mBtnCancel = (Button)findViewById(R.id.btnCancel);
        mBtnNext = (Button)findViewById(R.id.btnNext);

    }
}
