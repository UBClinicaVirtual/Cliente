package pcs.ub.edu.ar.clinicavirtual.activitys.start;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.common.api.ResultCallback;

import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.check.internet.Internet;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.ServerRequestLoginUser;
import pcs.ub.edu.ar.clinicavirtual.google.Google;
import pcs.ub.edu.ar.clinicavirtual.handler.LoginResponseHandler;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.IGoogle;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    IGoogle mGoogle = new Google(this);
    Boolean mCloseApplication = false;

    private GoogleApiClient apiClient;



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

    @Override
    public void onStart(){
        super.onStart();

        if (mGoogle.account().getIdToken() == null){

        }

        findViewById(R.id.progress).setVisibility(View.VISIBLE);
        startActivityForResult( mGoogle.SignInIntent() , mGoogle.RC() );
        //OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(apiClient);
        /*
        if (opr.isDone()){
            // If user cached credentials are valid, will be done the googlesigninresult
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        }else{

            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>()){
                 @Override
                 public void onResult(GoogleSignInResult googleSignInResult)    {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            };

        }
        */
    }

    private void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){
            // user logged
            GoogleSignInAccount acct = result.getSignInAccount();
            updateUI(true);
        }else{
            updateUI(false);
        }
    }

    private void updateUI(boolean signedIn){
        if(signedIn){
            findViewById(R.id.btnSignIn).setVisibility(View.GONE);
        }else {
            findViewById(R.id.btnSignIn).setVisibility(View.VISIBLE);
        }
    }



    //depending on the button to make
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnSignIn:

                if(internetAvailable()){
                    view.setClickable(false);
                    //PREGUNTAR QUE ID USER TRAJO
                    startActivityForResult( mGoogle.SignInIntent() , mGoogle.RC() );

                    findViewById(R.id.progress).setVisibility(View.VISIBLE);
                }else
                    Toast.makeText(this, "Sin conexion a internet", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private boolean internetAvailable() {
        return Internet.isNetAvailable(this) && Internet.isOnlineNet();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, "Analizando informaciion...", Toast.LENGTH_SHORT).show();
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        mGoogle.handleSignInResult(task);
        connector().execute(new ServerRequestLoginUser(R.id.btnSignIn, mGoogle.account().getIdToken() ),this);
    }

    @Override
    protected void loadNextActivityHandler() {

    }

    @Override
    protected void loadHandlers() {
        this.handlers().put(R.id.btnSignIn, new LoginResponseHandler());
    }

    @Override
    public void onBackPressed() {

        if(mCloseApplication){
            super.onBackPressed();
        }else{
            Toast.makeText(this, "Esta por salir de la aplicacion", Toast.LENGTH_SHORT).show();
            mCloseApplication = true;
        }
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
