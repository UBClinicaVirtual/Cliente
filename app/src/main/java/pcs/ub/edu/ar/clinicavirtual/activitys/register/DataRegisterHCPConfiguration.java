package pcs.ub.edu.ar.clinicavirtual.activitys.register;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IElementsConfiguration;

public class DataRegisterHCPConfiguration extends BaseActivity implements IElementsConfiguration {

    public Activity mActivity;;


    Spinner mSpnGender;
    Button btnBirthDate;
    DatePickerDialog.OnDateSetListener mDateListener;
    ArrayAdapter<CharSequence> mAdapter;

    TextView    mFirst_Name;
    TextView    mLast_Name;
    TextView    mId;
    TextView    mRegister_Number;
    String      mBirthDate;
    Integer     mGenderID;
    TextView    mAddress;
    TextView    mPhone;
    //missing specialities


    public DataRegisterHCPConfiguration(DataRegisterActivity dataRegisterActivity) {
        mActivity = dataRegisterActivity;
    }


    @Override
    public void initElements() {

        mFirst_Name = mActivity.findViewById(R.id.txtDoctorName);
        mLast_Name = mActivity.findViewById(R.id.txtDoctorSName);
        mId = mActivity.findViewById(R.id.numDoctorDNI);
        mAddress = mActivity.findViewById(R.id.txtDoctorAddress);
        mPhone = mActivity.findViewById(R.id.txtDoctorPhone);


        //Doctor profile
        mSpnGender = (Spinner) mActivity.findViewById(R.id.spnDoctorGender);
        mAdapter = ArrayAdapter.createFromResource(mActivity,R.array.arrayGender,R.layout.data_register_spinner_text_style);
        mSpnGender.setAdapter(mAdapter);

        mGenderID = mSpnGender.getSelectedItemPosition();


        btnBirthDate = mActivity.findViewById(R.id.btnDoctorBirthPicker);






        initListeners();


    }

    private void initListeners() {


        //ask gaby
        mSpnGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 1){

                    mAdapter = ArrayAdapter.createFromResource(mActivity, R.array.arrayFemaleCivilState, R.layout.data_register_spinner_text_style );

                }else{

                    mAdapter = ArrayAdapter.createFromResource( mActivity , R.array.arrayMaleCivilState, R.layout.data_register_spinner_text_style );

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
                mBirthDate = year + validateDate(month) + validateDate(dayOfMonth);
                Toast.makeText(mActivity,mBirthDate, Toast.LENGTH_SHORT).show();
            }
        };



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

            mDataProfile.put("first_name", mFirst_Name.getText());
            mDataProfile.put("last_name",mLast_Name.getText());
            mDataProfile.put("identification_number",mId.getText());
            mDataProfile.put("register_number",mRegister_Number.getText());
            mDataProfile.put("birth_date",mBirthDate);
            mDataProfile.put("gender_id",mGenderID);
            mDataProfile.put("address",mAddress.getText());
            mDataProfile.put("phone",mPhone.getText());
            //miss spsecialitie

            mProfile.put("hcp",mDataProfile);

            return mProfile;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
        //return mJsonFactory.objectToJson().getPatientProfileData( null );
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void loadNextActivityHandler() {

    }
}
