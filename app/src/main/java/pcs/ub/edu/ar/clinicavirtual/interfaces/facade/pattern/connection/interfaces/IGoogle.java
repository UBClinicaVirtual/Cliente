package pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces;

import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;

import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;

public interface IGoogle {

    public void GoogleConnection(BaseActivity activity, Integer clientId);

    public Intent SignInIntent();

    public Integer RC();

    public void handleSignInResult(Task<GoogleSignInAccount> completedtask);

    public GoogleSignInAccount account();

}
