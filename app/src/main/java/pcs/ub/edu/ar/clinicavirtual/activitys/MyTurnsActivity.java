package pcs.ub.edu.ar.clinicavirtual.activitys;

import android.os.Bundle;

import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces.IServerRequest;

public class MyTurnsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_turns);
    }

    @Override
    public void success(IServerRequest request) {

    }
}
