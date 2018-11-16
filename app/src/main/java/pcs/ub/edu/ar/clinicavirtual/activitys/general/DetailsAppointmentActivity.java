package pcs.ub.edu.ar.clinicavirtual.activitys.general;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;

public class DetailsAppointmentActivity extends BaseActivity {

    TextView mClinicTitle;
    TextView mSpeciality;
    TextView mhcp;
    TextView mdate;
    TextView mstatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        mClinicTitle = findViewById(R.id.ClinicTitle);
        mSpeciality = findViewById(R.id.SpecialityName);
        mhcp = findViewById(R.id.doctorName);
        mdate = findViewById(R.id.AppointmentDate);
        mstatus = findViewById(R.id.appointment_status);

        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {

            mClinicTitle.setText(mBundle.getString("clinicname"));
            mSpeciality.setText(mBundle.getString("SpecialityName"));
            mhcp.setText(mBundle.getString("hcp"));
            mdate.setText(mBundle.getString("date"));
            mstatus.setText(mBundle.getString("appointment_status"));
        }
    }
    @Override
    protected void loadNextActivityHandler() {

    }

    @Override
    public void onClick(View view) {

    }
}
