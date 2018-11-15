package pcs.ub.edu.ar.clinicavirtual.connection.check.internet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;

public class Internet {

    public static boolean isNetAvailable(BaseActivity activity) {

        ConnectivityManager connectivityManager = (ConnectivityManager)
                activity.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();

        return (actNetInfo != null && actNetInfo.isConnected());
    }

    public static  Boolean isOnlineNet() {

        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es");

            int val           = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
