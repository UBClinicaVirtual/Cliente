package pcs.ub.edu.ar.clinicavirtual.activitys.register.config;

//<editor-fold desc="IMPORTS">
import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.*;
import org.json.JSONObject;
import java.util.*;
import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.activitys.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.activitys.DataRegisterActivity;
import pcs.ub.edu.ar.clinicavirtual.factory.JsonFactory.JsonFactory;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IElementsConfiguration;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces.IServerRequest;
import pcs.ub.edu.ar.clinicavirtual.interfaces.json.interfaces.IJsonFactory;
//</editor-fold>

public class DataRegisterPatientConfiguration extends BaseActivity implements IElementsConfiguration {

    public Activity mActivity;;

    Spinner mSpnCivilState;
    Spinner mSpnGender;
    Button btnBirthDate;
    DatePickerDialog.OnDateSetListener mDateListener;
    ArrayAdapter<CharSequence> mAdapter;
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
        mAdapter = ArrayAdapter.createFromResource(mActivity,R.array.arrayAllergies,R.layout.data_register_spinner_text_style);

        btnBirthDate = mActivity.findViewById(R.id.btnPatientBirthPicker);

        btnBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month= cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        mActivity,
                        R.style.Theme_Design_BottomSheetDialog,
                        mDateListener,
                        year,month,day
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));
                dialog.show();
            }
        });

        mDateListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Toast.makeText(mActivity, year +"/"+month+"/"+dayOfMonth, Toast.LENGTH_SHORT).show();
            }
        };


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
