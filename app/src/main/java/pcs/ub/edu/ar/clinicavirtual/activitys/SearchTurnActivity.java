package pcs.ub.edu.ar.clinicavirtual.activitys;

import android.os.Bundle;

import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces.IServerRequest;

public class SearchTurnActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_turn);
    }

    @Override
    public void success(IServerRequest request) {

    }
}
