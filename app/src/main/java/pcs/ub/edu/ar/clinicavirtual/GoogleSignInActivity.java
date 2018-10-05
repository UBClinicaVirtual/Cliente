package pcs.ub.edu.ar.clinicavirtual;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GoogleSignInActivity extends AppCompatActivity {
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
