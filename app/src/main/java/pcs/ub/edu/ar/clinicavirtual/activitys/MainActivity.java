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

import org.w3c.dom.Text;

import pcs.ub.edu.ar.clinicavirtual.R;

public class MainActivity extends BaseActivity {

    Button mBtnLogIn;
    Button mBtnSignIn;

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
        createOnClickListenerButtons();
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

    private void createOnClickListenerButtons() {

        mBtnSignIn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent mIntentSignIn = new Intent(MainActivity.this, GoogleSignInActivity.class);
                startActivity(mIntentSignIn);
            }
        } );

        mBtnLogIn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent mIntentLogIn = new Intent(MainActivity.this, MainScreenActivity.class);
                startActivity(mIntentLogIn);
            }
        } );


    }


    //the buttons are initialized
    private void initButtons() {

        mBtnLogIn = (Button) findViewById(R.id.btnLogIn);
        mBtnSignIn =(Button) findViewById(R.id.btnSignIn);

    }
}
