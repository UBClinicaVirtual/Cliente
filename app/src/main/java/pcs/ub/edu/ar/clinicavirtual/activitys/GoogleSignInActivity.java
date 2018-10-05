package pcs.ub.edu.ar.clinicavirtual.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pcs.ub.edu.ar.clinicavirtual.R;

public class GoogleSignInActivity extends BaseActivity {
    Button mBtnGoogleSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_sign_in);

        initButton();
        createOnClickListenerButton();
    }

    private void createOnClickListenerButton() {
        mBtnGoogleSignIn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent mIntentDataRegister = new Intent(GoogleSignInActivity.this, DataRegisterActivity.class);
                startActivity(mIntentDataRegister);
            }
        } );
    }

    private void initButton() {

        mBtnGoogleSignIn =(Button) findViewById(R.id.btnGoogleSignIn);
    }
}
