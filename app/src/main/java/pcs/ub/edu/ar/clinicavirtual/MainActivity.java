package pcs.ub.edu.ar.clinicavirtual;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;




import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

    private GoogleSignInClient mGoogleSignInClient;
    private TextView mStatusTextView;

    private static final String TAG = "LoginActivity";
    private static final int RC_SIGN_IN = 9001;

    Button mBtnLogIn;
    Button mBtnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                //PIDO EL TOKEN PARA ENVIAR AL SERVIDOR
                //.requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();

        //Listeners

        findViewById(R.id.sign_in_button).setOnClickListener(this);


        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        initButtons();
        createOnClickListenerButtons();

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
