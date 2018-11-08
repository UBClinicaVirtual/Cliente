package pcs.ub.edu.ar.clinicavirtual.activitys.patient;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.user.ServerRequestUserAddPatientProfile;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.user.ServerRequestUserGetPatientProfile;
import pcs.ub.edu.ar.clinicavirtual.handler.AddPatientProfileHandler;
import pcs.ub.edu.ar.clinicavirtual.handler.GetPatientProfileHandler;

public class ProfileActivity extends BaseActivity {

    EditText txtName;
    EditText txtSName;
    EditText txtDNI;
    EditText txtBirthDate;
    EditText txtGender;
    EditText txtAddress;
    EditText txtPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initScreen();

        setContentView(R.layout.patient_activity_profile);

        initElements();

        initListeners();


    }

    private void initScreen() {
        android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.RED));
    }


    private void initListeners() {
        findViewById(R.id.btnPatientProfileEdit).setOnClickListener(this);
        findViewById(R.id.btnPatientProfileLoad).setOnClickListener(this);
    }



    private void initElements() {

        txtName = (EditText) findViewById(R.id.txtPatientProfileName);
        txtSName = (EditText) findViewById(R.id.txtPatientProfileSName);
        txtDNI = (EditText) findViewById(R.id.txtPatientProfileDNI);
        txtBirthDate = (EditText) findViewById(R.id.txtPatientProfileBirthDate);
        txtGender = (EditText) findViewById(R.id.txtPatientProfileGender);
        txtAddress = (EditText) findViewById(R.id.txtPatientProfileAddreess);
        txtPhone = (EditText) findViewById(R.id.txtPatientProfilePhone);

    }

    public void loadProfile(String response) throws JSONException {
        JSONObject json = new JSONObject(response);
        json = json.getJSONObject("patient");

        txtName.setText(json.getString("first_name"));
        txtSName.setText(json.getString("last_name"));
        txtDNI.setText(json.getString("identification_number"));
        txtBirthDate.setText(json.getString("birth_date"));
        txtGender.setText(json.getString("gender_id"));
        txtAddress.setText(json.getString("address"));
        txtPhone.setText(json.getString("phone"));
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnPatientProfileLoad:
                ServerRequestUserGetPatientProfile getPatientProfile = new ServerRequestUserGetPatientProfile(R.id.btnPatientProfileLoad);
                getPatientProfile.apiToken( apitoken() );
                connector().execute(getPatientProfile,this);
                break;
            case R.id.btnPatientProfileEdit:
                Toast.makeText(this, "Pretende mucho..", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    protected void loadHandlers() {
        this.getHandlers().put(R.id.btnPatientProfileLoad, new GetPatientProfileHandler());
    }
}
