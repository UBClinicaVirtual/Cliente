package pcs.ub.edu.ar.clinicavirtual.activitys.register;

//<editor-fold desc="IMPORTS">
import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.*;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.*;
import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IElementsConfiguration;
//</editor-fold>

public class DataRegisterPatientConfiguration extends BaseActivity implements IElementsConfiguration {

    public Activity mActivity;;

    Spinner mSpnCivilState;
    Spinner mSpnGender;
    Button btnBirthDate;
    DatePickerDialog.OnDateSetListener mDateListener;
    ArrayAdapter<CharSequence> mAdapter;

    TextView mName;
    TextView mSecondName;
    TextView mDNI;
    String mBirthDate;
    Integer mGenderID;
    TextView mAddress;
    TextView mPhone;


    public DataRegisterPatientConfiguration(DataRegisterActivity activity ){
        mActivity = activity;
    }


    public void initElements() {
        mName = mActivity.findViewById(R.id.txtPatientName);
        mSecondName = mActivity.findViewById(R.id.txtPatientSName);
        mDNI = mActivity.findViewById(R.id.numPatientDNI);
        mAddress = mActivity.findViewById(R.id.txtPatientAddress);
        mPhone = mActivity.findViewById(R.id.txtPatientPhone);

        //patient profile
        mSpnGender = (Spinner) mActivity.findViewById(R.id.spnPatientGender);
        mAdapter = ArrayAdapter.createFromResource(mActivity,R.array.arrayGender,R.layout.data_register_spinner_text_style);
        mSpnGender.setAdapter(mAdapter);

        mGenderID = mSpnGender.getSelectedItemPosition();
                //patient options allergies
        mAdapter = ArrayAdapter.createFromResource(mActivity,R.array.arrayAllergies,R.layout.data_register_spinner_text_style);

        btnBirthDate = mActivity.findViewById(R.id.btnPatientBirthPicker);






        initListeners();
    }

    private String validateDate(int mDate ){
        if(mDate < 10)
            return  "-0" + mDate;
        return  "-" + mDate;
    };

    @Override
    public JSONObject getInformationJsonFormat() {

        JSONObject mDataProfile = new JSONObject();
        JSONObject mProfile = new JSONObject();

        try {

            mDataProfile.put("first_name", mName.getText());
            mDataProfile.put("last_name",mSecondName.getText());
            mDataProfile.put("identification_number",mDNI.getText());
            mDataProfile.put("birth_date",mBirthDate);
            mDataProfile.put("gender_id",mGenderID);
            mDataProfile.put("address",mAddress.getText());
            mDataProfile.put("phone",mPhone.getText());

            mProfile.put("patient",mDataProfile);

            return mProfile;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
        //return mJsonFactory.objectToJson().getPatientProfileData( null );

    }


    private void initListeners() {

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
                month = month +1;
                mBirthDate = year + validateDate(month) + validateDate(dayOfMonth);
                Toast.makeText(mActivity,mBirthDate, Toast.LENGTH_SHORT).show();
            }
        };



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
}
