package pcs.ub.edu.ar.clinicavirtual.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.json.JSONException;
import org.json.JSONObject;

import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.ServerRequestLoginUser;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.user.ServerRequestUserGetPatientProfile;
import pcs.ub.edu.ar.clinicavirtual.google.Google;
import pcs.ub.edu.ar.clinicavirtual.handler.LoginResponseHandler;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces.IGoogle;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces.IServerRequest;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    IGoogle mGoogle = new Google(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGoogle.GoogleConnection(this, R.string.server_client_id);
        initListeners();
    }

    //the buttons are initialized
    private void initListeners() {
        findViewById(R.id.btnSignIn).setOnClickListener(this);
    }


    //depending on the button to make
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnSignIn:
                //PREGUNTAR QUE ID USER TRAJO
                startActivityForResult( mGoogle.SignInIntent() , mGoogle.RC() );
                break;

        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        mGoogle.handleSignInResult(task);
        connector().execute(new ServerRequestLoginUser(R.id.btnSignIn, mGoogle.account().getIdToken() ),this);
    }

    @Override
    protected void loadHandlers() {
        this.getHandlers().put(R.id.btnSignIn, new LoginResponseHandler());
    }

/*
// A TENER EN CUENTA ( momentaneamente español)
// Ccon el silentSignIn me permite verificar si el usuario ya inicio sesion en la aplicacion
// utilizando Google
GoogleSignIn.silentSignIn()
        .addOnCompleteListener(this, new OnCompleteListener<GoogleSignInAccount>() {
            @Override
            public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                handleSignInResult(task);
            }
        });
 */
}
