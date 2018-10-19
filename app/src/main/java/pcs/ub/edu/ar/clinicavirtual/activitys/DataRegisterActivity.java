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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.factory.ProfileFactory;

public class DataRegisterActivity extends BaseActivity {

    Spinner mSpnProfile;
    Spinner mSpnCivilState;
    Spinner mSpnGender;
    Spinner mSpnAllergies;
    ArrayAdapter<CharSequence> mAdapter;

    List < LinearLayout > mRegisterLayouts;
    List <EditText> mComponents; //PACIENTE

    Button mBtnCancel;
    Button mBtnNext;


    EditText txtPatientAllergies;

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
                //no se crea el profile, se deberia mandar la informacion al server.
                //new ProfileFactory().generate(mSpnProfile,mComponents);
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

        mSpnGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 1){
                    mSpnCivilState =  (Spinner) findViewById(R.id.spnPatientCivilState);
                    mAdapter = ArrayAdapter.createFromResource(DataRegisterActivity.this, R.array.arrayFemaleCivilState, R.layout.data_register_spinner_text_style );
                    mSpnCivilState.setAdapter(mAdapter);
                }else{
                    mSpnCivilState =  (Spinner) findViewById(R.id.spnPatientCivilState);
                    mAdapter = ArrayAdapter.createFromResource(DataRegisterActivity.this, R.array.arrayMaleCivilState, R.layout.data_register_spinner_text_style );
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
                }else{
                    Toast.makeText(DataRegisterActivity.this, "Alergia erronea o ya elegida", Toast.LENGTH_SHORT).show();
                }

                

            }

            private boolean ItemSelectedOK(String allergieSelected) {
                String[] arrayAllergies = txtPatientAllergies.getText().toString().split("\n");
                ArrayList<String>arrayListAllergies = new ArrayList<>(Arrays.asList(arrayAllergies));
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

    private void showProfileDataLayout(int position){
        mRegisterLayouts.get(position).setVisibility(View.VISIBLE);
    }

    private void cleanLayouts(){
        for(LinearLayout ll : mRegisterLayouts){
            ll.setVisibility(View.INVISIBLE);
        }
    }

    private void initElements() {

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

            //patient profile
            mSpnGender = (Spinner) findViewById(R.id.spnPatientGender);
            mAdapter = ArrayAdapter.createFromResource(this,R.array.arrayGender,R.layout.data_register_spinner_text_style);
            mSpnGender.setAdapter(mAdapter);

            //patient options allergies
            mSpnAllergies = (Spinner) findViewById(R.id.spnPatientAllergies);
            mAdapter = ArrayAdapter.createFromResource(this,R.array.arrayAllergies,R.layout.data_register_spinner_text_style);
            mSpnAllergies.setAdapter(mAdapter);

            //patient allergies choosed
            txtPatientAllergies = (EditText) findViewById(R.id.txtPatientAllergies);
            txtPatientAllergies.setEnabled(false);
        txtPatientAllergies.setText( "" );

        //the buttons are initialized
        mBtnCancel = (Button)findViewById(R.id.btnCancel);
        mBtnNext = (Button)findViewById(R.id.btnNext);

    }
}
