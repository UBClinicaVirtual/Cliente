package pcs.ub.edu.ar.clinicavirtual.activitys.register;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IElementsConfiguration;

public class DataRegisterClinicConfiguration extends BaseActivity implements IElementsConfiguration {

    public Activity mActivity;;

    Spinner mSpnCivilState;
    Spinner mSpnGender;
    Button btnBirthDate;
    DatePickerDialog.OnDateSetListener mDateListener;
    ArrayAdapter<CharSequence> mAdapter;

    TextView mBusiness_Name;
    TextView mBusiness_Number;
    TextView mAddress;
    TextView mPhone;
    // missing mHcp_Specialities;




    public DataRegisterClinicConfiguration(DataRegisterActivity dataRegisterActivity) {
        mActivity = dataRegisterActivity;
    }

    @Override
    public void initElements() {

        mBusiness_Name = mActivity.findViewById(R.id.txtClinicName);
        mBusiness_Number = mActivity.findViewById(R.id.numClinicCuit);
        mAddress = mActivity.findViewById(R.id.txtClinicAddress);
        mPhone = mActivity.findViewById(R.id.txtClinicPhone);
        //missing hcp_specialities


    }

    @Override
    public JSONObject getInformationJsonFormat() {
        JSONObject mDataProfile = new JSONObject();
        JSONObject mProfile = new JSONObject();

        try {

            mDataProfile.put( "business_name", mBusiness_Name.getText());
            mDataProfile.put("business_number",mBusiness_Number.getText());
            mDataProfile.put("address",mAddress.getText());
            mDataProfile.put("phone",mPhone.getText());
            //missing hcp_specialities

            mProfile.put("clinic",mDataProfile);

            return mProfile;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void loadNextActivityHandler() {

    }
}
