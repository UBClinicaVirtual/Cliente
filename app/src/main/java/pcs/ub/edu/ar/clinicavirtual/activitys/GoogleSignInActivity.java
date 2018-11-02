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

import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.requests.ServerRequestLoginUser;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces.IServerRequest;

public class GoogleSignInActivity extends BaseActivity {

    private static final int RC_SIGN_IN = 9001;
    private static final int RC_GET_TOKEN = 9002;
    private static final String TAG = "GoogleSignInActivity";
    private FirebaseAuth mAuth;
    GoogleSignInAccount account;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_sign_in);
        mAuth = FirebaseAuth.getInstance();
        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions() );
        initListeners();

    }

    private GoogleSignInOptions googleSignInOptions() {
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();
    }

    private void initListeners() {
        findViewById(R.id.btnGoogleSignIn).setOnClickListener(this);
    }

    @Override
    public void success(IServerRequest request) {

        if(request.requesterId() == R.id.btnGoogleSignIn){
            ServerRequestLoginUser serverRequestLoginUser  = (ServerRequestLoginUser) request;
            String response = serverRequestLoginUser.getUserData();
            Toast.makeText(this,response, Toast.LENGTH_SHORT).show();

            Intent mIntentDataRegister = new Intent(GoogleSignInActivity.this, DataRegisterActivity.class);
            startActivity(mIntentDataRegister);
        }

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnGoogleSignIn:
                signInWithGoogle();
                break;
        }
    }

    private void signInWithGoogle(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_GET_TOKEN);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

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
            String idToken = account.getIdToken();
            firebaseAuthWithGoogle(account);
            connector().call( new ServerRequestLoginUser(R.id.btnGoogleSignIn,idToken),this);

            //deberia ir
            // connector().call(new ServerRequestRegisterUser(R.id.sign_in_button,idToken),this);

        }catch (ApiException e){
            Log.w(TAG, "signInResult:failed code= " + e.getStatusCode());
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

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
                            Toast.makeText(GoogleSignInActivity.this, "error", Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
}
