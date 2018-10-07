package pcs.ub.edu.ar.clinicavirtual.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;

import org.w3c.dom.Text;

import pcs.ub.edu.ar.clinicavirtual.R;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    Button mBtnLogIn;
    Button mBtnSignIn;
    SignInButton mBtnGoogleSignIn;

    private static final int RC_SIGN_IN = 9001;

    private GoogleSignInClient mGoogleSignInClient;
    private TextView mStatusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Configure sign-in to request the user's ID, email address, and basic
            // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
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
        switch (view.getId()){
            case R.id.sign_in_button:
                signIn();
                break;

            case R.id.btnLogIn:
                logIn();
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
}
