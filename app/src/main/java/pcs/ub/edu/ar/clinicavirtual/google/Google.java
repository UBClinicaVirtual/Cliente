package pcs.ub.edu.ar.clinicavirtual.google;

//<editor-fold desc="IMPORTS">
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
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
import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.IGoogle;
//</editor-fold>

public class Google implements IGoogle {

    private static final int RC_GET_TOKEN = 9002;
    private static final String TAG = "GoogleSignInActivity";
    private FirebaseAuth mAuth;
    private GoogleSignInAccount account;
    private GoogleSignInClient mGoogleSignInClient;
    private BaseActivity activity;

    public Google(BaseActivity activity){
        this.activity = activity;
        mAuth = FirebaseAuth.getInstance();
    }


    private GoogleSignInOptions googleSignInOptions(String cliendId) {
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(cliendId)
                .requestEmail()
                .build();
    }



    @Override
    public void GoogleConnection(BaseActivity activity, Integer clientId) {

        mGoogleSignInClient = GoogleSignIn.getClient(activity, googleSignInOptions(activity.getString(clientId)) );

    }

    @Override
    public Intent SignInIntent() {
        return mGoogleSignInClient.getSignInIntent();
    }

    @Override
    public Integer RC() {
        return RC_GET_TOKEN ;
    }

    @Override
    public void handleSignInResult(Task<GoogleSignInAccount> completedtask) {
        try{

            account = completedtask.getResult(ApiException.class);
            firebaseAuthWithGoogle(account);

        }catch (ApiException e){
            Log.w(TAG, "signInResult:failed code= " + e.getStatusCode());
        }
    }

    @Override
    public GoogleSignInAccount account() {
        return account;
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }

}
