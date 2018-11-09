package pcs.ub.edu.ar.clinicavirtual.activitys.general;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.user.ServerRequestUserGetPatientAppointments;
import pcs.ub.edu.ar.clinicavirtual.handler.GetPatientAppointmentsHandler;

public class MyTurnsActivity extends BaseActivity {

    Button mSearchMyTurns;
    TextView mMyTurns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_activity_my_turns);
        initScreen();
        initElements();
        initListeners();
    }

    private void initScreen() {
        android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.RED));
    }

    private void initListeners() {
        findViewById(R.id.btnPatientMyTurns).setOnClickListener(this);
    }

    private void initElements() {
        mSearchMyTurns = (Button) findViewById(R.id.btnPatientMyTurns);
        mMyTurns = (TextView) findViewById(R.id.txtMyTurns);


    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPatientMyTurns:
                ServerRequestUserGetPatientAppointments getPatientAppointments = new ServerRequestUserGetPatientAppointments(R.id.btnPatientMyTurns);
                getPatientAppointments.apiToken( apitoken() );
                connector().execute(getPatientAppointments,this);
        }
    }

    @Override
    protected void loadNextActivityHandler() {

    }

    @Override
    protected void loadHandlers() {
        this.handlers().put(R.id.btnPatientMyTurns, new GetPatientAppointmentsHandler());
    }
}
