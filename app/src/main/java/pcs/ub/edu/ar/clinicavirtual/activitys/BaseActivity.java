package pcs.ub.edu.ar.clinicavirtual.activitys;

import android.support.v7.app.AppCompatActivity;

import pcs.ub.edu.ar.clinicavirtual.connection.ServerConnector;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IServerConnector;

public class BaseActivity extends AppCompatActivity {

    public IServerConnector getServerConnector() {
        return ServerConnector.getConnector();
    }

}
