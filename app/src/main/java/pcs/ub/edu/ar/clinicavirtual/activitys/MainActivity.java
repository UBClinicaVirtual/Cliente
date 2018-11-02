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
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces.IServerRequest;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final int RC_SIGN_IN = 9001;
    private static final int RC_GET_TOKEN = 9002;
    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;
    GoogleSignInAccount mAccount;
    private GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();


        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        // requestEmail method, algo get their email address
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                //IMPORTANT!
                // momentaneamente! Resolver el tema del server client id
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInCliente with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        initListeners();

    }


    @Override
    protected void onStart() {
        super.onStart();


        // Check for existing Google Sign In mAccount, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }


    //the buttons are initialized
    private void initListeners() {
        findViewById(R.id.btnLogIn).setOnClickListener(this);
        findViewById(R.id.btnSignIn).setOnClickListener(this);
    }


    //depending on the button to make
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnLogIn:

                logInWithGoogle();
                break;

            case R.id.btnSignIn:

                Intent mIntentSignIn = new Intent(MainActivity.this, GoogleSignInActivity.class);
                startActivity(mIntentSignIn);
                break;

        }
    }

    // IMPORTANTE ACA!!   pd:  se que esta en español, es momentaneo, para ti didier ^^
    private void logInWithGoogle(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_GET_TOKEN);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.SignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
        if (requestCode == RC_GET_TOKEN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedtask) {
        try{
            mAccount = completedtask.getResult(ApiException.class);
            String idToken = mAccount.getIdToken();
            firebaseAuthWithGoogle(mAccount);

            connector().call( new ServerRequestLoginUser(R.id.btnLogIn,idToken),this);

        }catch (ApiException e){
            //The ApiException status code indicates the detailed failure reason
            Log.w(TAG, "signInResult:failed code= " + e.getStatusCode());
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
    Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

    AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
    mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success");
                        FirebaseUser user = mAuth.getCurrentUser();

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();

                    }
                }
            });
}

    @Override
    public void success(IServerRequest request) {

        switch (request.requesterId()){
            case R.id.btnLogIn:

                ServerRequestLoginUser serverRequestLoginUser  = (ServerRequestLoginUser) request;
                String response = serverRequestLoginUser.getUserData();
                Toast.makeText(this,response, Toast.LENGTH_SHORT).show();
                JSONObject json = null;
                try {
                    json = new JSONObject( response );
                    String apitoken = json.getJSONObject("user").getString("api_token");
                    connector().apiToken( apitoken );
                    connector().call( new ServerRequestUserGetPatientProfile( 0 ), this );
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Intent mMainScreen = new Intent(MainActivity.this, MainScreenActivity.class);
                startActivity(mMainScreen);

                break;

            default:

                ServerRequestUserGetPatientProfile responseGetProfile  = (ServerRequestUserGetPatientProfile) request;
                Toast.makeText(this, "GP : " + responseGetProfile.response() , Toast.LENGTH_SHORT).show();
                break;
        }

        //<editor-fold desc="LO MISMO PERO CON IF">
        /*if ( request.requesterId() == null )
        {
            ServerRequestUserGetPatientProfile responseGetProfile  = (ServerRequestUserGetPatientProfile) request;

            //Esto es un ejemplo
            Toast.makeText(this, "GP : " + responseGetProfile.response() , Toast.LENGTH_SHORT).show();
        }
        //PREGUNTAS QUE BOTON FUE PRESIONADO
        else if(request.requesterId().equals(R.id.btnGoogleSignIn)){
            ServerRequestLoginUser serverRequestLoginUser  = (ServerRequestLoginUser) request;
            String response = serverRequestLoginUser.getUserData();


            Toast.makeText(this,response, Toast.LENGTH_SHORT).show();
            //deberia quedar asi
             //UserData userData = serverRequestLoginUser.getUserData();

            // connector().apiToken( userData.apiToken() );

            //Como obtener los datos con un JSON Object
            JSONObject json = null;
            try {
                json = new JSONObject( response );

                // Seteo el api token con el api token que me llega del server
                String apitoken = json.getJSONObject("user").getString("api_token");
                connector().apiToken( apitoken );

                // Llamado de prueba para obtener el perfil de usuario
               // connector().call( new ServerRequestUserGetProfile( null ), this );

                connector().call( new ServerRequestUserGetPatientProfile( null ), this );


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else if(request.requesterId().equals(R.id.btnSignIn)){
            Intent mMainScreen = new Intent(MainActivity.this, MainScreenActivity.class);
            startActivity(mMainScreen);
        }*/
        //</editor-fold>
    }
}
