package pcs.ub.edu.ar.clinicavirtual.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
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
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.user.ServerRequestUserGetProfile;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces.IServerRequest;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    Button mBtnLogIn;
    Button mBtnSignIn;
    SignInButton mBtnGoogleSignIn;

    private static final int RC_SIGN_IN = 9001;
    private static final int RC_GET_TOKEN = 9002;
    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;
    GoogleSignInAccount account;
    private GoogleSignInClient mGoogleSignInClient;
    private TextView mStatusTextView;
    private TextView mIdTokenTextView;

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


        initButtons();

    }


    @Override
    protected void onStart() {
        super.onStart();


        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(account);
    }

    private void updateUI(GoogleSignInAccount account) {

/*
                if (account != null) {
            mStatusTextView.setText(getString(R.string.signed_in_fmt, account.getDisplayName()));

            findViewById(R.id.sign_in_button).setVisibility(View.GONE);

        } else {
            mStatusTextView.setText(R.string.signed_out);

            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);

        }
*/
    }

    //the buttons are initialized
    private void initButtons() {
        findViewById(R.id.btnLogIn).setOnClickListener(this);
        findViewById(R.id.btnSignIn).setOnClickListener(this);
        findViewById(R.id.sign_in_button).setOnClickListener(this);
    }


    //depending on the button to make
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_button:

                //  logIn();

                //POR AHORA HASTA QUE SE TERMINE DE HACER LO DE ENVIAR Y RECIBIR ALGO CON EL TOKEN
                //signIn();
                getIdToken();
                break;

            case R.id.btnLogIn:
                // IUserProfileData mUserData =  getServerConnector().login(account.getIdToken());
                //Toast.makeText(this, mUserData.getmName(), Toast.LENGTH_SHORT).show();
                Intent mMainScreen = new Intent(MainActivity.this, MainScreenActivity.class);
                startActivity(mMainScreen);

                break;
            case R.id.btnSignIn:
                signInWithouthGoogle();
                break;

        }
    }

    private void signInWithouthGoogle(){
        Intent mIntentSignIn = new Intent(MainActivity.this, GoogleSignInActivity.class);
        startActivity(mIntentSignIn);
    }
    private void logIn() {
        Intent mIntentLogIn = new Intent(MainActivity.this, MainScreenActivity.class);
        startActivity(mIntentLogIn);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    // IMPORTANTE ACA!!   pd:  se que esta en español, es momentaneo, para ti didier ^^
    private void getIdToken(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_GET_TOKEN);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
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
            account = completedtask.getResult(ApiException.class);
            //GET ID TOKEN
            String idToken = account.getIdToken();
            firebaseAuthWithGoogle(account);
            //Toast.makeText(this, account.getIdToken(), Toast.LENGTH_SHORT).show();

            //connector().call(new ServerRequestRegisterUser(findViewById(R.id.sign_in_button),idToken),this);

            connector().call( new ServerRequestLoginUser(findViewById(R.id.sign_in_button),idToken),this);

            // PENDIENTE PENDIENTE
            // ENVIAR ID TOKENN AL SERVER Y VALIDAR

            // signed in successfully, show auth. UI.
            //updateUI(account);
        }catch (ApiException e){
            //The ApiException status code indicates the detailed failure reason
            Log.w(TAG, "signInResult:failed code= " + e.getStatusCode());
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            updateUI(null);
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

                    // ...
                }
            });
}

    @Override
    public void success(IServerRequest request) {

        if ( request.requesterId() == null )
        {
            ServerRequestUserGetPatientProfile responseGetProfile  = (ServerRequestUserGetPatientProfile) request;

            //Esto es un ejemplo
            //Toast.makeText(this, "GP : " + responseGetProfile.response() , Toast.LENGTH_SHORT).show();
        }
        //PREGUNTAS QUE BOTON FUE PRESIONADO
        else if(request.requesterId().equals(R.id.sign_in_button)){
            ServerRequestLoginUser serverRequestLoginUser  = (ServerRequestLoginUser) request;
            String response = serverRequestLoginUser.getUserData();

            //deberia quedar asi
            // UserData response = serverRequestLoginUser.getUserData();
            // connector().apiToken( response.apiToken() );

            //Como obtener los datos con un JSON Object
            JSONObject json = null;
            try {
                json = new JSONObject( response );

                // Seteo el api token con el api token que me llega del server
                connector().apiToken( json.getJSONObject("user").getString("api_token") );

                // Llamado de prueba para obtener el perfil de usuario
                //connector().call( new ServerRequestUserGetProfile( null ), this );

                connector().call( new ServerRequestUserGetPatientProfile( null ), this );


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
