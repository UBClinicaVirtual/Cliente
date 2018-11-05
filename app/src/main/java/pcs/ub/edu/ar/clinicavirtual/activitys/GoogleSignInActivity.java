package pcs.ub.edu.ar.clinicavirtual.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.ServerRequestLoginUser;
import pcs.ub.edu.ar.clinicavirtual.google.Google;
import pcs.ub.edu.ar.clinicavirtual.handler.LoginResponseHandler;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces.IGoogle;

public class GoogleSignInActivity extends BaseActivity {


    IGoogle mGoogle = new Google(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_sign_in);

        mGoogle.GoogleConnection(this, R.string.server_client_id);
        initListeners();

    }


    private void initListeners() {
        findViewById(R.id.btnGoogleSignIn).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnGoogleSignIn:

                startActivityForResult( mGoogle.SignInIntent() , mGoogle.RC() );

                break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

        mGoogle.handleSignInResult(task);


        connector().execute(new ServerRequestLoginUser(R.id.btnGoogleSignIn, mGoogle.account().getIdToken() ),this);
        //deberia ir
        // connector().call(new ServerRequestRegisterUser(R.id.sign_in_button,idToken),this);
    }

    @Override
    protected void loadHandlers() {
        this.getHandlers().put(R.id.btnGoogleSignIn,new LoginResponseHandler());
    }
}
