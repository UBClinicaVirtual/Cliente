package pcs.ub.edu.ar.clinicavirtual;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class DataRegisterActivity extends BaseActivity {

    Spinner mSpnProfile;
    ArrayAdapter<CharSequence> mProfileAdapter;

    LinearLayout mLLtab1;
    LinearLayout mLLtab2;
    LinearLayout mLLtab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_register);

        //The elements are initialized
        initElements();
        createOnClickListenerSpinner();
    }

    private void createOnClickListenerSpinner() {
        mSpnProfile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String profile = (String) mSpnProfile.getItemAtPosition(position);
                showProfileDataLayout(profile);
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void showProfileDataLayout(String profile){
        Toast.makeText(this, profile.toString(), Toast.LENGTH_SHORT).show();
        /* if(profile.toString().equals("Doctor")){



        }else if (profile.toString().equals("Paciente")){

        }else if (profile.toString().equals("Clinica")){

        }else if (profile.toString().equals("Adminsitrador")){

        }*/
    }

    private void initElements() {

        mSpnProfile =  (Spinner) findViewById(R.id.spn_profile);
        mProfileAdapter = ArrayAdapter.createFromResource(this, R.array.arrayProfiles, R.layout.data_register_spinner_text_style );
        mSpnProfile.setAdapter(mProfileAdapter);


        mLLtab1 = (LinearLayout) findViewById(R.id.tab1);
        mLLtab2 = (LinearLayout) findViewById(R.id.tab2);
        mLLtab3 = (LinearLayout) findViewById(R.id.tab3);

    }
}
