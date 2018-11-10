package pcs.ub.edu.ar.clinicavirtual.activitys.start;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;

import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.ServerRequestLoginUser;
import pcs.ub.edu.ar.clinicavirtual.google.Google;
import pcs.ub.edu.ar.clinicavirtual.handler.LoginResponseHandler;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.IGoogle;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    IGoogle mGoogle = new Google(this);
    Boolean mCloseApplication = false;


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
                view.setClickable(false);
                //PREGUNTAR QUE ID USER TRAJO
                startActivityForResult( mGoogle.SignInIntent() , mGoogle.RC() );

                findViewById(R.id.progress).setVisibility(View.VISIBLE);

                break;

        }
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
